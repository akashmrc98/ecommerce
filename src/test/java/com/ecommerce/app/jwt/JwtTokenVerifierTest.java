package com.ecommerce.app.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;


@AllArgsConstructor
@Getter
@Setter
class JwtTokenVerifierTest {
	@Test
	void getClaims() {
		String SECRET_KEY = "Secure@AntecSecure@AntecSecure@AntecSecure@AntecSecure@AntecSecure" +
		"@AntecSecure@Antec";

		String TOKEN = "eyJhbGciOiJIUzUxMiJ9" +
		".eyJzdWIiOiJha2FzaCIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJ1c2VyOndyaXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOnJlYWQifSx7ImF1dGhvcml0eSI6ImFkZHJlc3M6d3JpdGUifSx7ImF1dGhvcml0eSI6ImFkZHJlc3M6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjE5ODU0OTQyLCJleHAiOjE2MTk5ODAyMDB9.EtGfmIzXGKt_eFNqTvnxWdIvEulAgs_WHYj4lRdrMop5xm9eaHGS3lN_D5EqFEDJIMrZS7aCgw9f5p8P27OQFQ";

		Claims claims = Jwts.parserBuilder()
		.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
		.build()
		.parseClaimsJws(TOKEN).getBody();

		Assertions.assertEquals(claims.getSubject(), "akash");
	}

	@Test
	void getGrantedAuthorities() {
	}
}