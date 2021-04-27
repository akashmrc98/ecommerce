package com.ecommerce.app.repository;

import com.ecommerce.app.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}