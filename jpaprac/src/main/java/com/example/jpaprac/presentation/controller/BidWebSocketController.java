package com.example.jpaprac.presentation.controller;

import com.example.jpaprac.infrastructure.persistence.redis.BidMessagePublisher;
import com.example.jpaprac.presentation.dto.bid.BidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

public class BidWebSocketController {

    private final BidMessagePublisher publisher;

    @Autowired
    public BidWebSocketController(BidMessagePublisher publisher) {
        this.publisher = publisher;
    }


    @MessageMapping("/bid")
    public void handleBid(BidMessage message) {
        publisher.publish(message)
    }
}
