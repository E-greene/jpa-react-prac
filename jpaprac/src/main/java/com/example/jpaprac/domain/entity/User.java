package com.example.jpaprac.domain.entity;

import com.example.jpaprac.domain.common.BaseTimeEntity;
import com.example.jpaprac.presentation.dto.user.CreateUserCommand;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR") //auto_increment
    @SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "login_pwd", nullable = false)
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

    public static User create(CreateUserCommand createUserCommand, String encodedPwd) {
        User user = new User();
        user.name = createUserCommand.getName();
        user.email = createUserCommand.getEmail();
        user.loginId = createUserCommand.getLoginId();
        user.loginPwd = encodedPwd;
        user.role = createUserCommand.getRole() != null ? createUserCommand.getRole() : Role.USER;
        return user;
    }

}
