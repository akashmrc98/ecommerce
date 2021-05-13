package com.ecommerce.app.authentication.filters;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.app.jwt.JwtTokenVerifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain chain
	) throws ServletException, IOException {

		String accessToken = jwtTokenVerifier.stripAccessTokenPrefix(request.getHeader(jwtTokenVerifier.getAuthorizationHeader()));

		if(jwtTokenVerifier.isAuthorizedToken(accessToken)){
			chain.doFilter(request, response);
			return;
		}

		String username = jwtTokenVerifier.getAccessTokenSubject(accessToken);
		var auth = jwtTokenVerifier.getAccessTokenGrantedAuthorities(accessToken);

		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, auth);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
