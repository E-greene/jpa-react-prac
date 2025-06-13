package com.example.jpaprac.model;

import com.example.jpaprac.domain.entity.Role;
import com.example.jpaprac.domain.entity.User;

import java.time.LocalDateTime;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String loginId;
    private String loginPwd;
    private Role role;

    public UserDto() {

    }

    public UserDto(Long id, String name, String email, String loginId, String loginPwd, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLoginId(),
                user.getLoginPwd(),
                user.getRole()
        );
    }

    public static User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(),dto.getLoginId(), dto.getLoginPwd(), dto.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
