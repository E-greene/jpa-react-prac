package com.example.jpaprac.application.service.board;

import com.example.jpaprac.domain.common.exception.BoardNotFoundException;
import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.user.UserRepository;
import com.example.jpaprac.presentation.dto.board.*;
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
    public List<BoardApplicationDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardApplicationDto::fromEntity)
                .collect(Collectors.toList());
    }

    //전체 게시글 조회 (작성자포함)
    @Transactional(readOnly = true)
    public List<BoardApplicationDto> findAllWithUser() {
        return boardRepository.findAllWithUser().stream()
                .map(BoardApplicationDto::fromEntity)
                .collect(Collectors.toList());
    }

    //사용자 ID로 게시글 조회
    @Transactional
    public List<BoardApplicationDto> findBoardsByUserId(Long userId) {
        List<Board> boards = boardRepository.findByUserId(userId);
        return boards.stream()
                .map(BoardApplicationDto::fromEntity)
                .collect(Collectors.toList());
    }

    //게시글 단건조회
    @Transactional(readOnly = true)
    public BoardApplicationDto findById(Long boardId) {
        Board board = boardRepository.findById(boardId);
        if(board == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }
        return BoardApplicationDto.fromEntity(board);
    }

    //게시글 단건조회 (작성자포함)
    @Transactional(readOnly = true)
    public BoardApplicationDto findByIdWithUser(Long boardId) {
        Board board = boardRepository.findByIdWithUser(boardId);
        if(board == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }
        return BoardApplicationDto.fromEntity(board);
    }

    //게시글 생성
    @Transactional
    public Long saveBoard(CreateBoardCommand createBoardCommand) {
        User user = userRepository.findById(createBoardCommand.getUserId());
        if(user == null) {
            throw new IllegalArgumentException("사용자 없음.");
        }

        Board board = Board.create(user, createBoardCommand);
        return boardRepository.save(board).getId();
    }

    //게시글 수정
    @Transactional
    public BoardApplicationDto updateBoardById(Long boardId, UpdateBoardCommand updateBoardCommand) {
        Board board = boardRepository.findById(boardId);
        if(board == null || "Y".equals(board.getDeleteYN())) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }

        board.update(updateBoardCommand.getTitle(), updateBoardCommand.getContent());

        return BoardApplicationDto.fromEntity(board);
    }

    //게시글 삭제
    @Transactional
    public void deleteBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId);
        if(board == null || "Y".equals(board.getDeleteYN())) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }
        board.softDelete();
    }
}
