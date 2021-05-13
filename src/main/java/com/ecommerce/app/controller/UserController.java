package com.ecommerce.app.controller;

import com.ecommerce.app.domain.User;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}
