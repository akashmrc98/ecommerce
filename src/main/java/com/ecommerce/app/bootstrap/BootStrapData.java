package com.ecommerce.app.bootstrap;

import com.ecommerce.app.domain.*;
import com.ecommerce.app.repository.*;
import com.ecommerce.app.security.UserRole;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CartRepository cartRepository;
	private final ImageRepository imageRepository;
	private final ProductRepository productRepository;
	private final WishListRepository wishListRepository;

	@Override
	public void run(String... args) throws Exception {

		Logger logger = LoggerFactory.getLogger(BootStrapData.class);

		if (userRepository.count() == 0) {

			String path = new File("").getAbsolutePath();
			path = path + "\\src\\main\\resources";
			path = path.replace("/", "");
			logger.debug(path);
			logger.error(path);
			logger.trace(path);

			Cart cart1 = new Cart();
			Cart cart2 = new Cart();
			Cart cart3 = new Cart();

			WishList wishList1 = new WishList();
			WishList wishList2 = new WishList();
			WishList wishList3 = new WishList();

			cartRepository.save(cart1);
			cartRepository.save(cart2);
			cartRepository.save(cart3);

			wishListRepository.save(wishList1);
			wishListRepository.save(wishList2);
			wishListRepository.save(wishList3);

			User akash = new User();
			akash.setName("akash");
			akash.setUsername("akash");
			akash.setEmail("akashmadduru@gmail.com");
			akash.setPhone("9494535327");
			akash.setPassword(passwordEncoder.encode("akash"));
			akash.setUserRole(UserRole.ADMIN.name());
			akash.setAccountNonExpired(true);
			akash.setAccountNonLocked(true);
			akash.setEnabled(true);
			akash.setCredentialsNonExpired(true);
			akash.setCart(cart1);
			akash.setWishList(wishList1);

			User avinash = new User();
			avinash.setName("avinash");
			avinash.setUsername("avinash");
			avinash.setEmail("avinash@gmail.com");
			avinash.setPhone("9440575890");
			avinash.setPassword(passwordEncoder.encode("avinash"));
			avinash.setUserRole(UserRole.USER.name());
			avinash.setAccountNonExpired(true);
			avinash.setAccountNonLocked(true);
			avinash.setEnabled(true);
			avinash.setCredentialsNonExpired(true);
			avinash.setCart(cart2);
			avinash.setWishList(wishList2);

			User guest = new User();
			guest.setName("guest");
			guest.setUsername("guest");
			guest.setEmail("guest@gmail.com");
			guest.setPhone("000100010");
			guest.setPassword(passwordEncoder.encode("guest"));
			guest.setUserRole(UserRole.GUEST.name());
			guest.setAccountNonExpired(true);
			guest.setAccountNonLocked(true);
			guest.setEnabled(true);
			guest.setCredentialsNonExpired(true);
			guest.setCart(cart3);
			guest.setWishList(wishList3);

			userRepository.save(akash);
			userRepository.save(avinash);
			userRepository.save(guest);

			Product product1 = new Product();
			String[] product1Specs = {
			"Ultra-quiet mouse with 90% reduced click sound",
			"Comfortable mobile shape is small enough to toss",
			"USB Receiver is provided with the mouse"
			};
			product1.setId(1L);
			product1.setBrand("Logitech");
			product1.setCategory("Electronics");
			product1.setSubCategory("Mouse");
			product1.setPrice(800.00);
			product1.setModifiedAt(new Date());
			product1.setStock(10);
			product1.setDescription("Logitech M221 Wireless Mouse, Silent Buttons, 2.4 GHz with " +
			"USB Mini Receiver, 1000 DPI Optical Tracking, 18-Month Battery Life, Ambidextrous " +
			"PC/Mac/Laptop - Red");
			product1.setCreatedAt(new Date());
			product1.setModifiedAt(new Date());
			product1.setSpecifications(product1Specs);

			Image image_a1 = new Image();
			File product_a1_file = new File(path + "\\products\\Product1\\product_a1.jpg");
			byte[] a1_file_content = Files.readAllBytes(product_a1_file.toPath());
			image_a1.setId(1L);
			image_a1.setName(product_a1_file.getName());
			image_a1.setUploadedAt(new Date());
			image_a1.setSize(product_a1_file.length());
			image_a1.setContent(a1_file_content);

			Image image_a2 = new Image();
			File product_a2_file = new File(path + "\\products\\Product1\\product_a2.jpg");
			byte[] a2_file_content = Files.readAllBytes(product_a2_file.toPath());
			image_a2.setId(2L);
			image_a2.setName(product_a2_file.getName());
			image_a2.setUploadedAt(new Date());
			image_a2.setSize(product_a2_file.length());
			image_a2.setContent(a2_file_content);

			Image image_a3 = new Image();
			File product_a3_file = new File(path + "\\products\\Product1\\product_a3.jpg");
			byte[] a3_file_content = Files.readAllBytes(product_a3_file.toPath());
			image_a3.setId(3L);
			image_a3.setName(product_a3_file.getName());
			image_a3.setUploadedAt(new Date());
			image_a3.setSize(product_a3_file.length());
			image_a3.setContent(a3_file_content);
			ArrayList<Image> images_a = new ArrayList<>();
			images_a.add(image_a1);
			images_a.add(image_a2);
			images_a.add(image_a3);
			product1.setImages(images_a);

			Product product2 = new Product();
			String[] product2Specs = {
			"JBL Signature Sound",
			"Noise Cancelling Microphone",
			"One-Button Universal Remote with Mic"
			};
			product2.setId(2L);
			product2.setBrand("JBL");
			product2.setCategory("Electronics");
			product2.setSubCategory("Headphones");
			product2.setPrice(649.00);
			product2.setModifiedAt(new Date());
			product2.setStock(10);
			product2.setDescription("JBL C100SI by Harman In-Ear Deep Bass Headphones with Mic " +
			"(Black)");
			product2.setCreatedAt(new Date());
			product2.setModifiedAt(new Date());
			product2.setSpecifications(product2Specs);

			Image image_b1 = new Image();
			File product_b1_file = new File(path + "\\products\\Product2\\product_b1.jpg");
			byte[] b1_file_content = Files.readAllBytes(product_b1_file.toPath());
			image_b1.setId(4L);
			image_b1.setName(product_b1_file.getName());
			image_b1.setUploadedAt(new Date());
			image_b1.setSize(product_b1_file.length());
			image_b1.setContent(b1_file_content);

			Image image_b2 = new Image();
			File product_b2_file = new File(path + "\\products\\Product2\\product_b2.jpg");
			byte[] b2_file_content = Files.readAllBytes(product_b2_file.toPath());
			image_b2.setId(5L);
			image_b2.setName(product_b2_file.getName());
			image_b2.setUploadedAt(new Date());
			image_b2.setSize(product_b2_file.length());
			image_b2.setContent(b2_file_content);

			Image image_b3 = new Image();
			File product_b3_file = new File(path + "\\products\\Product2\\product_b2.jpg");
			byte[] b3_file_content = Files.readAllBytes(product_b3_file.toPath());
			image_b3.setId(6L);
			image_b3.setName(product_b3_file.getName());
			image_b3.setUploadedAt(new Date());
			image_b3.setSize(product_b3_file.length());
			image_b3.setContent(b3_file_content);
			ArrayList<Image> images_b = new ArrayList<>();
			images_b.add(image_b1);
			images_b.add(image_b2);
			images_b.add(image_b3);
			product2.setImages(images_b);

			Product product3 = new Product();
			String[] product3Specs = {
			"64GB",
			"Free up space on your OTG-enabled Android phone",
			"Retractable design with dual micro-USB and USB 3.0 connectors"
			};
			product3.setId(3L);
			product3.setBrand("Sandisk");
			product3.setCategory("Electronics");
			product3.setSubCategory("Pen drive");
			product3.setPrice(829.00);
			product3.setModifiedAt(new Date());
			product3.setStock(10);
			product3.setDescription("SanDisk Ultra Dual 64GB USB 3.0 OTG Pen Drive");
			product3.setCreatedAt(new Date());
			product3.setModifiedAt(new Date());
			product3.setSpecifications(product3Specs);

			Image image_c1 = new Image();
			File product_c1_file = new File(path + "\\products\\Product3\\product_c1.jpg");
			byte[] c1_file_content = Files.readAllBytes(product_c1_file.toPath());
			image_c1.setId(7L);
			image_c1.setName(product_c1_file.getName());
			image_c1.setUploadedAt(new Date());
			image_c1.setSize(product_c1_file.length());
			image_c1.setContent(c1_file_content);

			Image image_c2 = new Image();
			File product_c2_file = new File(path + "\\products\\Product3\\product_c2.jpg");
			byte[] c2_file_content = Files.readAllBytes(product_c2_file.toPath());
			image_c2.setId(8L);
			image_c2.setName(product_c2_file.getName());
			image_c2.setUploadedAt(new Date());
			image_c2.setSize(product_c2_file.length());
			image_c2.setContent(c2_file_content);

			Image image_c3 = new Image();
			File product_c3_file = new File(path + "\\products\\Product3\\product_c3.jpg");
			byte[] c3_file_content = Files.readAllBytes(product_c3_file.toPath());
			image_c3.setId(9L);
			image_c3.setName(product_c3_file.getName());
			image_c3.setUploadedAt(new Date());
			image_c3.setSize(product_c3_file.length());
			image_c3.setContent(c3_file_content);
			ArrayList<Image> images_c = new ArrayList<>();
			images_c.add(image_c1);
			images_c.add(image_c2);
			images_c.add(image_c3);
			product3.setImages(images_c);

			imageRepository.save(image_a1);
			imageRepository.save(image_a2);
			imageRepository.save(image_a3);
			imageRepository.save(image_b1);
			imageRepository.save(image_b2);
			imageRepository.save(image_b3);
			imageRepository.save(image_c1);
			imageRepository.save(image_c2);
			imageRepository.save(image_c3);

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
		}
	}
}
