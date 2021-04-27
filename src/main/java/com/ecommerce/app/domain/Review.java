package com.ecommerce.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    public Review(String review, int rating, LocalDateTime postedOn) {
        this.review = review;
        this.rating = rating;
        this.postedOn = postedOn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;
    private int rating;
    private LocalDateTime postedOn;

    @ManyToOne
    private User user;

}
