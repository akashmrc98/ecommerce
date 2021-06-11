package com.ecommerce.app.mapper.products;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.dto.ProductReviewDto;
import com.ecommerce.app.dto.ProductsDto;

public interface ProductsMapper {
	 Iterable<ProductsDto> productsToProductsDTO(Iterable<Product> products);
	 ProductReviewDto productToProductDto(Product product);
}
