package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Review;
import com.ecommerce.app.dto.ProductReviewDto;
import com.ecommerce.app.dto.ProductsDto;
import com.ecommerce.app.mapper.products.ProductsMapper;
import com.ecommerce.app.model.request.PurchaseDto;
import com.ecommerce.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsMapper productsMapperImpl;

    public Iterable<ProductsDto> getProducts(Integer pageNo, Integer pageSize, String sortBy){
    	PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    	Page<Product> pagedResult = productRepository.findAll(pageRequest);
    	return productsMapperImpl.productsToProductsDTO(pagedResult);
    }

    public ProductReviewDto getProduct(Long id){
        if(productRepository.findById(id).isPresent())
            return productsMapperImpl.productToProductDto(productRepository.findById(id).get());
        return null;
    }

    public Long getProductsCount(){
        return productRepository.count();
    }

    public void updateProductStock(PurchaseDto purchaseDto){
        Long[] productIdList = purchaseDto.getProductsIdList();
        int[] productQuantityList = purchaseDto.getProductsQuantityList();
        for (int i = 0; i < productIdList.length; i++) {
            Product product;
        	if(productRepository.findById(productIdList[i]).isPresent()){
                product = productRepository.findById(productIdList[i]).get();
                int getCurrentStock = product.getStock();
                int updatedStock = getCurrentStock - productQuantityList[i];
                product.setStock(updatedStock);
                productRepository.save(product);
            }
        }
    }

    public void saveProduct(Product product){productRepository.save(product);}
}
