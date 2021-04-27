package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.io.IOException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ImageRepositoryTest {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void testInsertImage(Image image) throws IOException {
//		Image existDoc = testEntityManager.find(Image.class, savedImg.getId());
	}

}