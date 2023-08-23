package com.hyphen.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this annotation for controller
@RequestMapping("/api/vi") // this is for route
public class StudentController {
	
	//private StudentService studentService = new StudentService(); // this is example of Tight coupling in spring boot
	
	/**
	 * Field Injection
	 * Loose Coupling 
	 * Automatic Dependency Injection
	 */
//	@Autowired
	private StudentService studentService;
	
	/**
	 * Constructor Injection
	 */
	public StudentController(@Qualifier("DBStudentService") StudentService studentService) {
//	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/students")
	public Student save(@RequestBody Student student) {
		return studentService.save(student);
	}
	
	@GetMapping("/students") // route for get method
	public List<Student> findAllStudents() {
			
		return studentService.findAllStudents();
	}
	
	@GetMapping("/students/{email}")
	public Student findByEmail(@PathVariable("email") String em) {
		return studentService.findByEmail(em);
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.update(student);
	}
	
	@DeleteMapping("/students/{email}")
	public void delete(@PathVariable("email") String em) {
		studentService.delete(em);
	}
	
	
}
