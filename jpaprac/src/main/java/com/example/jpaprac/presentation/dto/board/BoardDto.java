package com.example.jpaprac.presentation.dto.board;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.entity.User;

public class BoardDto {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String content;

    public BoardDto() {

    }

    public BoardDto(Long id, Long userId, String userName, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
    }


    public static BoardDto fromEntity(Board board) {
        return new BoardDto(
                board.getId(),
                board.getUser().getId(),
                board.getUser().getName(),
                board.getTitle(),
                board.getContent()
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

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
