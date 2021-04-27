package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce")
public class AddressController {
	private final AddressService addressService;
	
	@PostMapping("/address")
	public ResponseEntity<Address> createAddress(@RequestBody Address address){
		Address savedAddress = addressService.createAddress(address);
		return new ResponseEntity<Address>(savedAddress, HttpStatus.CREATED);
	}
	
	@GetMapping("/address")
	public ResponseEntity<Iterable<Address>> getAddress(){
		Iterable<Address> addresses = addressService.getAddress();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
	
	@DeleteMapping("/address")
	public void removeAddress(@RequestParam("id") Long id){
		addressService.removeAddress(id);
	}
}
