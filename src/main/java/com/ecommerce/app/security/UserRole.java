package com.ecommerce.app.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum UserRole {
	USER
	(Sets.newHashSet(
	UserPermission.ADDRESS_WRITE,
	UserPermission.ADDRESS_READ
	)),

	ADMIN
	(Sets.newHashSet(
	UserPermission.USER_WRITE,
	UserPermission.USER_READ,
	UserPermission.ADDRESS_WRITE,
	UserPermission.ADDRESS_READ
	)),

	GUEST
	(Sets.newHashSet());

	private final Set<UserPermission> userPermissions;

	public Set<GrantedAuthority> getGrantedAuthorities() {

		Set<GrantedAuthority> permission =
		getUserPermissions()
		.stream()
		.map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
		.collect(Collectors.toSet());

		permission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permission;
	}


}

