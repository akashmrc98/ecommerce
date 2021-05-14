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
}
