package com.hyphen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hyphen.entity.Student;

@Repository
//public interface StudentRepository extends CrudRepository<Student, Integer>  {}
public interface StudentRepository extends JpaRepository<Student, Integer> {}
