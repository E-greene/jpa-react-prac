package com.example.jpaprac.infrastructure.persistence.auth;

import com.example.jpaprac.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthJpaRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
    User findByLoginIdAndLoginPwd(String loginId, String loginPwd);
}
