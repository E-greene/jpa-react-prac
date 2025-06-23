package com.example.jpaprac.presentation.dto.board;

import com.example.jpaprac.domain.entity.Board;

import java.time.LocalDateTime;

public class BoardApplicationDto {
    private final Long id;
    private final Long userId;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;

    public BoardApplicationDto(Long id, Long userId, String userName, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static BoardApplicationDto fromEntity(Board board) {
        return new BoardApplicationDto(
                board.getId(),
                board.getUser().getId(),
                board.getUser().getName(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
