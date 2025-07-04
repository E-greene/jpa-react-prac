package com.example.jpaprac.presentation.dto.board;

public class CreateBoardCommand {
    private final Long userId;
    private final String title;
    private final String content;


    public CreateBoardCommand(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static CreateBoardCommand of(Long userId, String title, String content) {
        return new CreateBoardCommand(userId, title, content);
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
