package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
