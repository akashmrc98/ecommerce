package com.ecommerce.app.jwt;

import java.util.*;
import java.io.IOException;
import java.time.LocalDate;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class JwtTokenGenerator {

	@Autowired
	private final SecretKey accessSecretKey;
	@Autowired

	private final SecretKey refreshSecretKey;
	private final JwtConfig jwtConfig;

	public String generateRefreshToken(String subject, Object claims){
		return jwtConfig.getRefreshTokenPrefix() + Jwts.builder()
		.setSubject(subject)
		.claim("refresh", claims)
		.signWith(refreshSecretKey)
		.setIssuedAt(new Date())
		.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getRefreshTokenExpirationInDays())))
		.compact();
	}

	public String generateAccessToken(String subject, Object claims){
		return jwtConfig.getRefreshTokenPrefix() + Jwts.builder()
		.setSubject(subject)
		.claim("access", claims)
		.signWith(accessSecretKey)
		.setIssuedAt(new Date())
		.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getRefreshTokenExpirationInDays())))
		.compact();
	}

	public String objectMapAccessAndRefreshTokens(String accessToken, String refreshToken) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken);
		return objectMapper.writeValueAsString(jwtResponse);
	}
}