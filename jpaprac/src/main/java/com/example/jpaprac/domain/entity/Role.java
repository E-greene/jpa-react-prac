package com.example.jpaprac.domain.entity;

public enum Role {

    //GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_MEMBER","일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String roleName;
    private final String displayName;

    private Role(String roleName, String displayName) {
        this.roleName = roleName;
        this.displayName = displayName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
