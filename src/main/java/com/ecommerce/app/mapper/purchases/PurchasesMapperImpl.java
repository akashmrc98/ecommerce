package com.ecommerce.app.mapper.purchases;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.dto.ProductsDto;
import com.ecommerce.app.dto.PurchasesDto;
import com.ecommerce.app.mapper.products.ProductsMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class PurchasesMapperImpl implements PurchasesMapper {

	private final ProductsMapper productsMapperImpl;

	public Iterable<PurchasesDto> purchasesToPurchasesDto(Iterable<Purchase> purchases){
		List<PurchasesDto> purchasesDtoS = new ArrayList<>();
		for (Purchase purchase : purchases) {
			List<Product> products = purchase.getProducts();
			Iterable<ProductsDto> productsDtoS = productsMapperImpl.productsToProductsDTO(products);
			PurchasesDto purchasesDto = new PurchasesDto();
			purchasesDto.setId(purchase.getId());
			purchasesDto.setAddress(purchase.getAddress());
			purchasesDto.setProducts(productsDtoS);
			purchasesDto.setPaymentMethod(purchase.getPaymentMethod());
			purchasesDto.setPurchasedAt(purchase.getPurchasedAt());
			purchasesDto.setProductsQuantityList(purchase.getProductsQuantityList());
			purchasesDto.setTotalPrice(purchase.getTotalPrice());
			purchasesDto.setTotalProducts(purchase.getTotalProducts());
			purchasesDtoS.add(purchasesDto);
		}
		return purchasesDtoS;
	}
}
