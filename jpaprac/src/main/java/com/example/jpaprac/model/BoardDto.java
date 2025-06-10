package com.example.jpaprac.model;

import com.example.jpaprac.domain.entity.Board;

import java.time.LocalDateTime;

public class BoardDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardDto() {

    }

    public BoardDto(Long id, Long userId, String title, String content,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    public static BoardDto fromEntity(Board board) {
        return new BoardDto(
                board.getId(),
                board.getUserId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }

    public static Board toEntity(BoardDto dto) {
        return new Board(dto.getId(),dto.getUserId(), dto.getTitle(), dto.getContent());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
