package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.dto.ProductReviewDto;
import com.ecommerce.app.dto.ProductsDto;
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
	public ResponseEntity<Iterable<ProductsDto>> getProducts(
	@RequestParam(defaultValue = "0", value = "pageNo") Integer pageNo,
	@RequestParam(defaultValue = "4", value = "pageSize") Integer pageSize,
	@RequestParam(defaultValue = "id", value = "sortBy") String sortBy) {
		return new ResponseEntity<Iterable<ProductsDto>>(productService.getProducts(pageNo,
		pageSize, sortBy),
		HttpStatus.OK);
	}

	@GetMapping("/size")
	public ResponseEntity<Long> getProductsCount() {
		return new ResponseEntity<Long>(productService.getProductsCount(), HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductReviewDto> getProduct(@PathVariable("productId") Long id){
		return new ResponseEntity<ProductReviewDto>(productService.getProduct(id), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
	}
}
