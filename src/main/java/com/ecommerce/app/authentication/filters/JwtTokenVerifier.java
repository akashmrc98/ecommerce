package com.ecommerce.app.authentication.filters;

import com.ecommerce.app.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

	@Autowired
	public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig){
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

		if(Strings.isEmpty(authorizationHeader) || Strings.isBlank(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
			chain.doFilter(request, response);
			return;
		}

		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token);

			Claims body = claimsJws.getBody();

			String username = body.getSubject();
			Collection<Map<String, String>> authorities = (Collection<Map<String, String>>) body.get("authorities");

			Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = authorities.stream()
			.map( m -> new SimpleGrantedAuthority(m.get("authority")))
			.collect(Collectors.toSet());


			Authentication authentication = new UsernamePasswordAuthenticationToken(
			username,
			null,
			simpleGrantedAuthoritySet
			);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		catch (JwtException e){
			throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
		}

		chain.doFilter(request, response);
	}
}
