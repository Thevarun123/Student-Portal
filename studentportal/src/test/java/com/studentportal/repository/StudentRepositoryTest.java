package com.studentportal.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.studentportal.bean.Course;
import com.studentportal.bean.Student;

@DataJpaTest
public class StudentRepositoryTest {

	@Autowired
	StudentRepository studentRepository;

	Student student;

	Course course;

	@BeforeEach
	void setUp() {
		course = new Course(1, "Maths");
		student = new Student(1, "Varun Gupta", "varun9@gmail.com", List.of(course));
		studentRepository.save(student);
	}

	@AfterEach
	void tearDown() {
		course = null;
		student = null;
		studentRepository.deleteAll();
	}
	
	// test Success
	@Test
	void getStudentByStdEmail_found() {
		// given when then
		
		Optional<Student> studentOptional =  studentRepository.getStudentBystdEmail("varun9@gmail.com");
	    
		assertThat(studentOptional.get().getStdName()).isEqualTo(student.getStdName());

	}
	
	// failure
	@Test
	void getStudentByStdEmail_Notfound() {
		// given when then
		
		Optional<Student> studentOptional =  studentRepository.getStudentBystdEmail("shivam9@gmail.com");
		
		assertThat(studentOptional.isPresent()).isFalse();
	}
}
