package com.example.jpaprac.application.service.bid;

import com.example.jpaprac.infrastructure.redis.RedisPublisher;
import com.example.jpaprac.presentation.dto.bid.BidMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BidService {

    private final RedissonClient redissonClient;
    private final RedisPublisher redisPublisher;
    private final ObjectMapper objectMapper;

    @Autowired
    public BidService(RedissonClient redissonClient, RedisPublisher redisPublisher, ObjectMapper objectMapper) {
        this.redissonClient = redissonClient;
        this.redisPublisher = redisPublisher;
        this.objectMapper = objectMapper;
    }

    public boolean tryBid(String roomId, String username, int price) {
        RLock lock = redissonClient.getLock("bid:lock:" + roomId);
        try {
            if(lock.tryLock(1,5, TimeUnit.SECONDS)) {
                //실제 가격 갱신 및 저장 로직 생략, 바로 publish
                BidMessage bid = new BidMessage(roomId, username, price);
                redisPublisher.publish("bidding", objectMapper.writeValueAsString(bid));
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }
}
