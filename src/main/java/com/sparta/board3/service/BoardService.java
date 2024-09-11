package com.sparta.board3.service;

import com.sparta.board3.dto.board.request.BoardSaveRequestDto;
import com.sparta.board3.dto.board.request.BoardUpdateRequestDto;
import com.sparta.board3.dto.board.response.BoardSaveResponseDto;
import com.sparta.board3.dto.board.response.BoardSimpleResponseDto;
import com.sparta.board3.dto.board.response.BoardUpdateResponseDto;
import com.sparta.board3.entity.Board;
import com.sparta.board3.repository.BoardRepository;
import com.sparta.board3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContent());
        Board savedBoard = boardRepository.save(newBoard);
        return new BoardSaveResponseDto(savedBoard.getId(),savedBoard.getTitle(),savedBoard.getContent());
    }


    public Page<BoardSimpleResponseDto> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc(pageable);

        return boards.map(board -> new BoardSimpleResponseDto(
                board.getId(),
                board.getTitle(),
                board.getComments()
        ));
    }

    @Transactional
    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new NullPointerException("보드 못찾음"));
        board.updateBoard(boardUpdateRequestDto.getTitle(),boardUpdateRequestDto.getContent());
        return new BoardUpdateResponseDto(board.getId(),board.getTitle(),board.getContent());
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new NullPointerException("보드 못찾음"));
        boardRepository.delete(board);
    }
}
