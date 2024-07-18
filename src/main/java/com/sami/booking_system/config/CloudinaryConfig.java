package com.sami.booking_system.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
//    @Value("${cloudinary.cloud_name:default}")
//    private String cloudName;
//
//    @Value("${cloudinary.api_key:default}")
//    private String apiKey;
//
//    @Value("${cloudinary.api_secret:default}")
//    private String apiSecret;
//
//    @Bean
//    public Cloudinary cloudinary() {
//        return new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", cloudName,
//                "api_key", apiKey,
//                "api_secret", apiSecret));
//    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "dvqolnmnp");
        config.put("api_key", "585679249344257");
        config.put("api_secret", "sc-90kE8RbhkFZHyAUmbj8C7N8c");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
