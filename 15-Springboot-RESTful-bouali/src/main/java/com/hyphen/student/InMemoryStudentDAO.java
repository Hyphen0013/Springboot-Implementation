package com.hyphen.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

@Repository // because it getting, updating, deleting, save data so, it make Repository Bean
public class InMemoryStudentDAO {

	private final List<Student> studentsArr = new ArrayList<>();
	
	public List<Student> findAllStudents() {
		return studentsArr;
	}

	public Student save(Student student) {
		studentsArr.add(student);
		return student;
	}

	public Student findByEmail(String email) {
		return studentsArr.stream()
				.filter(stud -> email.equals(stud.getEmail()))
				.findFirst()
				.orElse(null);
	}

	public Student update(Student student) {
		var studentIndex = IntStream.range(0, studentsArr.size())
				.filter(index -> studentsArr.get(index).getEmail().equals(student.getEmail()))
				.findFirst()
				.orElse(-1);
		
		if(studentIndex > -1) {
			studentsArr.set(studentIndex, student);
			return student;
		}
		return null;
	}

	public void delete(String email) {
		var student = findByEmail(email);
		if(student != null) {
			studentsArr.remove(student);
		}
	}
}
