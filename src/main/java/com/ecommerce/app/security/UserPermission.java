package com.ecommerce.app.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserPermission {
	USER_WRITE("user:write"),
	USER_READ("user:read"),
	ADDRESS_WRITE("address:write"),
	ADDRESS_READ("address:read");

	private final String permission;
}
