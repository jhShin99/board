package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 1️⃣ 게시글 목록
     */
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    /**
     * 2️⃣ 게시글 상세 (조회수 증가 포함)
     */
    @Transactional
    public Board findById(Long id) {

        Board board = boardMapper.findById(id);

        if (board == null) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        boardMapper.increaseViewCount(id);

        return board;
    }

    /**
     * 3️⃣ 게시글 등록
     */
    @Transactional
    public void create(Board board, Long loginMemberId) {

        board.setWriterId(loginMemberId);
        board.setViewCount(0);

        boardMapper.insert(board);
    }

    /**
     * 4️⃣ 게시글 수정
     */
    @Transactional
    public void update(Long boardId,
                       String title,
                       String content,
                       Member loginMember) {

        Board board = boardMapper.findById(boardId);

        if (board == null) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        // 🔥 작성자 or 관리자만 가능
        if (!board.getWriterId().equals(loginMember.getId())
                && !"ADMIN".equals(loginMember.getRole())) {

            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        board.setTitle(title);
        board.setContent(content);

        boardMapper.update(board);
    }

    /**
     * 5️⃣ 게시글 삭제
     */
    @Transactional
    public void delete(Long boardId,
                       Member loginMember) {

        Board board = boardMapper.findById(boardId);

        if (board == null) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        if (!board.getWriterId().equals(loginMember.getId())
                && !"ADMIN".equals(loginMember.getRole())) {

            throw new IllegalStateException("삭제 권한이 없습니다.");
        }

        boardMapper.delete(boardId);
    }
}
