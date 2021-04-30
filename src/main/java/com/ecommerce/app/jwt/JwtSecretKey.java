package com.ecommerce.app.jwt;

import lombok.AllArgsConstructor;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Configuration
public class JwtSecretKey {

	@Autowired
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
