package com.bahubba.bahubbabookclubreactive.service.impl;

import com.bahubba.bahubbabookclubreactive.repository.ReaderRepo;
import com.bahubba.bahubbabookclubreactive.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReaderServiceImpl implements ReaderService, ReactiveUserDetailsService {
    @Autowired
    private ReaderRepo readerRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return readerRepo.findByUsername(username)
            .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
            .map(reader -> User.builder()
                .username(reader.getUsername())
                .password(reader.getPassword())
                .roles(reader.getRole())
                .build()
            );
    }
}
