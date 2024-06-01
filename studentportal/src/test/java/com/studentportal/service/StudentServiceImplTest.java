package com.studentportal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.studentportal.bean.Course;
import com.studentportal.bean.Student;

import com.studentportal.repository.CourseRepository;
import com.studentportal.repository.StudentRepository;

public class StudentServiceImplTest {

	@Mock
	StudentRepository studentRepository;
	CourseRepository courseRepository;
	StudentServiceImpl studentService;
	AutoCloseable autoCloseable;
	Student student;
	Course course;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		studentService = new StudentServiceImpl(studentRepository);
		course = new Course(1, "Maths");
		student = new Student(1, "Varun Gupta", "varun9@gmail.com", List.of(course));

	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	// mock -> when -> then

    @Test
	public void testGetAllStudents() {
    	mock(StudentRepository.class);
    	mock(StudentService.class);
    	
    	when(studentRepository.findAll()).thenReturn(new ArrayList<Student>(
                Collections.singleton(student)
        ));
    	
    	assertThat(studentService.getAllStudents().get(0).getStdName())
    	                  .isEqualTo(student.getStdName());
	}

	@Test
	public void testGetStudents() {
		mock(Student.class);
		mock(StudentRepository.class);

		when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));

		assertThat(studentService.getStudents(1).getStdName()).isEqualTo(student.getStdName());
	}

	@Test
	public void testCreateStudent() {
		mock(StudentRepository.class);
		mock(Student.class);

		when(studentRepository.save(student)).thenReturn(student);

		assertThat(studentService.createStudent(student)).isEqualTo("Success");
	}

	@Test
	public void testUpdateStudent() {
		mock(StudentRepository.class);
		mock(Student.class);

		when(studentRepository.save(student)).thenReturn(student);

		assertThat(studentService.updateStudent(student).getStdId()).isEqualTo(student.getStdId());
	}

	@Test
	public void testDeleteStudent() {
		mock(StudentRepository.class);
		mock(Student.class, Mockito.CALLS_REAL_METHODS);

		doAnswer(Answers.CALLS_REAL_METHODS).when(studentRepository).deleteById(1);

		assertThat(studentService.deleteStudent(1)).isEqualTo("Success");
	}

	@Test
	public void testGetStudentBystdEmail() {
		mock(StudentRepository.class);
		mock(Student.class);
		
		when(studentRepository.getStudentBystdEmail("varun9@gmail.com"))
		                            .thenReturn(Optional.ofNullable(student));
		
		assertThat(studentService.getStudentBystdEmail("varun9@gmail.com").getStdName())
		                              .isEqualTo(student.getStdName());
	}
}
