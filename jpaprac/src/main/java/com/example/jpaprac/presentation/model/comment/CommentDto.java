package com.example.jpaprac.presentation.model.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    private Long id;
    private Long userId;
    private Long boardId;
    private String content;
    private Long parentId;
    private List<CommentDto> children = new ArrayList<>();

    public CommentDto() {

    }



    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getContent() {
        return content;
    }

    public Long getParentId() {
        return parentId;
    }

    public List<CommentDto> getChildren() {
        return children;
    }
}
