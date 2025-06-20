package com.example.jpaprac.infrastructure.persistence.comment;

import com.example.jpaprac.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

}
