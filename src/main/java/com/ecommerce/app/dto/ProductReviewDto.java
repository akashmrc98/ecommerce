package com.ecommerce.app.dto;

import com.ecommerce.app.domain.Image;
import com.ecommerce.app.domain.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductReviewDto {
	private Long id;
	private String description;
	private String brand;
	private Double price;
	private Integer stock;
	private String[] specifications;
	private List<Image> images;
	private List<Review> reviews;
}
