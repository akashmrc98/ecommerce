package com.ecommerce.app.authentication.filters;

import java.io.IOException;
import java.io.PrintWriter;
import lombok.SneakyThrows;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.app.jwt.JwtTokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecommerce.app.authentication.AuthRequest;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	public JwtAuthenticationFilter(
		AuthenticationManager authenticationManager,
		JwtTokenGenerator jwtTokenGenerator
	) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenGenerator = jwtTokenGenerator;
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
		PrintWriter tokenAsResponse = response.getWriter();

		String accessToken = jwtTokenGenerator.generateAccessToken(authResult.getName(), authResult.getDetails());
		String refreshToken = jwtTokenGenerator.generateRefreshToken(authResult.getName(), authResult.getAuthorities());
		String tokens = jwtTokenGenerator.objectMapAccessAndRefreshTokens(accessToken, refreshToken);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		tokenAsResponse.write(tokens);
		tokenAsResponse.flush();
	}
}
