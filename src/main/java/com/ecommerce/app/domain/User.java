package com.ecommerce.app.domain;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.domain.Review;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(unique = true)
    private String phone;

    @OneToMany()
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> address;

    @OneToMany()
    private Set<Review> reviews;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
