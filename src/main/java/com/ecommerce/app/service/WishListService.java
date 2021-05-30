package com.ecommerce.app.service;

import com.ecommerce.app.domain.Cart;
import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.WishList;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.WishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WishListService {

	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final WishListRepository wishListRepository;

	public Iterable<Product> getWishListById(Long wishListId){
		if(wishListRepository.findById(wishListId).isPresent())
			return wishListRepository.findById(wishListId).get().getProducts();
		return null;
	}

	public void addProductToWishListByProductId(Long wishListId, Long productId) {
		WishList wishList = null;
		Product product = null;
		if(wishListRepository.findById(wishListId).isPresent())
			wishList = wishListRepository.findById(wishListId).get();
		assert wishList != null;
		List<Product> wishListProducts = wishList.getProducts();
		if(productRepository.findById(productId).isPresent())
			product = productRepository.findById(productId).get();
		wishListProducts.add(product);
		wishListRepository.save(wishList);
	}

	public void removeProductFromWishListByProductId(Long wishListId, Long productId){
		Optional<WishList> wishList = wishListRepository.findById(wishListId);
		List<Product> wishListProducts = null;
		if(wishList.isPresent())
			wishListProducts = wishList.get().getProducts();
		assert wishListProducts != null;
		wishListProducts.removeIf(product -> product.getId().equals(productId));
		wishListRepository.save(wishList.get());
	}

	public void deleteAllProductsInWishListById(Long wishListId){
		WishList wishList = null;
		if(wishListRepository.findById(wishListId).isPresent())
			wishList = wishListRepository.findById(wishListId).get();
		List<Product> products = new ArrayList<>();
		assert wishList != null;
		wishList.setProducts(products);
		wishListRepository.save(wishList);
	}
}
