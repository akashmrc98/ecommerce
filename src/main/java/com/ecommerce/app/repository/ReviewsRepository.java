package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Review, Long> {
}
