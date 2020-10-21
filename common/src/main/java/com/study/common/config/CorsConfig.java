//package com.study.common.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 跨域配置.
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                // 允许任何域名使用
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH")
//                // 允许任何头
//                .allowedHeaders("*")
//                .maxAge(3600);
//    }
//
//}
