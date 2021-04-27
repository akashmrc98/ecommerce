package com.ecommerce.app.config;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import com.ecommerce.app.repository.VendorRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final VendorRepository vendorRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and().csrf().disable();

		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and();

		http
			.exceptionHandling()
			.authenticationEntryPoint(
				(request, response, ex) ->{
					response.sendError(
					HttpServletResponse.SC_UNAUTHORIZED,
					ex.getMessage()
					);
				}
			)
		.and();

		http
			.authorizeRequests()
			.antMatchers("/api/v1/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/**").permitAll()
			.antMatchers(HttpMethod.PUT, "/api/v1/**").permitAll()
			.antMatchers(HttpMethod.DELETE, "/api/v1/**").permitAll()
			.anyRequest().authenticated();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter();
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(10);
	}

}
