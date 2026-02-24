package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Board {

    private Long id;

    private Long boardTypeId;

    private Long writerId;

    private String title;

    private String content;

    private int viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
