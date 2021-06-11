package com.ecommerce.app.service;

import com.ecommerce.app.domain.Product;
import com.ecommerce.app.domain.Review;
import com.ecommerce.app.domain.User;
import com.ecommerce.app.model.request.LikeDto;
import com.ecommerce.app.model.request.ReviewDto;
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

	public int getReviewLikes(Long reviewId){
		return reviewsRepository.findById(reviewId).get().getFavourites();
	}

	public void likeReview(LikeDto username, Long reviewId) throws Exception {
		Review review = reviewsRepository.findById(reviewId).get();
		boolean isLiked = review.getUsername().equals(review.getUsername());
		if(!isLiked){
			int updatedLikes = review.getFavourites() + 1;
			review.setFavourites(updatedLikes);
			reviewsRepository.save(review);
		}
		if(isLiked){
			throw new Exception("User already liked review");
		}
	}

}
