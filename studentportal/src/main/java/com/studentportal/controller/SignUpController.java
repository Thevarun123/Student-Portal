package com.studentportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.bean.Course;
import com.studentportal.bean.SignupRequest;
import com.studentportal.bean.Student;
import com.studentportal.bean.StudentUser;
import com.studentportal.repository.StudentUserRepository;
import com.studentportal.response.handler.StudentResponse;
import com.studentportal.service.StudentServiceImpl;
import com.studentportal.service.jwt.StudentDetailsServiceImpl;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200/")
public class SignUpController {

	@Autowired
	StudentServiceImpl serviceImpl;
	
	@Autowired
	StudentDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveStudent(@RequestBody SignupRequest signupRequest) {
        
		// after role based auth
		
		StudentUser updatedStudent = detailsServiceImpl.save(signupRequest);
		
		// before role based auth
		// values are updated
//		StudentUser updatedStudent = new StudentUser();
//		updatedStudent.setName(signupRequest.getName());
//		updatedStudent.setEmail(signupRequest.getEmail());
//		updatedStudent.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//		
//		studentUserRepository.save(updatedStudent); // saving the student
		
		// saving the name and email in student table
		Student student = new Student();
		student.setStdName(signupRequest.getName());
		student.setStdEmail(signupRequest.getEmail());
          
		List<Course> courseList = new ArrayList<>();
		  courseList.add(new Course(""));
		  
		  student.setCourses(courseList);
		   
		serviceImpl.createStudent(student);
//		
		
		return StudentResponse
				.responseBuilder("Student added ", HttpStatus.OK, updatedStudent);

	}
}
