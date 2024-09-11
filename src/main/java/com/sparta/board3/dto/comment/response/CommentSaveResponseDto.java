package com.sparta.board3.dto.comment.response;

import lombok.Getter;

@Getter
public class CommentSaveResponseDto {
    private final Long id;
    private final String content;

    public CommentSaveResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
