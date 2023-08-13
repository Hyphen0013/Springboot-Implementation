package com.hyphen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyphen.entity.User;
import com.hyphen.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/home")
	public String home() {		
		return "home";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if(p != null) {
			String email = p.getName();
			User user = userRepository.findByEmail(email);
			
			m.addAttribute("user", user);				
		}
	}
}
