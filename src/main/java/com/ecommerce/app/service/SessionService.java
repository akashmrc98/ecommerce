package com.ecommerce.app.service;

import com.ecommerce.app.domain.Session;
import com.ecommerce.app.jwt.JwtResponse;
import com.ecommerce.app.jwt.JwtTokenGenerator;
import com.ecommerce.app.jwt.JwtTokenVerifier;
import com.ecommerce.app.repository.SessionRepository;
import com.ecommerce.app.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

	private final SessionRepository sessionRepository;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final JwtTokenVerifier jwtTokenVerifier;

	public JwtResponse generateNewAccessToken(Session session) {

		String refreshToken = session.getRefreshToken();
		Optional<Session> isValidToken = sessionRepository.findByRefreshToken(refreshToken);

		return isValidToken.map(session1 -> {

			UserRole authorities = UserRole.valueOf(session1.getUser().getUserRole());

			String newAccessToken = jwtTokenGenerator.generateAccessToken(
			session1.getUser().getUsername(),
			authorities.getGrantedAuthorities()
			);


			sessionRepository.updateSessionToken(session1.getId(), newAccessToken);
			session1.setAccessToken(newAccessToken);

			JwtResponse response = new JwtResponse(
			session.getId(),
			session1.getUser().getUsername(),
			session.getRefreshToken(),
			newAccessToken,
			session1.getUser().getCart().getId()
			);
			return response;
		})
		.orElseThrow(() -> {
			throw new NullPointerException("Invalid Token");
		});
	}

	public Boolean authorizeUser(Session session){
		Optional<Session> fetchedSession =
		sessionRepository.findByRefreshToken(session.getRefreshToken());
		return fetchedSession.map(value -> value.getAccessToken().equals(session.getAccessToken())).orElse(false);
	}


}
