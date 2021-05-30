package com.ecommerce.app.repository;

import com.ecommerce.app.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {}
