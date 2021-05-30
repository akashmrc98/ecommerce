package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Review;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Cacheable(value = "products-cache")
    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Long id){
        if(productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        return null;
    }

    public Iterable<Review> getProductReviews(Long id){
        Product product = getProduct(id);
        return product.getReviews();
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
