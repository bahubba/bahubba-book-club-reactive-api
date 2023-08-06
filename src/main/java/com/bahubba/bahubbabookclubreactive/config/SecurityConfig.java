package com.bahubba.bahubbabookclubreactive.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ReactiveAuthenticationManager authenticationManager;

    // TODO  - Implement JWT authorization with jose or other reactive JWT library
//    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Sets up security for the application
     * @param http HTTP security object for Spring
     * @return SecurityFilterChain
     * @throws Exception
     * TODO - Update allowed origins after deploying and setting up a reverse proxy
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .httpBasic(Customizer.withDefaults())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authenticationManager(authenticationManager)
//            .addFilterBefore(jwtAuthFilter, SecurityWebFiltersOrder.FIRST)
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://localhost:3000", "https://127.0.0.1:3000"));
        config.setAllowedHeaders(List.of(
            "Accept",
            "Content-Type",
            "X-Requested-With",
            "Authorization",
            "Access-Control-Allow-Origin"
        ));
        config.setAllowCredentials(true);
        config.setAllowedMethods(List.of("GET", "POST", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
