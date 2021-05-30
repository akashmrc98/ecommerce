package com.ecommerce.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.index.qual.NonNegative;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
	private String subCategory;
	private Double price;
	private Date manufacturedOn;
	private Date createdAt;
	private Date modifiedAt;
	private String[] specifications;

	@NonNegative
	private Integer stock;

	@OneToMany
	private List<Image> images;
	@OneToMany
	private List<Review> reviews;
}
