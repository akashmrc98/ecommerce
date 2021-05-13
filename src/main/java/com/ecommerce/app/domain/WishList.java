package com.ecommerce.app.domain;

import javax.persistence.*;
import java.util.List;

public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany()
	private List<Product> products;
	@OneToOne()
	private User user;
}
