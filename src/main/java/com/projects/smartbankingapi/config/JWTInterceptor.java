package com.projects.smartbankingapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("{}", request.getRequestURI());
        String auth = request.getHeader("authorization");
        JWTUtils.TokenVerification(auth);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}