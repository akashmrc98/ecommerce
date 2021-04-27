package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ecommerce")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getProducts(){
        return new ResponseEntity<Iterable<Product>>(productService.getProducts(), HttpStatus.OK);
    }
    
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product){
    	productService.saveProduct(product);
    }
}
