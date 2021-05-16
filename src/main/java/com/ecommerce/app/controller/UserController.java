package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ecommerce")
public class UserController {
	private final UserService userService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/users")
	public void saveUser(@RequestBody User user) {
		userService.createUser(user);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<Iterable<User>> getUsers() {
		Iterable<User> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/purchases/{username}")
	public ResponseEntity<Iterable<Purchase>> getPurchases(@PathVariable("username") String username) {
		Iterable<Purchase> purchases = userService.getPurchases(username);
		return new ResponseEntity<Iterable<Purchase>>(purchases, HttpStatus.OK);
	}

	@GetMapping("/address/{username}")
	public ResponseEntity<Iterable<Address>> getAddresses(@PathVariable("username") String username) {
		Iterable<Address> addresses = userService.getAddresses(username);
		return new ResponseEntity<Iterable<Address>>(addresses, HttpStatus.OK);
	}

	@PostMapping("/address/{username}")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveAddress(
	@PathVariable("username") String username,
	@RequestBody List<Address> address) {
		userService.saveAddresses(username, address);
	}

}
