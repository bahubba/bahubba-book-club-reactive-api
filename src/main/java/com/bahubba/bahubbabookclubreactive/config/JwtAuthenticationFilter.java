package com.bahubba.bahubbabookclubreactive.config;

import com.bahubba.bahubbabookclubreactive.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Filters all requests to non-auth endpoints by validity of JWT from the request's auth cookie
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtService jwtService;
    private final ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain filterChain) {
        return jwtService.getJwtFromCookies(exchange.getRequest())
            .flatMap(jwt -> {
                if (jwt == null || jwt.isEmpty() || shouldSkipFilter(exchange.getRequest())) {
                    return filterChain.filter(exchange);
                }

                String username = jwtService.extractUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(username);
                    return userDetailsMono.flatMap(userDetails -> {
                        if (jwtService.isTokenValid(jwt, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                            );
                            // TODO - Additional details for auth token (IP, etc.)
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(exchange.getRequest()));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                        return filterChain.filter(exchange);
                    });
                }

                return filterChain.filter(exchange);
            });
    }

    private boolean shouldSkipFilter(ServerHttpRequest request) {
        String path = request.getURI().getPath();
        return path.startsWith("/api/v1/auth/");
    }
}
