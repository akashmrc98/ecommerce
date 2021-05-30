package com.ecommerce.app.dto;
import com.ecommerce.app.domain.Address;
import com.ecommerce.app.domain.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class PurchaseDto {
	private Long cartId;
	private String username;
	private double totalPrice;
	private int totalProducts;
	private int[] productsQuantityList;
	private Long[] productsIdList;
	private List<Product> products;
	private String paymentMethod;
	private Address address;
}
