package com.ecommerce.app.authentication;

import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class AuthUser implements UserDetails {
	private final Collection<? extends GrantedAuthority> authorities;
	private final String username;
	private final String password;
	private final boolean isAccountNonExpired;
	private final boolean isAccountNonLocked;
	private final boolean isCredentialsNonExpired;
	private final boolean isEnabled;
}
