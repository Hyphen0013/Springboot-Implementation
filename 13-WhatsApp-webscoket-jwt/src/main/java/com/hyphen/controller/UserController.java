package com.hyphen.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hyphen.exception.UserException;
import com.hyphen.model.User;
import com.hyphen.request.UpdateUserRequest;
import com.hyphen.response.ApiResponse;
import com.hyphen.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(
		UserService userService
	) {
		this.userService = userService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler (
		@RequestHeader("Authorization") String token
	) throws UserException {
		User user = userService.findUserProfile(token);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/search")
	public ResponseEntity<HashSet<User>> searchUserByName(
			@RequestParam("name") String name,
			@RequestHeader("Authorization") String token
	) throws UserException {
		User user = userService.findUserProfile(token);
		
		List<User> users = userService.searchUserNotLoggedIn(name, user.getEmail());
//		List<User> users = userService.searchUser(name);
		
		HashSet<User> set = new HashSet<>(users);
		
		return new ResponseEntity<>(set, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{query}")
	public ResponseEntity<List<User>> searchUserHandler(
			@PathVariable("query") String query
	) {
		List<User> users = userService.searchUser(query);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateUserHandler(
			@RequestBody UpdateUserRequest req,
			@PathVariable Long id,
			@RequestHeader("Authorization") String token
	) throws UserException {
		User user = userService.findUserProfile(token);
		userService.updateUser(id, req);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("User updated successfully!");
		res.setStatus(true);		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/{userId}")
//	public ResponseEntity<User> getUserDetailsById(
//			@PathVariable Long userId,
//			@RequestHeader("Authorization") String token
//	) throws UserException, UserException {
//		System.out.println(userId);
//		
//		User user = userService.findUserById(userId);
//		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//	}	
}
