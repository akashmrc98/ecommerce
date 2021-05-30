package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.dto.CartProductDto;
import com.ecommerce.app.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/carts")
public class CartController {

	private final CartService cartService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addProductToCartByID(@RequestBody CartProductDto cartProductDto) {
		cartService.addProductToCartByProductID(
			cartProductDto.getCartId(),
			cartProductDto.getProductId()
		);
	}

	@GetMapping("/{cartId}")
	public ResponseEntity<Iterable<Product>> getCartByID(@PathVariable("cartId") Long cartId) {
		Iterable<Product> products = cartService.getCartProductsByID(cartId);
		return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void removeProductFromCartByProductID(
	@RequestParam("cartId") Long cartId,
    @RequestParam("productId") Long productId) {
	cartService.removeProductFromCartByProductID(cartId , productId);
	}
}
