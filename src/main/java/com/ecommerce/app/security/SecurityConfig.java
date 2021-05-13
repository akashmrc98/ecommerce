package com.ecommerce.app.security;

import com.ecommerce.app.authentication.AuthUserService;
import com.ecommerce.app.authentication.filters.JwtAuthenticationFilter;
import com.ecommerce.app.authentication.filters.JwtTokenFilter;
import com.ecommerce.app.jwt.JwtTokenGenerator;
import com.ecommerce.app.jwt.JwtTokenVerifier;
import com.ecommerce.app.repository.SessionRepository;
import com.ecommerce.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthUserService authUserService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final JwtTokenVerifier jwtTokenVerifier;
	private final SessionRepository sessionRepository;
	private final UserRepository userRepository;

	@Autowired
	public SecurityConfig(
	PasswordEncoder passwordEncoder,
	AuthUserService authUserService,
	JwtTokenGenerator jwtTokenGenerator,
	JwtTokenVerifier jwtTokenVerifier,
	SessionRepository sessionRepository,
	UserRepository userRepository
	) {
		this.authUserService = authUserService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenGenerator = jwtTokenGenerator;
		this.jwtTokenVerifier = jwtTokenVerifier;
		this.sessionRepository = sessionRepository;
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenGenerator,
		sessionRepository, userRepository))
		.addFilterAfter(new JwtTokenFilter(jwtTokenVerifier), JwtAuthenticationFilter.class)
		.authorizeRequests()
		.anyRequest()
		.authenticated();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(authUserService);
		return provider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

}
