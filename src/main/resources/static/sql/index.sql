CREATE INDEX idx_board_type ON board(board_type_id);
CREATE INDEX idx_board_created ON board(created_at);
CREATE INDEX idx_comment_board ON comment(board_id);
CREATE INDEX idx_comment_parent ON comment(parent_id);
CREATE INDEX idx_like_board ON board_like(board_id);