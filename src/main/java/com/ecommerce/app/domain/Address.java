package com.ecommerce.app.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstLine;
    private String secondLine;
    private String zip;
    private String city;
    private String state;

    @ManyToOne
    private User user;

    public Address(String firstLine, String secondLine, String zip, String city, String state) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.zip = zip;
        this.city = city;
        this.state = state;
    }
}
