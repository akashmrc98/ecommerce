package com.ecommerce.app.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import com.ecommerce.app.domain.Vendor;
import org.springframework.stereotype.Service;

import com.ecommerce.app.repository.VendorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Service
public class VendorService  {
	
	private final PasswordEncoder passwordEncoder;
	private final VendorRepository vendorRepository;
	
	public Iterable<Vendor> getVendors(){
		return vendorRepository.findAll();
	}
	
	public void createVendor(Vendor vendor){
		vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
		vendorRepository.save(vendor);
	}
	
	public Optional<Vendor> findByUsername(String username)  {
		return vendorRepository.findByUsername(username);
	}
}
