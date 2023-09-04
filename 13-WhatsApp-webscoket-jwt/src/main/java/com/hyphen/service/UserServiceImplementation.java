package com.hyphen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.hyphen.config.TokenProvider;
import com.hyphen.exception.UserException;
import com.hyphen.model.User;
import com.hyphen.repository.UserRepository;
import com.hyphen.request.UpdateUserRequest;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	public UserServiceImplementation(
		UserRepository userRepository,
		TokenProvider tokenProvider
	) {
		this.userRepository = userRepository;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public User findUserById(Long id) throws UserException {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User not found wit id: " + id);
	}

	@Override
	public User findUserProfile(String jwt) throws UserException {
		String email = tokenProvider.getEmailFromToken(jwt);
		
		if(email == null) {
			throw new BadCredentialsException("Received invalid token!");
		}
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("User not found with email " + email);
		}
		return user;
	}

	@Override
	public User updateUser(Long userId, UpdateUserRequest req) throws UserException {
		User user = findUserById(userId);
		
		if(req.getFull_name() != null) {
			user.setFull_name(req.getFull_name());
		}
		if(req.getProfile_picture() != null) {
			user.setProfile_picture(req.getProfile_picture());
		}		
		return userRepository.save(user);
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users = userRepository.searchUser(query);
		return users;
	}

	@Override
	public List<User> searchUserNotLoggedIn(String query, String email) throws UserException {
		List<User> users = userRepository.searchUserNotLoggedIn(query, email);
		return users;
	}
	

}
