package com.studentportal.service;

import java.util.List;
import java.util.Optional;

import com.studentportal.bean.Course;
import com.studentportal.bean.Student;

public interface StudentService {
//  Get student
	public List<Student> getAllStudents();

//  Get student by id
	public Student getStudents(Integer id);

	public Course getStudentsAndCourses(Integer id);

//  Post student
	public String createStudent(Student student1);

//  Put student
	public Student updateStudent(Student student);

//  Patch student
	public Student modifyStudent(Student student);

//  Delete student
	public String deleteStudent(Integer id);
	
	public Student getStudentBystdEmail(String email);
}
