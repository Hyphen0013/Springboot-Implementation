package com.hyphen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyphen.dto.LoginDTO;
import com.hyphen.dto.UserDTO;
import com.hyphen.response.LoginResponse;
import com.hyphen.service.UserService;

@RestController // This is for REST API
@CrossOrigin // This is for connecting Front-end
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/save")
	public String saveEmployee(@RequestBody UserDTO userDTO) {
		String id = userService.addUser(userDTO);
		return id;
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
		LoginResponse loginMessage = userService.loginUser(loginDTO);
		return ResponseEntity.ok(loginMessage);
	}
}
