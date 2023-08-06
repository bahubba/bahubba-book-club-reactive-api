package com.bahubba.bahubbabookclubreactive.config;

import com.bahubba.bahubbabookclubreactive.repository.ReaderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

/**
 * Configures beans to be used within the application
 */
@Configuration
@PropertySource("classpath:application.yaml")
@RequiredArgsConstructor
public class AppConfig {

    private final ReaderRepo readerRepo;

    /**
     * Creates a ReactiveUserDetailsService for loading users by username
     * @return ReactiveUserDetailsService
     */
    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return username -> readerRepo.findByUsername(username)
            .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
            .cast(UserDetails.class);
    }

    /**
     * Creates a ReactiveAuthenticationManager
     * @return ReactiveAuthenticationManager
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
            new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }

    /**
     * Creates a password encoder
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
