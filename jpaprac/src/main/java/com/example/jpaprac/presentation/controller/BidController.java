package com.example.jpaprac.presentation.controller;

import com.example.jpaprac.application.service.bid.BidService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.presentation.dto.bid.BidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    public ResponseEntity<ApiResponse<Void>> bid(@RequestBody BidMessage bid) {
        boolean success = bidService.tryBid(bid.getRoomId(), bid.getUsername(), bid.getPrice());

        if (success) {
            return ResponseEntity.ok(ApiResponse.success("입찰에 성공했습니다."));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("입찰에 실패했습니다."));
        }
    }
}
