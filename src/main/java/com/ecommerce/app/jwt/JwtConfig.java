package com.ecommerce.app.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "application.jwt")
@Configuration
public class JwtConfig {

	private String accessSecretKey;
	private String accessTokenPrefix;
	private Integer accessTokenExpirationInHours;

	private String refreshSecretKey;
	private String refreshTokenPrefix;
	private Integer refr

	private String
	public String getAuthorizationHeader(){
		return HttpHeaders.AUTHORIZATION;
	}
}
