package com.example.jpaprac.presentation.dto.user;

import com.example.jpaprac.domain.entity.Role;

public class CreateUserRequest {
    private String name;
    private String email;
    private String loginId;
    private String loginPwd;
    private Role role;

    public CreateUserRequest(String name, String email, String loginId, String loginPwd, Role role) {
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public Role getRole() {
        return role;
    }
}
