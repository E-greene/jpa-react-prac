package com.example.jpaprac.application.service.board;

import com.example.jpaprac.domain.common.exception.BoardNotFoundException;
import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.user.UserRepository;
import com.example.jpaprac.presentation.model.BoardDto;
import com.example.jpaprac.domain.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }
    
    //전체 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    //사용자 ID로 게시글 조회
    @Transactional
    public List<BoardDto> findBoardsByUserId(Long userId) {
        List<Board> boards = boardRepository.findByUserId(userId);
        return boards.stream()
                .map(BoardDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    //게시글 단건조회
    @Transactional(readOnly = true)
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id);
        if(board == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }
        return BoardDto.fromEntity(board);
    }
    
    //게시글 생성
    @Transactional
    public Long saveBoard(BoardDto dto) {
        User user = userRepository.findById(dto.getUserId());
        if(user == null) {
            throw new IllegalArgumentException("사용자 없음.");
        }

        Board board = BoardDto.toEntity(dto, user);
        return boardRepository.save(board).getId();
    }
    
    //게시글 수정
    @Transactional
    public BoardDto updateBoardById(Long id, BoardDto dto) {
        Board board = boardRepository.findById(id);
        if(board == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }

        board.update(dto.getTitle(), dto.getContent());

        return BoardDto.fromEntity(board);
    }
    
    //게시글 삭제
    @Transactional
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
