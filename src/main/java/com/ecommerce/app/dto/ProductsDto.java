package com.ecommerce.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductsDto  {
	private Long id;
	private String description;
	private String brand;
	private Double price;
	private Integer stock;
	private byte[] images;
	private double ratings;
	private int reviews;
}