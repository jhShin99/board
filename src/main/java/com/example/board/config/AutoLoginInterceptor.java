package com.example.board.config;

import com.example.board.domain.Member;
import com.example.board.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        HttpSession session = request.getSession();

        if (session.getAttribute("loginMember") != null) {
            return true;
        }

        if (request.getCookies() == null) {
            return true;
        }

        for (Cookie cookie : request.getCookies()) {

            if (cookie.getName().equals("rememberToken")) {

                Member member = memberService.findByRememberToken(cookie.getValue());

                if (member != null) {
                    System.out.println("자동 로그인 성공: " + member.getUsername());
                    session.setAttribute("loginMember", member);
                }
            }
        }

        return true;
    }
}
