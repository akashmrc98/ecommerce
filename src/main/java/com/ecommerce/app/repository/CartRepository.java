package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
