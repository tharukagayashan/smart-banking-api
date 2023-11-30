package com.projects.smartbankingapi.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    private final JWTIntercepter jwtIntercepter;

    public WebMvcConfig(JWTIntercepter jwtIntercepter) {
        this.jwtIntercepter = jwtIntercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtIntercepter)
                .excludePathPatterns(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**"
                )
                .addPathPatterns(
                        "/**"
                );
    }
}
