package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.service.CartService;
import com.ecommerce.app.service.PurchaseService;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ecommerce")
public class PurchaseController {

	private final PurchaseService purchaseService;
	private final CartService cartService;
	private final UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/purchases")
	public void checkOutCart(@RequestBody PurchaseDto purchaseDto){
		Purchase purchase = purchaseService.checkOutOrder(purchaseDto);
		cartService.clearCart(purchaseDto.getCartId());
		userService.createOrderToUser(purchaseDto.getUsername(), purchase);
	}

}
