package com.example.board.mapper;

import com.example.board.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface MemberMapper {

    void insert(Member member);

    Member findById(Long id);

    Member findByUsername(String username);

    void updateRememberToken(@Param("memberId") Long memberId,
                             @Param("token") String token,
                             @Param("expiry") LocalDateTime expiry);

    Member findByRememberToken(String token);

    void clearRememberToken(@Param("memberId") Long memberId);
}
