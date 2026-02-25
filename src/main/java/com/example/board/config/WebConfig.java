package com.example.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/board/write, /board/edit/**", "/admin/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/board/write", "/board/edit/**", "/admin/**")
                .excludePathPatterns(
                        "/",
                        "/member/login",
                        "/member/join",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                );
    }
}
