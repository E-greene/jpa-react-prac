package com.example.jpaprac.domain.repository.user;

import com.example.jpaprac.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginIdAndLoginPwd(String loginId, String loginPwd);
    User save(User dto);
}