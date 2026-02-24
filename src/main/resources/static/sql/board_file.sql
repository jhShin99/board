CREATE TABLE board_file
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id      BIGINT       NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    stored_name   VARCHAR(255) NOT NULL,
    file_path     VARCHAR(500) NOT NULL,
    file_size     BIGINT,
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_file_board
        FOREIGN KEY (board_id)
            REFERENCES board (id)
            ON DELETE CASCADE
);