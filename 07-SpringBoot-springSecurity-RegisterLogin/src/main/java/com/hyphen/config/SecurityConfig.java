package com.hyphen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	public CustomAuthSucessHandler sucessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getdeDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getdeDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		Simple User Authorized
		/*
		 * http.csrf().disable() .authorizeHttpRequests().requestMatchers("/",
		 * "/register", "/signin", "/save-user").permitAll()
		 * .requestMatchers("/user/**").authenticated().and()
		 * .formLogin().loginPage("/signin").loginProcessingUrl("/user-login") //
		 * .usernameParameter("email") .defaultSuccessUrl("/user/home").permitAll();
		 */
		
//		Role based Authentication
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/**").permitAll().and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/user-login")
		.successHandler(sucessHandler)
//		.and().logout()
		.permitAll();
		
		return http.build();
	}
}
