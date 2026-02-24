package com.example.board.mapper;

import com.example.board.domain.Member;

public interface MemberMapper {

    Member findById(Long id);
    void insert(Member member);
}
