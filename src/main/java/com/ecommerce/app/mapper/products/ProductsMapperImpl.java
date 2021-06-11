package com.ecommerce.app.mapper.products;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Review;
import com.ecommerce.app.dto.ProductReviewDto;
import com.ecommerce.app.dto.ProductsDto;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductsMapperImpl implements ProductsMapper {

	public static double getRatings(List<Review> reviews){
		double rating = (float) reviews.stream()
		.mapToDouble(Review::getRating)
		.summaryStatistics().getSum();
		rating =  rating / reviews.size();
		return rating;
	}

	public static int getReviews(List<Review> reviews){
		return reviews.size();
	}

	public Iterable<ProductsDto> productsToProductsDTO(Iterable<Product> products){
		List<ProductsDto> productsDtoS = new ArrayList<>();
		for (Product product : products) {
			ProductsDto productsDto = new ProductsDto();
			productsDto.setId(product.getId());
			productsDto.setBrand(product.getBrand());
			productsDto.setPrice(product.getPrice());
			productsDto.setDescription(product.getDescription());
			productsDto.setStock(product.getStock());
			productsDto.setImages(product.getImages().get(0).getContent());
			productsDto.setRatings(ProductsMapperImpl.getRatings(product.getReviews()));
			productsDto.setReviews(ProductsMapperImpl.getReviews(product.getReviews()));
			productsDtoS.add(productsDto);
		}
		return productsDtoS;
	}

	public ProductReviewDto productToProductDto(Product product){
		ProductReviewDto productReviewDto = new ProductReviewDto();
		productReviewDto.setId(product.getId());
		productReviewDto.setBrand(product.getBrand());
		productReviewDto.setPrice(product.getPrice());
		productReviewDto.setDescription(product.getDescription());
		productReviewDto.setSpecifications(product.getSpecifications());
		productReviewDto.setStock(product.getStock());
		productReviewDto.setImages(product.getImages());
		productReviewDto.setReviews(product.getReviews());
		return productReviewDto;
	}
}
