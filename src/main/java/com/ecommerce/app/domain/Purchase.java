package com.ecommerce.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer totalProducts;
	private Double totalPrice;
	private Date purchasedAt;
	private Date lastModifiedAt;
	private int[] productsQuantityList;
	private String paymentMethod;

	@OneToOne
	private Address address;

	@ManyToMany
	private List<DeliveryStatus> deliveryStatuses;

	@ManyToMany
	private List<Product> products;
}
