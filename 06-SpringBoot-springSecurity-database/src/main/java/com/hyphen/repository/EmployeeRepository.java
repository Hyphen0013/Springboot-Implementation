package com.hyphen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyphen.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public Employee findByEmail(String email);
}
