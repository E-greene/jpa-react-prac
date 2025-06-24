package com.example.jpaprac.presentation.dto.auth;

public class LoginUserCommand {
    private final String loginId;
    private final String loginPwd;

    public LoginUserCommand(String loginId, String loginPwd) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
    }

    public static LoginUserCommand fromLoginUserRequest(LoginUserRequest loginUserRequest) {
        return new LoginUserCommand(
                loginUserRequest.getLoginId(),
                loginUserRequest.getLogidPwd()
        );
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }
}
