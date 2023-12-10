//package com.projects.smartbankingapi.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final JWTInterceptor jwtInterceptor;
//
//    public WebMvcConfig(JWTInterceptor jwtInterceptor) {
//        this.jwtInterceptor = jwtInterceptor;
//    }
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//                .excludePathPatterns(
//                        "/auth/**",
//                        "/swagger-ui/**",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html",
//                        "/v2/api-docs",
//                        "/webjars/**"
//                )
//                .addPathPatterns(
//                        "/**"
//                );
//    }
//}
