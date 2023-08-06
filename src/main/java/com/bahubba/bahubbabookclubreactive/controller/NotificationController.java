package com.bahubba.bahubbabookclubreactive.controller;

import com.bahubba.bahubbabookclubreactive.service.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/notification")
@Log4j2 // DELETEME
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/count-new", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getUnviewedNotificationCount() {
        return notificationService.getUnviewedNotificationCount();
    }

    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> test() {
        log.info("Yo, we're here in test"); // DELETEME
        return notificationService.test();
    }
}
