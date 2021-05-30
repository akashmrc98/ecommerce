package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;

	public Purchase checkOutOrder(PurchaseDto purchaseDto){
		Purchase purchase = new Purchase();
		purchase.setTotalProducts(purchaseDto.getTotalProducts());
		purchase.setTotalPrice(purchaseDto.getTotalPrice());
		purchase.setLastModifiedAt(new Date());
		purchase.setPurchasedAt(new Date());
		purchase.setProducts(purchaseDto.getProducts());
		purchase.setProductsQuantityList(purchaseDto.getProductsQuantityList());
		purchase.setAddress(purchaseDto.getAddress());
		purchase.setPaymentMethod(purchaseDto.getPaymentMethod());
		purchaseRepository.save(purchase);
		return purchase;
	}
}
