package com.bahubba.bahubbabookclubreactive.repository;

import com.bahubba.bahubbabookclubreactive.model.document.Reader;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ReaderRepo extends ReactiveMongoRepository<Reader, UUID> {
    Mono<Reader> findByUsername(final String username);
}
