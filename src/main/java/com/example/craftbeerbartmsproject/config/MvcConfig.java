package com.example.craftbeerbartmsproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/404").setViewName("/error/404");
        registry.addViewController("/login").setViewName("/user/login");
        registry.addViewController("/").setViewName("/user/landing");
        registry.addViewController("/terms").setViewName("/moderator/termsOfService");
        registry.addViewController("/about_us").setViewName("/user/aboutUs");
    }
}
