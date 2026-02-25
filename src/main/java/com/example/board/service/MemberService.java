package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insert(member);
    }

    public Member login(String username, String password) {

        Member findMember = memberMapper.findByUsername(username);

        if (findMember == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            return null;
        }

        return findMember;
    }

    public Member findByRememberToken(String token) {

        if (token == null || token.isEmpty()) {
            return null;
        }

        return memberMapper.findByRememberToken(token);
    }

    @Transactional
    public void updateRememberToken(Long memberId,
                                    String token,
                                    LocalDateTime expiry) {

        memberMapper.updateRememberToken(memberId, token, expiry);
    }

    @Transactional
    public void clearRememberToken(Long memberId) {
        memberMapper.clearRememberToken(memberId);
    }
}
