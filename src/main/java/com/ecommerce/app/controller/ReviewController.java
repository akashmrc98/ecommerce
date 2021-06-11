package com.ecommerce.app.controller;

import com.ecommerce.app.model.request.LikeDto;
import com.ecommerce.app.model.request.ReviewDto;
import com.ecommerce.app.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ecommerce/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@GetMapping("/likes/{reviewLikeId}")
	public ResponseEntity<Integer> getReviewLikes(@PathVariable("reviewLikeId") Long id){
		return new ResponseEntity<Integer>(reviewService.getReviewLikes(id), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void saveReview(@RequestBody ReviewDto review){
		reviewService.saveReview(review);
	}

	@PostMapping("/like/{reviewId}")
	@ResponseStatus(HttpStatus.OK)
	public void likeReview(@PathVariable("reviewId") Long id, @RequestBody LikeDto username) throws Exception {
	reviewService.likeReview(username, id);
	}

}
