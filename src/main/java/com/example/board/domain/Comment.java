package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Comment {

    private Long id;

    private Long boardId;

    private Long writerId;

    private Long parentId;   // null이면 일반 댓글

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 계층 구조용
    private List<Comment> children = new ArrayList<>();
}
