package com.example.jpaprac.presentation.dto.user;

import com.example.jpaprac.domain.entity.Role;
import com.example.jpaprac.domain.entity.User;

public class UserApplicationDto {

    private final Long id;
    private final String name;
    private final String email;
    private final String loginId;
    private final String loginPwd;
    private final Role role;

    public UserApplicationDto(Long id, String name, String email, String loginId, String loginPwd, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static UserApplicationDto fromEntity(User user) {
        return new UserApplicationDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLoginId(),
                user.getLoginPwd(),
                user.getRole()
        );
    }

    public Long getId() {
        return id;
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
