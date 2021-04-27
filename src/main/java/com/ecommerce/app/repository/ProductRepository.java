package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
