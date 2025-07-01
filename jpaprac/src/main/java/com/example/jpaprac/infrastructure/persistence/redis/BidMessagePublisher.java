package com.example.jpaprac.infrastructure.persistence.redis;

import com.example.jpaprac.presentation.dto.bid.BidMessage;

public interface BidMessagePublisher {
    void publish(BidMessage message);
}
