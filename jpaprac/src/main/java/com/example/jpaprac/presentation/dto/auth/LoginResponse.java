package com.example.jpaprac.presentation.dto.auth;

import com.example.jpaprac.domain.entity.Role;

public class LoginResponse {

    private final String loginId;
    private final String role;

    public LoginResponse(String loginId, String role) {
        this.loginId = loginId;
        this.role = role;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getRole() {
        return role;
    }
}
