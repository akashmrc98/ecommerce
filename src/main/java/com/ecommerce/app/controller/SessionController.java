package com.ecommerce.app.controller;

import com.ecommerce.app.domain.Session;
import com.ecommerce.app.dto.JwtResponse;
import com.ecommerce.app.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ecommerce/session")
@AllArgsConstructor
public class SessionController {
	private final SessionService sessionService;

	@PostMapping("/new-access-token")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
	public ResponseEntity<JwtResponse> generateNewSessionToken(@RequestBody Session session) {
		JwtResponse newJwtToken = sessionService.generateNewAccessToken(session);
		return new ResponseEntity<JwtResponse>(newJwtToken, HttpStatus.OK);
	}

	@PostMapping("/authenticate-token")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
	public ResponseEntity<Boolean> authenticateSessionToken(@RequestBody Session session) {
		Boolean isAuthenticated = sessionService.authorizeUser(session);
		return new ResponseEntity<Boolean>(isAuthenticated, HttpStatus.OK);
	}

}
