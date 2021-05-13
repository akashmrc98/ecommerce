package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Cart;
import com.ecommerce.app.dto.CartProductDto;
import com.ecommerce.app.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce")
public class CartController {

	private final CartService cartService;

	@PostMapping("/cart")
	@ResponseStatus(HttpStatus.CREATED)
	public void addToCart(@RequestBody CartProductDto cartProductDto) {
		cartService.addProductToCart(cartProductDto.getCartId(), cartProductDto.getProductId());
	}

	@GetMapping("/cart")
	public ResponseEntity<Cart> getCart(@RequestParam("cartId") Long cartId) {
		Cart cart = cartService.getCart(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@DeleteMapping("/cart")
	@ResponseStatus(HttpStatus.OK)
	public void removeFromCart(@RequestParam("cartId") Long cartId,
	                           @RequestParam("productId") Long productId) {
		cartService.removeProductFromCart(cartId, productId);
	}

	@GetMapping("/cart-length")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Integer> getCartLength(@RequestParam("cartId") Long cartId) {
		return new ResponseEntity<Integer>(cartService.getCartLength(cartId), HttpStatus.OK);
	}

}
