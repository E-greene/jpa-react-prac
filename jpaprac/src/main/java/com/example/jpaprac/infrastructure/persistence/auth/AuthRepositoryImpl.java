package com.example.jpaprac.infrastructure.persistence.auth;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.auth.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;

    @Autowired
    public AuthRepositoryImpl(AuthJpaRepository authJpaRepository) {
        this.authJpaRepository = authJpaRepository;
    }

    @Override
    public User findByLoginId(String loginId) {
        return authJpaRepository.findByLoginId(loginId);
    }

    @Override
    public User findByLoginIdAndLoginPwd(String loginId, String loginPwd) {
        return authJpaRepository.findByLoginIdAndLoginPwd(loginId, loginPwd);
    }

    public User save(User user) {
        return authJpaRepository.save(user);
    }
}
