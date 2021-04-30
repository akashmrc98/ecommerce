package com.ecommerce.app.authentication;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserDao {
	UserDetails selectUserByUsername(String username);
}
