package com.ecommerce.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Nullable;

@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@Nullable CorsRegistry registry) {
				try {
					assert registry != null;
					registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("PUT", "DELETE", "GET", "POST")
					.allowedHeaders("*")
					.exposedHeaders("*");
				} catch (NullPointerException npe){
					npe.printStackTrace();
				}
			}
		};
	}
}
