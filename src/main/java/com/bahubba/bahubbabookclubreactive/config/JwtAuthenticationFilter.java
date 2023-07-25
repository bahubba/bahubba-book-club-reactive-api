package com.bahubba.bahubbabookclubreactive.config;

import com.bahubba.bahubbabookclubreactive.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
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

    /**
     * Authorization filter logic
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {
        String jwt = jwtService.getJwtFromCookies(exchange.getRequest());
        final String username;

        if (jwt == null || "".equals(jwt)) {
            return filterChain.filter(exchange);
        }

        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            return userDetailsService.findByUsername(username)
                .flatMap(userDetails -> {
                    if(jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                    return filterChain.filter(exchange);
                });
        }

        return filterChain.filter(exchange);
    }
}
