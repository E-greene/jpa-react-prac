package com.example.jpaprac.presentation.dto.board;

import java.time.LocalDateTime;

public class BoardResponse {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdDate;

    public BoardResponse() {

    }

    public BoardResponse(Long id, Long userId, String title, String content, String userName, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createdDate = createdDate;
    }

    public static BoardResponse fromBoardApplicationDto(BoardApplicationDto boardApplicationDto) {
        return new BoardResponse(
                boardApplicationDto.getId(),
                boardApplicationDto.getUserId(),
                boardApplicationDto.getTitle(),
                boardApplicationDto.getContent(),
                boardApplicationDto.getUserName(),
                boardApplicationDto.getCreatedDate()
        );
    }

    public Long getId() {
        return id;
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

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
