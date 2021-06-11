package com.ecommerce.app.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
	private String accessSecretKey;
	private String accessTokenPrefix;
	private Integer accessTokenExpirationInHours;

	private String refreshSecretKey;
	private String refreshTokenPrefix;
	private Integer refreshTokenExpirationInDays;
}
