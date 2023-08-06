package com.bahubba.bahubbabookclubreactive.service.impl;

import com.bahubba.bahubbabookclubreactive.repository.NotificationRepo;
import com.bahubba.bahubbabookclubreactive.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;

    // TODO - Get the current user's ID from the security context/JWT
    @Override
    public Flux<Long> getUnviewedNotificationCount() {
        return notificationRepo.countUnviewed(UUID.fromString("4e940a89-9db8-4e29-b6c3-485925976be3"));
    }

    @Override
    public Flux<Long> test() {
        return notificationRepo.countFlux();
    }
}
