package com.hyphen.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

	/**
	 * LOGIN WITH JWT IN SPRING SECURITY
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // by default spring security store credentials in cookies but we wabt JWT so, use STATELESS
		.authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()) // any url start with /api authorize to all
		.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
		.csrf().disable() // for CSRT Token disable from front 
		.cors().configurationSource(new CorsConfigurationSource() { // enable CORS 
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000",
						"http://localhost:4200",
						"http://localhost:8080"
				)); // It allow this API from front-end (website)
				
				cfg.setAllowedMethods(Collections.singletonList("*")); // Allow all CRUD methods
				cfg.setAllowCredentials(true); // for credentials
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		})
		.and().httpBasic().and().formLogin();
		return http.build();
	}
	
	/**
	 * ENCRYPT PASSWORD BEFORE SAVE
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}