package com.hyphen.service;

import java.util.List;

import com.hyphen.exception.UserException;
import com.hyphen.model.User;
import com.hyphen.request.UpdateUserRequest;

public interface UserService {

	public User findUserById(Long id) throws UserException;
	
	public User findUserProfile(String jwt) throws UserException;
	
	public User updateUser(Long userId, UpdateUserRequest req) throws UserException;
	
	public List<User> searchUser(String query);
	
	public List<User> searchUserNotLoggedIn(String query, String email) throws UserException;
}
