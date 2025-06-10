package com.example.jpaprac.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") //테이블명 user가 예약어라는 error
public class User extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String loginId;

    @Column
    private String loginPwd;

    @Enumerated(EnumType.STRING) // db에 문자열로 저장하기 위함
    @Column
    private Role role;

    public User() {

    }

    public User(Long id, String name, String email, String loginId, String loginPwd, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
