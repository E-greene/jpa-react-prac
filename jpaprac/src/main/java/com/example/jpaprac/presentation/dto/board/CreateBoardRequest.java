package com.example.jpaprac.presentation.dto.board;

public class CreateBoardRequest {

    private String title;
    private String content;

    public CreateBoardRequest() {

    }

    public CreateBoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
