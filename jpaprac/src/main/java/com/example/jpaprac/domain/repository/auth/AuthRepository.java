package com.example.jpaprac.domain.repository.auth;

import com.example.jpaprac.domain.entity.User;

import java.util.Optional;

public interface AuthRepository {
    User findByLoginId(String loginId);
    User findByLoginIdAndLoginPwd(String loginId, String loginPwd);
    User save(User dto);
}
