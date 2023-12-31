package com.hyphen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyphen.config.TokenProvider;
import com.hyphen.exception.UserException;
import com.hyphen.model.User;
import com.hyphen.repository.UserRepository;
import com.hyphen.request.LoginRequest;
import com.hyphen.response.AuthResponse;
import com.hyphen.service.CustomUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired CustomUserService customUserService;
	
//	public AuthController(
//		UserRepository userRepository
//	) {
//		this.userRepository = userRepository;
//	}
	
	/**
	 * USER REGISTRATION
	 */
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler (
		@RequestBody User user
	) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFull_name();
		
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("Email is already used with another user account!");
		}	
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFull_name(fullName);	
		
		User savedUser = userRepository.save(createdUser);		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
				
		String token = tokenProvider.generateToken(authentication);
		
		String message = "User signup successfully";
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage(message);
		authResponse.setStatus(true);	
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}
	
	/**
	 * USER LOGIN
	 */	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.generateToken(authentication);
		
		String message = "User signin successfully";
				
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage(message);
		authResponse.setStatus(true);	
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.ACCEPTED);
	}
	
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserService.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not mathced");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}	
}
