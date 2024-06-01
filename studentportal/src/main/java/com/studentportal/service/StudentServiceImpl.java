package com.studentportal.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;
import java.util.Optional;

import javax.swing.plaf.ColorUIResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import com.studentportal.bean.Course;
import com.studentportal.bean.Student;
import com.studentportal.exception.StudentNotFoundException;
import com.studentportal.repository.CourseRepository;
import com.studentportal.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	// Custom CRUD operations to access them in the controller

//	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	// Get student
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudents(Integer id) {

		Student student1 = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Requested student does not exists."));

		return student1;
	}

	@Override
	public String createStudent(Student student1) {

		studentRepository.save(student1);
		return "Success";
	}

	@Override
	public Student updateStudent(Student student1) {

		return studentRepository.save(student1);
	}

	// patch api
	@Override
	public Student modifyStudent(Student student1) {

		return studentRepository.save(student1);
	}

	@Override
	public String deleteStudent(Integer id) {
		studentRepository.deleteById(id);
		return "Success";
	}

	public Course getStudentsAndCourses(Integer id) {
		Student student1 = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Requested student course does not exists."));

		return courseRepository.findById(id).get();
	}

	public Student getStudentBystdEmail(String email) {
		Student student = studentRepository.getStudentBystdEmail(email)
				.orElseThrow(() -> new StudentNotFoundException("Requested student does not exists"));

		return student;
	}

}
