package com.example.jpaprac.infrastructure.persistence.board;

import com.example.jpaprac.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserIdAndDeleteYN(Long userId, String deleteYN);
    List<Board> findAllByDeleteYN(String deleteYN);
    Optional<Board> findByIdAndDeleteYN(Long id, String deleteYN);

    //작성자도 조회 가능한 게시물 목록조회
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.deleteYN = :deleteYN")
    List<Board> findAllWithUser(@Param("deleteYN") String deleteYN);

    //작성자도 조회 가능한 게시글 상세조회
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.id = :id AND b.deleteYN = :deleteYN")
    Optional<Board> findByIdWithUser(@Param("id") Long boardId, @Param("deleteYN") String deleteYN);
}
