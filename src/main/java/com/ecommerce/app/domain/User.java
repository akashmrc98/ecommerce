package com.ecommerce.app.domain;

import java.util.*;

import lombok.Setter;
import lombok.Getter;
import javax.persistence.*;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;

    private String name;
    private String password;
    private String userRole;

    private  boolean isAccountNonLocked;
    private  boolean isEnabled;
    private  boolean isCredentialsNonExpired;
    private  boolean isAccountNonExpired;

    @OneToMany()
    private List<Review> reviews = new ArrayList<>();
    @OneToMany()
    private List<Address> address = new ArrayList<>();
    @OneToMany()
    private List<Purchase> purchases = new ArrayList<>();
    @OneToOne()
    private Cart cart;
}
