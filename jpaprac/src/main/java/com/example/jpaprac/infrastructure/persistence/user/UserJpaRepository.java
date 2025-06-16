package com.example.jpaprac.infrastructure.persistence.user;

import com.example.jpaprac.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
