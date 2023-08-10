package com.bahubba.bahubbabookclubreactive.repository;

import com.bahubba.bahubbabookclubreactive.model.document.RefreshToken;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface RefreshTokenRepo extends ReactiveMongoRepository<RefreshToken, UUID> {
    Mono<RefreshToken> findByToken(final String token);

    Mono<Void> deleteByReaderId(final UUID readerId);

    void deleteByToken(final String token);
}
