package com.ecommerce.app.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob()
	private String accessToken;

	@Lob()
	private String refreshToken;

	@OneToOne
	@JoinColumn(name = "user")
	User user;
}
