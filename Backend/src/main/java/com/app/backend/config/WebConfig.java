package com.app.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                .allowedHeaders("*")
                .allowedCredentials(true);
    }
}
