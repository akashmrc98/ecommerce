package com.ecommerce.app.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@AllArgsConstructor
public class JwtSecretKey {

	private final JwtConfig jwtConfig;

	@Bean
	public SecretKey accessSecretKey(){
		return Keys.hmacShaKeyFor(jwtConfig.getAccessSecretKey().getBytes(StandardCharsets.UTF_8));
	}

	@Bean
	public SecretKey refreshSecretKey(){
		return Keys.hmacShaKeyFor(jwtConfig.getRefreshSecretKey().getBytes(StandardCharsets.UTF_8));
	}

}
