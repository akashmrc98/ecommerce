package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Long id){
        if(productRepository.findById(id).isPresent())
            productRepository.findById(id).get();
        return null;
    }

    public void saveProduct(Product product){productRepository.save(product);}
}
