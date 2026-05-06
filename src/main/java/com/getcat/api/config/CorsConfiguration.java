package com.getcat.api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Configure the URL pattern to apply CORS to, in this case all URLs under "/api"
                .allowedOrigins("http://localhost:4200" , "http://localhost") // Set the allowed origin to "http://localhost:4200", you can add more origins if needed
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Set the allowed HTTP methods
                .allowedHeaders("*") // Set the allowed headers, you can specify headers explicitly if needed
                .allowCredentials(true); // Set to true if you want to allow credentials (e.g., cookies) to be sent in CORS requests
    }
}
