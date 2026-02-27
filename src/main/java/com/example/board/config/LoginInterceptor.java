package com.example.board.config;

import com.example.board.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("loginMember") == null) {

            response.sendRedirect("/member/login");
            return false;
        }

        Member loginMember = (Member) session.getAttribute("loginMember");

        if (request.getRequestURI().startsWith("/admin")) {

            if (!"ADMIN".equals(loginMember.getRole())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }
}

