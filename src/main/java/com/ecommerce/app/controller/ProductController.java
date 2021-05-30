package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ecommerce/products")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Iterable<Product>> getProducts() {
		return new ResponseEntity<Iterable<Product>>(productService.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long id){
		return new ResponseEntity<Product>(productService.getProduct(id), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
	}
}
