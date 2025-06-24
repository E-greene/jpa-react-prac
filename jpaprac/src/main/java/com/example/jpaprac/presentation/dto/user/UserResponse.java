package com.example.jpaprac.presentation.dto.user;

import com.example.jpaprac.domain.entity.Role;

public class UserResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String loginId;
    private final Role role;

    public UserResponse(Long id, String name, String email, String loginId, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.role = role;
    }

    public static UserResponse fromUserApplicationDto(UserApplicationDto userApplicationDto) {
        return new UserResponse(
                userApplicationDto.getId(),
                userApplicationDto.getName(),
                userApplicationDto.getEmail(),
                userApplicationDto.getLoginId(),
                userApplicationDto.getRole()
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

    public Role getRole() {
        return role;
    }
}
