package com.ecommerce.app.authentication.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.app.jwt.JwtTokenVerifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenVerifier jwtTokenVerifier;

	@Autowired
	public JwtTokenFilter(JwtTokenVerifier jwtTokenVerifier){
		this.jwtTokenVerifier = jwtTokenVerifier;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		String accessToken = request.getHeader(jwtTokenVerifier.getAuthorizationHeader());

		if(jwtTokenVerifier.isAuthorizedToken(accessToken)){
			chain.doFilter(request, response);
			return;
		}

		Authentication authentication =
		new UsernamePasswordAuthenticationToken(
			jwtTokenVerifier.getSubject(accessToken),
			null,
			jwtTokenVerifier.getGrantedAuthorities(accessToken)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
