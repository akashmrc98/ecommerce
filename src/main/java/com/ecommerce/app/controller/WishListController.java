package com.ecommerce.app.controller;

import com.ecommerce.app.domain.WishList;
import com.ecommerce.app.dto.WishListProductDto;
import com.ecommerce.app.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce")
public class WishListController {

	private final WishListService wishListService;

	@PostMapping("/wishlist")
	@ResponseStatus(HttpStatus.CREATED)
	public void addToWishList(@RequestBody WishListProductDto wishListProductDto) {
		wishListService
		.addProductToWishList(
		wishListProductDto.getWishListId(),
		wishListProductDto.getProductId()
		);
	}

	@GetMapping("/wishlist")
	public ResponseEntity<WishList> getWishList(@RequestParam("wishListId") Long wishListId) {
		WishList wishList = wishListService.getWishList(wishListId);
		return new ResponseEntity<WishList>(wishList, HttpStatus.OK);
	}

	@DeleteMapping("/wishlist")
	@ResponseStatus(HttpStatus.OK)
	public void removeFromWishList(@RequestParam("wishListId") Long wishListId,
	                           @RequestParam("productId") Long productId) {
		wishListService.removeProductFromWishList(wishListId, productId);
	}

	@GetMapping("/wishlist-length")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Integer> getWishListLength(@RequestParam("wishListId") Long wishListId) {
		return new ResponseEntity<Integer>(wishListService.getWishListLength(wishListId), HttpStatus.OK);
	}
}

