package com.example.jpaprac.reposirory;

import com.example.jpaprac.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {


}
