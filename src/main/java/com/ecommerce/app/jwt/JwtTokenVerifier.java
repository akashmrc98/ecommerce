package com.ecommerce.app.jwt;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class JwtTokenVerifier {

	@Autowired
	private final SecretKey accessSecretKey;

	@Autowired
	private final JwtConfig jwtConfig;

	public String getAuthorizationHeader(){
		return HttpHeaders.AUTHORIZATION;
	}

	public Boolean isAuthorizedToken(String accessToken){
		return !Strings.isEmpty(accessToken) && !Strings.isBlank(accessToken) && accessToken.startsWith(jwtConfig.getAccessTokenPrefix());
	}

	public String stripAccessTokenPrefix(String accessToken){
		return accessToken.replace(jwtConfig.getAccessTokenPrefix(), "");
	}

	public Claims getClaims(String accessToken){
		try {
		 return Jwts.parserBuilder()
		.setSigningKey(accessSecretKey)
		.build()
		.parseClaimsJws(accessToken).getBody();
		}
		catch (JwtException e){
			throw new IllegalStateException(String.format("Token %s cannot be trusted", accessToken));
		}
	}

	public String getSubject(String accessToken){
		return getClaims(accessToken).getSubject();
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities(String accessToken){
			Collection<Map<String, String>> authorities = (Collection<Map<String, String>>) getClaims(accessToken).get("authorities");
			return authorities.stream().map( m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
	}

}
