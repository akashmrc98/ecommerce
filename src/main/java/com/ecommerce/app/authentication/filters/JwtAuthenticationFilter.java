package com.ecommerce.app.authentication.filters;

import com.ecommerce.app.authentication.AuthRequest;
import com.ecommerce.app.jwt.JwtConfig;
import lombok.SneakyThrows;
import io.jsonwebtoken.Jwts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

	@Autowired
	public JwtAuthenticationFilter(
		AuthenticationManager authenticationManager,
		SecretKey secretKey,
		JwtConfig jwtConfig
	) {
		this.authenticationManager = authenticationManager;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}

	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		AuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
		Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		return authenticationManager.authenticate(authentication);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String token = Jwts.builder()
					.setSubject(authResult.getName())
					.claim("authorities", authResult.getAuthorities())
					.setIssuedAt(new Date())
					.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationInHours())))
					.signWith(secretKey)
					.compact();


		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter tokenAsResponse = response.getWriter();
		tokenAsResponse.write(mapper.writeValueAsString(jwtConfig.getTokenPrefix() + token));
		tokenAsResponse.flush();
	}
}
