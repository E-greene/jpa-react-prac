package com.example.jpaprac.domain.entity;

import com.example.jpaprac.domain.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @Column
    private String deleteYN;

    public Comment() {}

    public Comment(Long id, User user, Board board, String content, Comment parent) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.content = content;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Board getBoard() {
        return board;
    }

    public String getContent() {
        return content;
    }

    public Comment getParent() {
        return parent;
    }

    public String getDeleteYN() {
        return deleteYN;
    }
}
