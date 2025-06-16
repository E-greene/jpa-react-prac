package com.example.jpaprac.presentation.model;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.entity.User;

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
                board.getUser().getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }

    public static Board toEntity(BoardDto dto, User user) {
        return new Board(dto.getId(),user, dto.getTitle(), dto.getContent());
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
