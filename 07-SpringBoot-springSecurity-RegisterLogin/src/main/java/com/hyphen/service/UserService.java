package com.hyphen.service;

import com.hyphen.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	public void removeSuccessSessionMessage(); // remove session from thymeleaf
	public void removeErrorSessionMessage(); // remove session from thymeleaf
}
