package com.hyphen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hyphen.entity.Employee;
import com.hyphen.repository.EmployeeRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Employee employee = employeeRepository.findByEmail(email);
		if(employee == null) {
			throw new UsernameNotFoundException("User email not found!");
		} else {
			return new CustomUser(employee);
		}
	}
	
}
