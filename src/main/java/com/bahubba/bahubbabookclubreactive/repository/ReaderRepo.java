package com.bahubba.bahubbabookclubreactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepo extends ReactiveCrudRepository<Reader, UUID> {
    Mono<Reader> findByUsername(final String username);

    Optional<Reader> findByEmail(final String email);

    Optional<Reader> findByUsernameOrEmail(final String username, final String email);

    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);

    boolean existsByUsernameOrEmail(final String username, final String email);
}
