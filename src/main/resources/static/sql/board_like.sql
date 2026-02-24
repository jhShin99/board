CREATE TABLE board_like
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id   BIGINT   NOT NULL,
    member_id  BIGINT   NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_like_board
        FOREIGN KEY (board_id)
            REFERENCES board (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_like_member
        FOREIGN KEY (member_id)
            REFERENCES member (id)
            ON DELETE CASCADE,

    CONSTRAINT uq_board_like UNIQUE (board_id, member_id)
);