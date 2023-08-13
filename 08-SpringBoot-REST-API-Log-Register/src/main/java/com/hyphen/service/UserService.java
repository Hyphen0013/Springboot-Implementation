package com.hyphen.service;

import com.hyphen.dto.LoginDTO;
import com.hyphen.dto.UserDTO;
import com.hyphen.response.LoginResponse;

public interface UserService {
	String addUser(UserDTO userDTO);
	
	LoginResponse loginUser(LoginDTO loginDTO);
}
