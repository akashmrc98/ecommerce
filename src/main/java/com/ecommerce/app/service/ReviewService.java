package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Review;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.dto.LikeDto;
import com.ecommerce.app.dto.ReviewDto;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.ReviewsRepository;
import com.ecommerce.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final ReviewsRepository reviewsRepository;

	public void saveReview(ReviewDto review){
		Review newReview = new Review();
		newReview.setUsername(review.getUsername());
		newReview.setHeadLine(review.getHeadLine());
		newReview.setDescription(review.getDescription());
		newReview.setRating(review.getRating());
		newReview.setReviewedOn(new Date());
		reviewsRepository.save(newReview);

		User user = userRepository.findByUsername(review.getUsername());
		List<Review> userReviews = user.getReviews();
		userReviews.add(newReview);
		userRepository.save(user);

		Product product = productRepository.findById(review.getProductId()).get();
		List<Review> productReviews = product.getReviews();
		productReviews.add(newReview);
		productRepository.save(product);
	}

	public void likeReview(LikeDto username, Long reviewId){
		Review review = reviewsRepository.findById(reviewId).get();
		List<String> likes = review.getLikes();
		likes.add(username.getUsername());
		review.setLikes(likes);
		reviewsRepository.save(review);
	}

}
