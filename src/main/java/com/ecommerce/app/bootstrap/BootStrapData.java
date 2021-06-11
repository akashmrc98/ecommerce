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
			product3.setId(4L);
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

			Product product4 = new Product();
			String[] product4Specs = {
			"48MP rear camera | 8MP tele + 16MP camera",
			"4000mAH lithium-ion battery",
			"8GB RAM | 256GB internal memory"
			};
			product4.setId(4L);
			product4.setBrand("OnePlus");
			product4.setCategory("Electronics");
			product4.setSubCategory("Mobile");
			product4.setPrice(59999.00);
			product4.setModifiedAt(new Date());
			product4.setStock(12);
			product4.setDescription("OnePlus 7 Pro, 8GB RAM, Super AMOLED Display," +
			"256GB Storage, 4000mAH");
			product4.setCreatedAt(new Date());
			product4.setModifiedAt(new Date());
			product4.setSpecifications(product4Specs);

			Image image_d1 = new Image();
			File product_d1_file = new File(path + "\\products\\Product4\\product_d1.jpg");
			byte[] d1_file_content = Files.readAllBytes(product_d1_file.toPath());
			image_d1.setId(10L);
			image_d1.setName(product_d1_file.getName());
			image_d1.setUploadedAt(new Date());
			image_d1.setSize(product_d1_file.length());
			image_d1.setContent(d1_file_content);

			Image image_d2 = new Image();
			File product_d2_file = new File(path + "\\products\\Product4\\product_d2.jpg");
			byte[] d2_file_content = Files.readAllBytes(product_d2_file.toPath());
			image_d2.setId(11L);
			image_d2.setName(product_d2_file.getName());
			image_d2.setUploadedAt(new Date());
			image_d2.setSize(product_d2_file.length());
			image_d2.setContent(d2_file_content);

			Image image_d3 = new Image();
			File product_d3_file = new File(path + "\\products\\Product4\\product_d3.jpg");
			byte[] d3_file_content = Files.readAllBytes(product_d3_file.toPath());
			image_d3.setId(12L);
			image_d3.setName(product_d3_file.getName());
			image_d3.setUploadedAt(new Date());
			image_d3.setSize(product_d3_file.length());
			image_d3.setContent(d3_file_content);
			ArrayList<Image> images_d = new ArrayList<>();
			images_d.add(image_d1);
			images_d.add(image_d2);
			images_d.add(image_d3);
			product4.setImages(images_d);


			Product product5 = new Product();
			String[] product5Specs = {
			"Intel Core i7 9700 Desktop 9th Gen 8 Cores",
			"8 Cores /8 Threads",
			"Intel UHD Graphics 630"
			};
			product5.setId(5L);
			product5.setBrand("Intel");
			product5.setCategory("Electronics");
			product5.setSubCategory("Processor");
			product5.setPrice(29999.00);
			product5.setModifiedAt(new Date());
			product5.setStock(12);
			product5.setDescription("Intel Core i7 9700 Desktop 9th Gen Processor 8 Cores");
			product5.setCreatedAt(new Date());
			product5.setModifiedAt(new Date());
			product5.setSpecifications(product5Specs);

			Image image_e1 = new Image();
			File product_e1_file = new File(path + "\\products\\Product5\\product_e1.jpg");
			byte[] e1_file_content = Files.readAllBytes(product_e1_file.toPath());
			image_e1.setId(13L);
			image_e1.setName(product_e1_file.getName());
			image_e1.setUploadedAt(new Date());
			image_e1.setSize(product_e1_file.length());
			image_e1.setContent(e1_file_content);

			Image image_e2 = new Image();
			File product_e2_file = new File(path + "\\products\\Product5\\product_e2.jpg");
			byte[] e2_file_content = Files.readAllBytes(product_e2_file.toPath());
			image_e2.setId(14L);
			image_e2.setName(product_e2_file.getName());
			image_e2.setUploadedAt(new Date());
			image_e2.setSize(product_e2_file.length());
			image_e2.setContent(e2_file_content);

			Image image_e3 = new Image();
			File product_e3_file = new File(path + "\\products\\Product5\\product_e3.jpg");
			byte[] e3_file_content = Files.readAllBytes(product_e3_file.toPath());
			image_e3.setId(15L);
			image_e3.setName(product_e3_file.getName());
			image_e3.setUploadedAt(new Date());
			image_e3.setSize(product_e3_file.length());
			image_e3.setContent(e3_file_content);
			ArrayList<Image> images_e = new ArrayList<>();
			images_e.add(image_e1);
			images_e.add(image_e2);
			images_e.add(image_e3);
			product5.setImages(images_e);

			Product product6 = new Product();
			String[] product6Specs = {
			"2.60GHz Intel Core i7-9750H 9th Gen processor",
			"1TB 7200rpm HDD+ 256 GB SSD | 8GB DDR4 RAM",
			"15.6-inch screen, NVIDIA GTX 1650 4GB Graphics"
			};
			product6.setId(6L);
			product6.setBrand("HP");
			product6.setCategory("Electronics");
			product6.setSubCategory("Laptop");
			product6.setPrice(129999.00);
			product6.setModifiedAt(new Date());
			product6.setStock(12);
			product6.setDescription("HP Pavilion Gaming 9th Gen Intel Core i7 Processor 15.6-inch " +
			"FHD Gaming Laptop (i7-9750H/8GB/256GB SSD + 1TB HDD/Windows 10/4GB NVIDIA GTX 1650");
			product6.setCreatedAt(new Date());
			product6.setModifiedAt(new Date());
			product6.setSpecifications(product6Specs);

			Image image_f1 = new Image();
			File product_f1_file = new File(path + "\\products\\Product6\\product_f1.jpg");
			byte[] f1_file_content = Files.readAllBytes(product_f1_file.toPath());
			image_f1.setId(16L);
			image_f1.setName(product_f1_file.getName());
			image_f1.setUploadedAt(new Date());
			image_f1.setSize(product_f1_file.length());
			image_f1.setContent(f1_file_content);

			Image image_f2 = new Image();
			File product_f2_file = new File(path + "\\products\\Product6\\product_f2.jpg");
			byte[] f2_file_content = Files.readAllBytes(product_f2_file.toPath());
			image_f2.setId(17L);
			image_f2.setName(product_f2_file.getName());
			image_f2.setUploadedAt(new Date());
			image_f2.setSize(product_f2_file.length());
			image_f2.setContent(f2_file_content);

			Image image_f3 = new Image();
			File product_f3_file = new File(path + "\\products\\Product6\\product_f3.jpg");
			byte[] f3_file_content = Files.readAllBytes(product_f3_file.toPath());
			image_f3.setId(18L);
			image_f3.setName(product_f3_file.getName());
			image_f3.setUploadedAt(new Date());
			image_f3.setSize(product_f3_file.length());
			image_f3.setContent(f3_file_content);
			ArrayList<Image> images_f = new ArrayList<>();
			images_f.add(image_f1);
			images_f.add(image_f2);
			images_f.add(image_f3);
			product6.setImages(images_f);

			imageRepository.save(image_a1);
			imageRepository.save(image_a2);
			imageRepository.save(image_a3);
			imageRepository.save(image_b1);
			imageRepository.save(image_b2);
			imageRepository.save(image_b3);
			imageRepository.save(image_c1);
			imageRepository.save(image_c2);
			imageRepository.save(image_c3);
			imageRepository.save(image_d1);
			imageRepository.save(image_d2);
			imageRepository.save(image_d3);
			imageRepository.save(image_e1);
			imageRepository.save(image_e2);
			imageRepository.save(image_e3);
			imageRepository.save(image_f1);
			imageRepository.save(image_f2);
			imageRepository.save(image_f3);

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
			productRepository.save(product4);
			productRepository.save(product5);
			productRepository.save(product6);
		}
	}
}
