package com.ecommerce.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
	private Long id;
	private String username;
	private String accessToken;
	private String refreshToken;
	private Long cartId;
	private Long wishListId;
}
