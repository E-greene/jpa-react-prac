package com.example.jpaprac.infrastructure.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedissonBidLockManager {

    private final RedissonClient redissonClient;

    @Autowired
    public RedissonBidLockManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean tryBidWithLock(String roomId, Runnable biddingLogic) {
        RLock lock = redissonClient.getLock("bid:lock:" + roomId);

        try {
            if (lock.tryLock(1, 5, TimeUnit.SECONDS)) {
                biddingLogic.run();
                return true;
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }
}
