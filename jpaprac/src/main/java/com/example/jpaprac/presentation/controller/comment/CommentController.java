//package com.example.jpaprac.presentation.controller.comment;
//
//import com.example.jpaprac.application.service.comment.CommentService;
//import com.example.jpaprac.presentation.dto.comment.CommentDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @Autowired
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    //댓글 생성
//    @PostMapping
//    public Long createComment(@RequestBody CommentDto dto) {
//        return commentService.createComment(dto);
//    }
//
//    //게시글 댓글 조회
//    @GetMapping("/board/boardId")
//    public List<CommentDto>  getCommentsByBoard(@PathVariable Long boardId) {
//        return commentService.getCommentsByBoard(boardId);
//    }
//
//    //댓글 수정
//    @PutMapping("/{commentId}")
//    public void updateComment(@PathVariable Long commentId, @RequestBody CommentDto dto) {
//        commentService.updateComment(commentId, dto.getContent());
//    }
//
//    //댓글 삭제
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable Long commentId) {
//        commentService.deleteComment(commentId);
//    }
//}
