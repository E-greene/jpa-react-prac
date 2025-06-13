package com.example.jpaprac.domain.entity;

import com.example.jpaprac.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String title;

    @Column
    private String content;

    public Board() {

    }

    public Board(Long id,Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
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

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
