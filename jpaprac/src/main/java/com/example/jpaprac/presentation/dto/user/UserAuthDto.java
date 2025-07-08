package com.example.jpaprac.presentation.dto.user;

import com.example.jpaprac.domain.entity.Role;

import java.security.Principal;

public class UserAuthDto implements Principal {

    private final Long id;
    private final String name;
    private final String loginId;
    private final Role role;

    public UserAuthDto(Long id, String name, String loginId, Role role) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoginId() {
        return loginId;
    }

    public Role getRole() {
        return role;
    }
}
