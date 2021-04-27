package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Vendor;
import com.ecommerce.app.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class VendorController {
	private final VendorService vendorService;
	
	@PostMapping("/vendors")
	public void saveVendor(@RequestBody Vendor vendor){
		vendorService.createVendor(vendor);
	}
	
	@GetMapping("/vendors")
	public ResponseEntity<Iterable<Vendor>> getVendors(){
		Iterable<Vendor> vendors = vendorService.getVendors();
		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}
	
}
