package com.example.jpaprac.presentation.dto.auth;

public class LoginUserRequest {

    private String loginId;
    private String logidPwd;

    public LoginUserRequest(String loginId, String logidPwd) {
        this.loginId = loginId;
        this.logidPwd = logidPwd;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLogidPwd() {
        return logidPwd;
    }
}
