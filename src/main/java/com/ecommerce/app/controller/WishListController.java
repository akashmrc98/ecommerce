package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.dto.ProductsDto;
import com.ecommerce.app.model.request.WishListProductDto;
import com.ecommerce.app.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce/wishlists")
public class WishListController {

	private final WishListService wishListService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addProductToWishListById(@RequestBody WishListProductDto wishListProductDto) {
		wishListService.addProductToWishListByProductId(
		wishListProductDto.getWishListId(),
		wishListProductDto.getProductId()
		);
	}

	@GetMapping("/{wishListId}")
	public ResponseEntity<Iterable<ProductsDto>> getWishListById(@PathVariable("wishListId") Long wishListId) {
		Iterable<ProductsDto> products = wishListService.getWishListById(wishListId);
		return new ResponseEntity<Iterable<ProductsDto>>(products, HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void removeProductFromWishListById(
	@RequestParam("wishListId") String wishListId,
    @RequestParam("productId") String productId)
	{
		wishListService
		.removeProductFromWishListByProductId(Long.parseLong(wishListId), Long.parseLong(productId));
	}
}

