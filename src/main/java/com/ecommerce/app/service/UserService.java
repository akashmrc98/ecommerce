package com.ecommerce.app.service;

import com.ecommerce.app.authentication.AuthUser;
import com.ecommerce.app.authentication.AuthUserDao;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecommerce.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UserService implements AuthUserDao {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public Iterable<User> getUsers(){
		return userRepository.findAll();
	}
	
	public void createUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public User findByUsername(String username)  {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails selectUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		UserRole userRole = UserRole.valueOf(user.getUserRole());
		return new AuthUser(
		userRole.getGrantedAuthorities(),
		user.getUsername(),
		user.getPassword(),
		user.isAccountNonExpired(),
		user.isAccountNonLocked(),
		user.isCredentialsNonExpired(),
		user.isEnabled()
		);
	}

}
