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
		List<Product> products = purchaseDto.getProducts();
		Purchase purchase = new Purchase();
		purchase.setItems(purchaseDto.getTotalItems());
		purchase.setPrice(purchaseDto.getTotalPrice());
		purchase.setLastModifiedAt(new Date());
		purchase.setPurchasedAt(new Date());
		purchase.setProducts(products);
		purchase.setItemsQuantity(purchaseDto.getItemsList());
		purchase.setAddress(purchaseDto.getAddress());
		purchase.setPaymentMethod(purchaseDto.getPaymentMethod());
		purchaseRepository.save(purchase);
		return purchase;
	}

	public Iterable<Purchase> getPurchases(){
		return purchaseRepository.findAll();
	}
}
