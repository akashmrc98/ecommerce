package com.ecommerce.app.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
	private Long id;
	private String username;
	private String accessToken;
	private String refreshToken;
	private Long cartId;
}
