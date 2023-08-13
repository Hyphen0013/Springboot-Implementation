package com.hyphen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hyphen.entity.User;
import com.hyphen.repository.UserRepository;
import com.hyphen.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/save-user")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		
		User saveUsr = userService.saveUser(user);
		if(saveUsr != null) {
			session.setAttribute("successMsg", "User registered successfully");
		} else {
			session.setAttribute("errorMsg", "User registration failed!");
		}
		return "redirect:/register";
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
