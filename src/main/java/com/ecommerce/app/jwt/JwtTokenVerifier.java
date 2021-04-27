package com.ecommerce.app.jwt;


import com.ecommerce.app.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AllArgsConstructor
@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

	private final VendorRepository vendorRepository;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	) throws ServletException, IOException
	{

		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if(header.isEmpty() || !header.startsWith("Bearer ")){
			filterChain.doFilter(request, response);
			return;
		}


	}
}
