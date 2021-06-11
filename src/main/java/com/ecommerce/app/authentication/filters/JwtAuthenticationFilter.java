package com.ecommerce.app.authentication.filters;

import com.ecommerce.app.authentication.AuthRequest;
import com.ecommerce.app.domain.Session;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.jwt.JwtTokenGenerator;
import com.ecommerce.app.repository.SessionRepository;
import com.ecommerce.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final SessionRepository sessionRepository;
	private final UserRepository userRepository;

	@Autowired
	public JwtAuthenticationFilter(
	AuthenticationManager authenticationManager,
	JwtTokenGenerator jwtTokenGenerator,
	SessionRepository sessionRepository,
	UserRepository userRepository
	) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenGenerator = jwtTokenGenerator;
		this.sessionRepository = sessionRepository;
		this.userRepository = userRepository;
	}

	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(
	HttpServletRequest request,
	HttpServletResponse response
	) throws AuthenticationException {
		AuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(),
		AuthRequest.class);
		Authentication authentication =
		new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
		authRequest.getPassword());
		return authenticationManager.authenticate(authentication);
	}

	@Override
	protected void successfulAuthentication(
	HttpServletRequest request,
	HttpServletResponse response,
	FilterChain chain,
	Authentication authResult
	) throws IOException, ServletException {

		String accessToken = jwtTokenGenerator
		.generateAccessToken(authResult.getName(), authResult.getAuthorities());

		String refreshToken = jwtTokenGenerator
		.generateRefreshToken(authResult.getName(), authResult.getDetails());

		User user = userRepository.findByUsername(authResult.getName());

		Session session = new Session();
		session.setAccessToken(accessToken);
		session.setRefreshToken(refreshToken);
		session.setUser(user);

		sessionRepository.save(session);

		String tokens = jwtTokenGenerator
		.objectMapAccessAndRefreshTokens(
		accessToken,
		refreshToken,
		session.getId(),
		user.getUsername(),
		session.getUser().getCart().getId(),
		session.getUser().getWishList().getId()
		);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter tokenAsResponse = response.getWriter();
		tokenAsResponse.write(tokens);
		tokenAsResponse.flush();
	}
}