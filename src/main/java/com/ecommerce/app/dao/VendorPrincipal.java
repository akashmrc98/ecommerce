package com.ecommerce.app.dao;

import com.ecommerce.app.domain.Vendor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class VendorPrincipal implements UserDetails {

	private Vendor vendor;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays
		.stream(this.vendor.getAuthorities())
		.map(SimpleGrantedAuthority::new)
		.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return vendor.getPassword();
	}

	@Override
	public String getUsername() {
		return vendor.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return vendor.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return vendor.isEnabled();
	}
}
