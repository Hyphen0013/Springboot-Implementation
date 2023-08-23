package com.hyphen.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyphen.config.JwtService;
import com.hyphen.token.TokenRepository;
import com.hyphen.user.User;
import com.hyphen.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private UserRepository repository;
	private TokenRepository tokenRepository;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
//		var user = User.builder()
//				.firstname(request.getFirstname())
//				.lastname(request.getLastname())
//				.email(request.getEmail())
//				.password(passwordEncoder.encode(request.getPassword()))
//				.role(request.getRole())
//				.build();

		return null;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		return null;
	}
}
