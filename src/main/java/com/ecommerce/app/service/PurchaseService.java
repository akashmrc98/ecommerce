package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.model.request.PurchaseDto;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {

	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;

	public Purchase checkOutOrder(PurchaseDto purchaseDto){
		List<Product> products= new ArrayList<>();
		for (Long product:purchaseDto.getProductsIdList()) {
			products.add(productRepository.findById(product).get());
		}
		Purchase purchase = new Purchase();
		purchase.setTotalProducts(purchaseDto.getTotalProducts());
		purchase.setTotalPrice(purchaseDto.getTotalPrice());
		purchase.setLastModifiedAt(new Date());
		purchase.setPurchasedAt(new Date());
		purchase.setProducts(products);
		purchase.setProductsQuantityList(purchaseDto.getProductsQuantityList());
		purchase.setAddress(purchaseDto.getAddress());
		purchase.setPaymentMethod(purchaseDto.getPaymentMethod());
		purchaseRepository.save(purchase);
		return purchase;
	}
}
