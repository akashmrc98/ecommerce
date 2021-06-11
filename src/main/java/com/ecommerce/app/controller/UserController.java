package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.dto.PurchasesDto;
import com.ecommerce.app.service.AddressService;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ecommerce/users")
public class UserController {
	private final UserService userService;
	private final AddressService addressService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping
	public void saveUser(@RequestBody User user) {
		userService.createUser(user);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<Iterable<User>> getUsers() {
		Iterable<User> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{username}/purchases")
	public ResponseEntity<Iterable<PurchasesDto>> getPurchasesByUserID(@PathVariable("username") String username) {
		Iterable<PurchasesDto> purchases = userService.getPurchasesByUserID(username);
		return new ResponseEntity<Iterable<PurchasesDto>>(purchases, HttpStatus.OK);
	}

	@GetMapping("/{username}/address")
	public ResponseEntity<Iterable<Address>> getAddressesByUserID(@PathVariable("username") String username) {
		Iterable<Address> addresses = userService.getAddressesByUserID(username);
		return new ResponseEntity<Iterable<Address>>(addresses, HttpStatus.OK);
	}

	@PostMapping("/{username}/address")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveAddress(
	@PathVariable("username") String username,
	@RequestBody Address address) {
		addressService.createAddress(address);
		userService.saveAddressesByUsername(username, address);
	}

}
