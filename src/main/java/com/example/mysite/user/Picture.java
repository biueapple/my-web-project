package com.example.mysite.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Picture implements WebMvcConfigurer {
	
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/upload/**")   // 웹에서 접근할 URL 패턴
            .addResourceLocations("file:///D:/my-web-project/upload/"); // 실제 파일 경로
}
}
