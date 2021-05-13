package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce")
public class AddressController {
	private final AddressService addressService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/address")
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		Address savedAddress = addressService.createAddress(address);
		return new ResponseEntity<Address>(savedAddress, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/address")
	public ResponseEntity<Iterable<Address>> getAddress() {
		Iterable<Address> addresses = addressService.getAddress();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping("/address")
	public void removeAddress(@RequestParam("id") Long id) {
		addressService.removeAddress(id);
	}
}
