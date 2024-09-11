package com.sparta.board3.service;

import com.sparta.board3.dto.board.request.BoardSaveRequestDto;
import com.sparta.board3.dto.board.response.BoardSaveResponseDto;
import com.sparta.board3.entity.Board;
import com.sparta.board3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContent());
        Board savedBoard = boardRepository.save(newBoard);
        return new BoardSaveResponseDto(savedBoard.getId(),savedBoard.getTitle(),savedBoard.getContent());
    }
}
