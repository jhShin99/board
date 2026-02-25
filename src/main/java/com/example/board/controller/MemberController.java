package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(Member member) {
        memberService.join(member);
        return "redirect:/member/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(String username,
                        String password,
                        String rememberMe,
                        HttpServletResponse response,
                        HttpSession session) {

        Member loginMember = memberService.login(username, password);

        if (loginMember == null) {
            return "redirect:/member/login?error=true";
        }

        // 로그인 유지 체크했을 경우
        if (rememberMe != null) {

            String token = UUID.randomUUID().toString();

            memberService.updateRememberToken(
                    loginMember.getId(),
                    token,
                    LocalDateTime.now().plusDays(7)
            );

            Cookie cookie = new Cookie("rememberToken", token);
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }


        session.setAttribute("loginMember", loginMember);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {

        Member loginMember =
                (Member) session.getAttribute("loginMember");

        if (loginMember != null) {
            memberService.clearRememberToken(loginMember.getId());
        }

        session.invalidate();

        Cookie cookie = new Cookie("rememberToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
