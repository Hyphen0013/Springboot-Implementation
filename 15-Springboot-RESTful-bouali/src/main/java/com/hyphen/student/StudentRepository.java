package com.hyphen.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // if you not annotate with repository spring boot know that it is repository due to JpaRepository extends
public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findByEmail(String email);
	
	int deleteByEmail(String email);
}
