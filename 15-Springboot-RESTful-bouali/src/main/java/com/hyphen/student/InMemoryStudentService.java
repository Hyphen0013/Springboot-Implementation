package com.hyphen.student;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service // provide some business
//@Primary
public class InMemoryStudentService implements StudentService {
	
	private final InMemoryStudentDAO studentDao;
	
	/**
	 * Constructor Injection
	 */
	public InMemoryStudentService(InMemoryStudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> findAllStudents() {
//		List<Student> studentList = new ArrayList<>();
//		
//		Student student = new Student();
//		student.setName("Hyphen");
//		student.setLevel("student");
//		student.setEmail("hyphen.call@gmail.com");
//		student.setPassword("123456");
//		student.setDob(LocalDate.now());	
//		
//		studentList.add(student);
		return studentDao.findAllStudents();
	}

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Student findByEmail(String email) {
		return studentDao.findByEmail(email);
	}

	@Override
	public Student update(Student student) {
		return studentDao.update(student);
	}

	@Override
	public void delete(String email) {
		System.out.println(email);
		studentDao.delete(email);
	}

}
