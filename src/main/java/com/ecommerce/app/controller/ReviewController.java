package com.ecommerce.app.controller;

import com.ecommerce.app.dto.LikeDto;
import com.ecommerce.app.dto.ReviewDto;
import com.ecommerce.app.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ecommerce/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void saveReview(@RequestBody ReviewDto review){
		reviewService.saveReview(review);
	}

	@PostMapping("/like/{reviewId}")
	@ResponseStatus(HttpStatus.OK)
	public void likeReview(@PathVariable("reviewId") Long id, @RequestBody LikeDto username){
	reviewService.likeReview(username, id);
	}

}
