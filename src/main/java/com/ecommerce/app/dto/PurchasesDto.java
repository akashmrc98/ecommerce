package com.ecommerce.app.dto;

import com.ecommerce.app.domain.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PurchasesDto {
	private Long id;
	private Integer totalProducts;
	private Double totalPrice;
	private Date purchasedAt;
	private int[] productsQuantityList;
	private String paymentMethod;
	private Address address;
	private Iterable<ProductsDto> products;
}
