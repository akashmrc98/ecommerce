package com.ecommerce.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 3000, nullable = false, unique = true)
	private String name;
	private long size;
	private Date uploadedAt;
	@Lob()
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] content;
}
