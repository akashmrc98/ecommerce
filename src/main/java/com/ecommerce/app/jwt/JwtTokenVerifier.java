package com.ecommerce.app.jwt;

import com.sun.istack.NotNull;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class JwtTokenVerifier {

	@Autowired
	private final SecretKey accessSecretKey;
	private final JwtConfig jwtConfig;

	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}

	public Boolean isAuthorizedToken(@NotNull String accessToken) {
		try {
			return !Strings.isEmpty(accessToken)
			&& !Strings.isBlank(accessToken)
			&& accessToken.startsWith(jwtConfig.getAccessTokenPrefix());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
	}

	public String stripAccessTokenPrefix(@NotNull String accessToken) {
		try {
			return accessToken.replace(jwtConfig.getAccessTokenPrefix(), "");
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
	}

	public String stripRefreshTokenPrefix(@NotNull String refreshToken) {
		try {
			return refreshToken.replace(jwtConfig.getRefreshTokenPrefix(), "");
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
	}

	public Claims getAccessTokenClaims(String accessToken) throws InvalidClaimException {
		try {
			return Jwts.parserBuilder()
			.setSigningKey(accessSecretKey)
			.build()
			.parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException ee) {
			throw new ExpiredJwtException(ee.getHeader(), ee.getClaims(), ee.getMessage());
		} catch (SignatureException se) {
			throw new SignatureException(se.getMessage() + "Signature Issue:" + accessToken);
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

	public String getAccessTokenSubject(String accessToken) {
		try {
			return getAccessTokenClaims(accessToken).getSubject();
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
	}

	public Set<SimpleGrantedAuthority> getAccessTokenGrantedAuthorities(String accessToken) {
		try {
			Collection<Map<String, String>> authorities = (Collection<Map<String, String>>)
			getAccessTokenClaims(accessToken).get("authorities");
			return authorities.stream()
			.map(m -> new SimpleGrantedAuthority(m.get("authority")))
			.collect(Collectors.toSet());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
	}

}
