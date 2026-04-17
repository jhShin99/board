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

    public Member findByUsername(String username) {
        return memberMapper.findByUsername(username);
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

    @Transactional
    public void changePassword(Long memberId,
                               String currentPassword,
                               String newPassword) {

        Member member = memberMapper.findById(memberId);

        if (member == null) {
            throw new IllegalArgumentException("회원 없음");
        }

        // 1️⃣ 현재 비밀번호 확인
        if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호 불일치");
        }

        // 2️⃣ 새 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        // 3️⃣ 비밀번호 + rememberToken 제거
        memberMapper.updatePassword(memberId, encodedPassword);
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
