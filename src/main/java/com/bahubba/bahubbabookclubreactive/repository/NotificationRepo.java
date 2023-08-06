package com.bahubba.bahubbabookclubreactive.repository;

import com.bahubba.bahubbabookclubreactive.model.entity.Notification;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface NotificationRepo extends ReactiveCrudRepository<Notification, UUID> {
    @Query(
        value = "SELECT COUNT(*) " +
            "FROM notification n " +
            "WHERE n.target_reader_id = :readerId " +
            "AND NOT EXISTS (" +
            "SELECT 1 FROM notification_views nv " +
            "WHERE nv.notification_id = n.id " +
            "AND nv.reader_id = :readerId)"
    )
    Flux<Long> countUnviewed(UUID readerId);

    @Query("SELECT COUNT(*) FROM notification")
    Flux<Long> countFlux();
}
