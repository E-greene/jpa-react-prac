package com.example.jpaprac.presentation.model.user;

import com.example.jpaprac.domain.entity.Role;
import com.example.jpaprac.domain.entity.User;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String loginId;
    private String loginPwd;
    private Role role;

    public UserDto() {

    }

    public UserDto(Long id, String name, String email, String loginId, String loginPwd, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLoginId(),
                user.getLoginPwd(),
                user.getRole()
        );
    }

    public static User toEntity(UserDto dto) {
        Role role = dto.getRole() != null ? dto.getRole() : Role.USER;
        return new User(dto.getId(), dto.getName(), dto.getEmail(),dto.getLoginId(), dto.getLoginPwd(), role);
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
