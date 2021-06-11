package com.ecommerce.app.jwt;

import com.ecommerce.app.model.response.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;


@AllArgsConstructor
@Configuration
public class JwtTokenGenerator {

	private final JwtConfig jwtConfig;
	@Autowired
	private final SecretKey accessSecretKey;
	@Autowired
	private final SecretKey refreshSecretKey;


	public String generateRefreshToken(String subject, Object claims) {

		return jwtConfig.getRefreshTokenPrefix() + Jwts.builder()
		.setSubject(subject)
		.claim("user", claims)
		.setIssuedAt(new Date())
		.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
		.signWith(accessSecretKey)
		.compact();
	}

	public String generateAccessToken(
	String subject,
	Collection<? extends GrantedAuthority> claims
	) {
		try {
			return jwtConfig.getAccessTokenPrefix() + Jwts.builder()
			.setSubject(subject)
			.claim("authorities", claims)
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
			.signWith(refreshSecretKey)
			.compact();
		} catch (ExpiredJwtException ee) {
			throw new ExpiredJwtException(ee.getHeader(), ee.getClaims(), ee.getMessage());
		} catch (SignatureException se) {
			throw new SignatureException(se.getMessage());
		} catch (MissingClaimException mce) {
			throw new MissingClaimException(mce.getHeader(), mce.getClaims(), mce.getMessage());
		} catch (MalformedJwtException malformedJwtException) {
			throw new MalformedJwtException(malformedJwtException.getMessage());
		} catch (UnsupportedJwtException uje) {
			throw new UnsupportedJwtException(uje.getMessage());
		} catch (JwtException je) {
			throw new JwtException(je.getMessage());
		}
	}

	public String objectMapAccessAndRefreshTokens(
	String accessToken,
	String refreshToken,
	Long id,
	String username,
	Long cartId,
	Long wishListId
	) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JwtResponse jwtResponse = new JwtResponse(
		id,
		username,
		accessToken,
		refreshToken,
		cartId,
		wishListId);
		return objectMapper.writeValueAsString(jwtResponse);
	}
}