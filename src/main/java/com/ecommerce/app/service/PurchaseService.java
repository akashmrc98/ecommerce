package com.ecommerce.app.service;

import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;

	public Purchase checkOutOrder(PurchaseDto purchaseDto){
		Purchase purchase = new Purchase();
		purchase.setItems(purchaseDto.getTotalItems());
		purchase.setPrice(purchaseDto.getTotalPrice());
		purchase.setProducts(purchase.getProducts());
		purchaseRepository.save(purchase);
		return purchase;
	}
}
