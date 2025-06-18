package com.example.jpaprac.domain.entity;

import com.example.jpaprac.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "boards")
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String deleteYN; //Y으로 기본값 설정

    public Board() {

    }

    public Board(Long id,User user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.deleteYN = "N";
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDeleteYN() {
        return deleteYN;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void softDelete() {
        this.deleteYN = "Y";
    }
}
