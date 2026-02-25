package com.example.board.mapper;

import com.example.board.domain.Board;

import java.util.List;

public interface BoardMapper {

    /**
     * 게시글 목록 조회
     */
    List<Board> findAll();

    /**
     * 게시글 단건 조회
     */
    Board findById(Long id);

    /**
     * 게시글 등록
     */
    void insert(Board board);

    /**
     * 게시글 수정
     */
    void update(Board board);

    /**
     * 게시글 삭제
     */
    void delete(Long id);

    /**
     * 조회수 증가
     */
    void increaseViewCount(Long id);
}
