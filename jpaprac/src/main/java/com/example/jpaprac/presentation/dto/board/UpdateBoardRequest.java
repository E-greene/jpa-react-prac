package com.example.jpaprac.presentation.dto.board;

public class UpdateBoardRequest {
    private String title;
    private String content;

    public UpdateBoardRequest(String title, String content) {
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
