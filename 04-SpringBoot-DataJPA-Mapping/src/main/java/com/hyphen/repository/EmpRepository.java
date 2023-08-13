package com.hyphen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyphen.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{}
