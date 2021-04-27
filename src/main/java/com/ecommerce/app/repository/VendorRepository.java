package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VendorRepository extends CrudRepository<Vendor, Long> {
	public Vendor findByEmail(String email);
	public Optional<Vendor> findByUsername(String username);
}
