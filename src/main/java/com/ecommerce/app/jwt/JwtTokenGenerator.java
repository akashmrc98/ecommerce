package com.ecommerce.app.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class JwtTokenGenerator {

	private String refreshToken;
	private final SecretKey secretKey = Keys.hmacShaKeyFor("@COOLER@COOLER@COOLER@COOLER@COOLER@COOLER@COOLER@".getBytes(StandardCharsets.UTF_8));
	private final long refreshTokenExpirationInDays = 2L;

	public String generateRefreshToken(){
		return Jwts.builder()
		.setSubject("akash")
		.claim("refreshId", UUID.randomUUID())
		.signWith(secretKey)
		.setIssuedAt(new Date())
		.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(refreshTokenExpirationInDays)))
		.compact();
	}

	public boolean isRefreshTokenExpired(){
		return false;
	}


}
