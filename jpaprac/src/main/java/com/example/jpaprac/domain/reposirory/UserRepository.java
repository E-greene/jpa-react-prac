package com.example.jpaprac.reposirory;

import com.example.jpaprac.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginIdAndLoginPwd(String loginId, String loginPwd);
}
