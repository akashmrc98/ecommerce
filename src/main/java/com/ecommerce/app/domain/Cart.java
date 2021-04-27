package com.ecommerce.app.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany()
    private Set<Product> products;
    @OneToOne()
    private User user;
}
