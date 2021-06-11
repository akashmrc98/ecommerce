package com.ecommerce.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {
	private String username;
	private Long productId;
	private String headLine;
	private String description;
	private int rating;
}
