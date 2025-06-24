package com.example.jpaprac.presentation.dto.user;

import com.example.jpaprac.domain.entity.Role;

public class CreateUserCommand {

    private final String name;
    private final String email;
    private final String loginId;
    private final String loginPwd;
    private final Role role;

    public CreateUserCommand(String name, String email, String loginId, String loginPwd, Role role) {
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static CreateUserCommand fromCreateBoardRequest(CreateUserRequest createUserRequest) {
        return new CreateUserCommand(
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getLoginId(),
                createUserRequest.getLoginPwd(),
                createUserRequest.getRole()
        );
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
