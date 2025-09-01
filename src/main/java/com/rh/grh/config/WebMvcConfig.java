/*package com.rh.grh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Exemple : pour servir les fichiers uploadés (CV, contrats...)
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
*/