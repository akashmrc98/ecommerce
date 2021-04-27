package com.ecommerce.app.domain;

import com.ecommerce.app.domain.Review;
import com.ecommerce.app.domain.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String brand;
    private String category;
    private Double price;
    private Date manufacturedOn;
    private String[] specifications;
    private Integer stock;
    
    @OneToMany()
    private Set<Image> images;
    
    public Product(String description, String brand, String category, Double price,
     Date manufacturedOn, String[] specifications, Integer stock){
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.manufacturedOn = manufacturedOn;
        this.specifications = specifications;
        this.stock = stock;
    }
    
    @OneToMany()
    private Set<Review> reviews;
}
