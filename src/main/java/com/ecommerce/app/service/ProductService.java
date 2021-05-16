package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.dto.PurchaseDto;
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
            return productRepository.findById(id).get();
        return null;
    }

    public void updateProductStock(PurchaseDto purchaseDto){
        Long[] idList = purchaseDto.getProductIdList();
        int[] itemsList = purchaseDto.getItemsList();
        for (int i = 0; i < idList.length; i++) {
            Product product;
        	if(productRepository.findById(idList[i]).isPresent()){
                product = productRepository.findById(idList[i]).get();
                int getCurrentStock = product.getStock();
                int updatedStock = getCurrentStock - itemsList[i];
                product.setStock(updatedStock);
                productRepository.save(product);
            }
        }
    }

    public void saveProduct(Product product){productRepository.save(product);}
}
