package com.ecommerce.app.service;

import com.ecommerce.app.domain.Cart;
import com.ecommerce.app.domain.Product;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final ProductRepository productRepository;

	public Cart getCart(Long cartId){
		if(cartRepository.findById(cartId).isPresent())
			return cartRepository.findById(cartId).get();
		return null;
	}

	public void addProductToCart(Long cartId, Long productId) {
		Cart cart = null;
		Product product = null;
		if(cartRepository.findById(cartId).isPresent())
			cart = cartRepository.findById(cartId).get();
		assert cart != null;
		List<Product> cartProducts = cart.getProducts();
		if(productRepository.findById(productId).isPresent())
			product = productRepository.findById(productId).get();
		cartProducts.add(product);
		cartRepository.save(cart);
	}

	public void removeProductFromCart(Long cartId, Long productId){
		Optional<Cart> cart = cartRepository.findById(cartId);
		List<Product> cartProducts = null;
		if(cart.isPresent())
			cartProducts = cart.get().getProducts();
		assert cartProducts != null;
		cartProducts.removeIf(product -> product.getId().equals(productId));
		cartRepository.save(cart.get());
	}

	public void clearCart(Long cartId){
		Cart cart = null;
		if(cartRepository.findById(cartId).isPresent())
			cart = cartRepository.findById(cartId).get();
		List<Product> products = new ArrayList<>();
		assert cart != null;
		cart.setProducts(products);
		cartRepository.save(cart);
	}

	public Integer getCartLength(Long cartId){
		Cart cart = null;
		if(cartRepository.findById(cartId).isPresent())
			cart = cartRepository.findById(cartId).get();
		assert cart != null;
		return cart.getProducts().size();
	}
}
