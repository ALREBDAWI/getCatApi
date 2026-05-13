package com.getcat.api.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to set up the Cloudinary service for image management
 */
@Configuration
public class CloudinaryConfig {
    // Injection of Cloudinary credentials from .env
    @Value(("${CLOUDINARY_CLOUD_NAME}"))
    String cloudName;

    @Value("${CLOUDINARY_API_KEY}")
    String apiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        // return a cloudinary instance to be ready for use
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }
}
