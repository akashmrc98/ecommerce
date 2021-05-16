package com.ecommerce.app.service;

import com.ecommerce.app.authentication.AuthUser;
import com.ecommerce.app.authentication.AuthUserDao;
import com.ecommerce.app.domain.*;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.repository.WishListRepository;
import com.ecommerce.app.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements AuthUserDao {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final WishListRepository wishListRepository;

	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	public void createUser(User user) {
		Cart cart = new Cart();
		WishList wishList = new WishList();
		cartRepository.save(cart);
		wishListRepository.save(wishList);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setEnabled(true);
		user.setCredentialsNonExpired(true);
		user.setCart(cart);
		user.setWishList(wishList);
		userRepository.save(user);
	}

	public void createOrderToUser(String username, Purchase purchase) {
		User user = userRepository.findByUsername(username);
		List<Purchase> purchases = user.getPurchases();
		purchases.add(purchase);
		user.setPurchases(purchases);
		userRepository.save(user);
	}

	public Iterable<Address> getAddresses(String username) {
		return userRepository.findByUsername(username).getAddress();
	}

	public void saveAddresses(String username, List<Address> addresses) {
		User user = userRepository.findByUsername(username);
		List<Address> currentAddress = user.getAddress();
		currentAddress.addAll(addresses);
		user.setAddress(currentAddress);
		userRepository.save(user);
	}

	public Iterable<Purchase> getPurchases(String username) {
		return userRepository.findByUsername(username).getPurchases();
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
