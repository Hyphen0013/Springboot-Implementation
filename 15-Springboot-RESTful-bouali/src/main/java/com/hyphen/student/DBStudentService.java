package com.hyphen.student;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DBStudentService implements StudentService {

	private final StudentRepository studentRepository;

	public DBStudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	@Override
	public Student update(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void delete(String email) {
		System.out.println(email);
		studentRepository.deleteByEmail(email);
	}

}
