package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
