package com.sparta.board3.controller;

import com.sparta.board3.dto.comment.request.CommentSaveRequestDto;
import com.sparta.board3.dto.comment.response.CommentResponseDto;
import com.sparta.board3.dto.comment.response.CommentSaveResponseDto;
import com.sparta.board3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@PathVariable long boardId,
                                                              @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return ResponseEntity.ok(commentService.saveComment(boardId, commentSaveRequestDto));
    }

    @GetMapping("/boards/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(){
        return ResponseEntity.ok(commentService.getComments());
    }
}
