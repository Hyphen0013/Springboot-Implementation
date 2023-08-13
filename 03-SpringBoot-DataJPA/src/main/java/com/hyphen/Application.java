package com.hyphen;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hyphen.entity.Student;
import com.hyphen.repository.StudentRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		StudentRepository stRepo = context.getBean(StudentRepository.class);
		
		Student student = new Student();
		student.setName("From Spring Boot");
		student.setLevel("Student");
		student.setEmail("springboot@gmail.com");
		student.setPassword("123456");
		student.setDob(null);
		student.setCreatedAt(null);
		student.setUpdated_at(null);
		
//		stRepo.save(student); // Save
		
		List<Student> list = (List<Student>) stRepo.findAll();
		
		Student getSt = stRepo.findById(14).get(); // Find 
		
		getSt.setName("Spring Boot JPA"); // Update
		stRepo.save(getSt); // Update then save
		
//		stRepo.delete(getSt); // Delete
		System.out.println(getSt.getName());
		
//		SORTING
		
		
//		PAGINATIONS
		Sort sort = Sort.by("name").ascending();
		Pageable pageable = PageRequest.of(0, 3, sort);
		Page<Student> page = stRepo.findAll(pageable);
		
		page.get().forEach(e -> System.out.println(e));
		
		List<Student> listSort = stRepo.findAll(sort);
		listSort.forEach(e -> System.out.println(e));
	}

}
