package com.example.jpaprac.domain.entity;

import javax.persistence.*;

@Entity
public class User extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String userId;

    @Column
    private String userPwd;

    @Enumerated(EnumType.STRING) // db에 문자열로 저장하기 위함
    @Column
    private Role role;

    public User() {

    }

    public User(Long id, String name, String userId, String userPwd, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
