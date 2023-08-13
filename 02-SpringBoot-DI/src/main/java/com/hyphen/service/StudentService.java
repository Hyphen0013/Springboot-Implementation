package com.hyphen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hyphen.repository.StudentDao;

//@Component
@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao; // this is called filed injection
	
	public String saveStudent() {
		boolean check = studentDao.save();
		if(check) {
			return "Regsitration successfull change";
		} else {
			return "Regsitration failed";
		}
	}
}
