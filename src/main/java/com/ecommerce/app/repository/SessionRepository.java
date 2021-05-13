package com.ecommerce.app.repository;

import com.ecommerce.app.domain.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface SessionRepository extends CrudRepository<Session, Long> {

	Optional<Session> findByRefreshToken(String refreshToken);

	@Modifying
	@Transactional
	@Query("update Session s set s.accessToken = :accessToken where s.id = :id")
	void updateSessionToken(@Param("id") Long id, @Param("accessToken") String accessToken);
}
