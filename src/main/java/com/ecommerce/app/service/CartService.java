package com.ecommerce.app.service;

import com.ecommerce.app.domain.Cart;
import com.ecommerce.app.domain.Product;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final ProductRepository productRepository;

	public Cart getCart(Long cartId){
		return cartRepository.findById(cartId).get();
	}

	public void addProductToCart(Long cartId, Long productId) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		List<Product> cartProducts = cart.get().getProducts();
		Product product = productRepository.findById(productId).get();
		cartProducts.add(product);
		cartRepository.save(cart.get());
	}

	public void removeProductFromCart(Long cartId, Long productId){
		Optional<Cart> cart = cartRepository.findById(cartId);
		List<Product> cartProducts = cart.get().getProducts();
		cartProducts.removeIf(product -> product.getId().equals(productId));
		cartRepository.save(cart.get());
	}

	public Integer getCartLength(Long cartId){
		Optional<Cart> cart = cartRepository.findById(cartId);
		return cart.get().getProducts().size();
	}
}
