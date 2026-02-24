CREATE TABLE board
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_type_id BIGINT       NOT NULL,
    writer_id     BIGINT       NOT NULL,
    title         VARCHAR(200) NOT NULL,
    content       TEXT         NOT NULL,
    view_count    INT          NOT NULL DEFAULT 0,
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_board_type
        FOREIGN KEY (board_type_id)
            REFERENCES board_type (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_board_writer
        FOREIGN KEY (writer_id)
            REFERENCES member (id)
            ON DELETE CASCADE
);