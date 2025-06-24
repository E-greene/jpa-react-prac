package com.example.jpaprac.presentation.dto.auth;

public class LoginUserRequest {

    private String loginId;
    private String loginPwd;

    public LoginUserRequest(String loginId, String loginPwd) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }
}
