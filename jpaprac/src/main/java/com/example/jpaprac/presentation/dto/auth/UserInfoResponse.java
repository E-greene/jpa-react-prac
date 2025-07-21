package com.example.jpaprac.presentation.dto.auth;

public class UserInfoResponse {

    private final String loginId;
    private final String role;

    public UserInfoResponse(String loginId, String role) {
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
