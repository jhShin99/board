CREATE TABLE comment
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id   BIGINT   NOT NULL,
    writer_id  BIGINT   NOT NULL,
    parent_id  BIGINT NULL,
    content    TEXT     NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_comment_board
        FOREIGN KEY (board_id)
            REFERENCES board (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_comment_writer
        FOREIGN KEY (writer_id)
            REFERENCES member (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_comment_parent
        FOREIGN KEY (parent_id)
            REFERENCES comment (id)
            ON DELETE CASCADE
);