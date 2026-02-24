package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MemberMapper memberMapper;

    @GetMapping("/member/{id}")
    public Member find(@PathVariable Long id) {
        return memberMapper.findById(id);
    }
}
