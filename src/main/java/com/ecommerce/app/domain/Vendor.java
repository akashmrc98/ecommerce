package com.ecommerce.app.domain;

import java.util.Set;
import lombok.Setter;
import lombok.Getter;
import javax.persistence.*;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vendor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendor;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    private String username;
    private  boolean isAccountNonLocked;
    private  boolean isEnabled;
    private  boolean isCredentialNonExpired;
    private  boolean isAccountNonExpired;

    private String[] roles;
    private String[] authorities;

    @OneToMany()
    private Set<Product> products;
    @OneToMany()
    private Set<Review> reviews;
    @OneToMany()
    private Set<Address> address;
}