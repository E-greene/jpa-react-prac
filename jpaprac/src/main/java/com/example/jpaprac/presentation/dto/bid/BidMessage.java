package com.example.jpaprac.presentation.dto.bid;

public class BidMessage {
    private String roomId;
    private String username;
    private int price;



    public BidMessage(String roomId, String username, int price) {
        this.roomId = roomId;
        this.username = username;
        this.price = price;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getUsername() {
        return username;
    }

    public int getPrice() {
        return price;
    }
}
