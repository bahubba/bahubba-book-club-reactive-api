package com.bahubba.bahubbabookclubreactive.handler;

import com.bahubba.bahubbabookclubreactive.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TestHandler implements WebSocketHandler {
    private final NotificationService notificationService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<Long> test = notificationService.test();
        Flux<WebSocketMessage> messageFlux = test.map(String::valueOf).map(session::textMessage);

        return session.send(messageFlux);
    }
}
