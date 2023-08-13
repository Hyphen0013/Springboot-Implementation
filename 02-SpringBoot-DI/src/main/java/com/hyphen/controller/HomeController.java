package com.hyphen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyphen.service.StudentService;

@Controller
public class HomeController {
	
//	@Autowired
	private StudentService studentService; // remove @Autowired so use constructor injection
	
	@Autowired
	public HomeController(StudentService studentService) {
		System.out.println("constructor injection");
		this.studentService = studentService;
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/createS-student")
	public String registerStudent() {
		String msg = studentService.saveStudent();
		System.out.println(msg);
		return "success";
	}
}
