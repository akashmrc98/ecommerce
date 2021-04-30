package com.ecommerce.app.service;

import com.ecommerce.app.domain.Session;
import com.ecommerce.app.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {
	private final SessionRepository sessionRepository;

	public void saveToken(Session session){
		sessionRepository.save(session);
	}
}
