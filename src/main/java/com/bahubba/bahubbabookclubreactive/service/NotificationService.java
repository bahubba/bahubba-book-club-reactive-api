package com.bahubba.bahubbabookclubreactive.service;

import reactor.core.publisher.Flux;

public interface NotificationService {
    Flux<Long> getUnviewedNotificationCount();

    Flux<Long> test();
}
