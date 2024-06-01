package com.studentportal.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.swing.CellEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.bean.Course;
import com.studentportal.bean.CourseDetails;
import com.studentportal.bean.Role;
import com.studentportal.bean.SignupRequest;
import com.studentportal.bean.Student;
import com.studentportal.bean.StudentUser;
import com.studentportal.bean.StudentWithPasswordDTO;
import com.studentportal.exception.StudentNotFoundException;
import com.studentportal.repository.CourseDetailsRepository;
import com.studentportal.repository.CourseRepository;
import com.studentportal.repository.RoleRepository;
import com.studentportal.repository.StudentRepository;
import com.studentportal.repository.StudentUserRepository;
import com.studentportal.repository.UsersRoleRepository;
import com.studentportal.response.handler.StudentResponse;
import com.studentportal.service.StudentServiceImpl;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {

	@Autowired
	PasswordEncoder passwordEncoder; // to encode the password

	@Autowired
	StudentServiceImpl serviceImpl; // to perform crud operations

	@Autowired
	StudentRepository studentRepository; //

	@Autowired
	StudentUserRepository studentUserRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseDetailsRepository courseDetailsRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UsersRoleRepository usersRoleRepository;
	
	
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
 // only admin can access all students data
	public ResponseEntity<Object> getStudents() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null && authentication.getAuthorities().stream()
	            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
			
			return new StudentResponse().responseBuilder("Requested detail of students is :", HttpStatus.OK,
					serviceImpl.getAllStudents());
			
		}
		
		return new StudentResponse().responseBuilder("Requested detail of students is :", HttpStatus.OK,
				serviceImpl.getStudentBystdEmail(authentication.getName()));
		
//		return serviceImpl.getAllStudents();
	}

	@RequestMapping(value = "students/{studentId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getStudentsById(@AuthenticationPrincipal UserDetails userDetails,
			@PathVariable("studentId") Integer id) {

		// for allowing to view any student details 
		
		 Collection<? extends GrantedAuthority> isAdmin = userDetails.getAuthorities();
		 System.out.println("is this admin"+ isAdmin);  
		 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		 
//		String authenticatedStudent = userDetails.getUsername();

		Student requestedStudent = serviceImpl.getStudents(id);

//		System.out.println("authenticated user: " + authenticatedStudent);
		System.out.println("email of requested student " + requestedStudent.getStdEmail());
		System.out.println("email from authentication getName " + authentication.getName());
		

         // check if authentication in not null and has ROLE_ADMIN 		
		if (authentication != null && authentication.getAuthorities().stream()
	            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
			
//			usersRoleRepository.deleteById((long) 32); // working in the users_role table
			
			return new StudentResponse().responseBuilder("Requested detail of students is :", HttpStatus.OK,
					serviceImpl.getStudents(id));
		}
		
		if (!authentication.getName().equals(requestedStudent.getStdEmail())) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
		}
        
        
		return new StudentResponse().responseBuilder("Requested detail of students is :", HttpStatus.OK,
				serviceImpl.getStudents(id));
	}

	// url: students/1/courses
	@RequestMapping(value = "students/{studentId}/courses/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getStudentsByIdAndCourseId(@PathVariable("courseId") Integer id) {

		return new StudentResponse().responseBuilder("Requested detail of courses:", HttpStatus.OK,
				serviceImpl.getStudentsAndCourses(id));

	}

	@RequestMapping(value = "/students", method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Object> saveStudent(@RequestBody StudentWithPasswordDTO student) {

		// values are updated
		Student updatedStudent = new Student();
		updatedStudent.setStdName(student.getStdName());
		updatedStudent.setStdEmail(student.getStdEmail());

		List<Course> coursesList = new ArrayList<>();
		for (Course c : student.getCourses()) {

			Course updatedCourse = new Course(c.getCourseName());
//			updatedCourse.setStudent(updatedStudent); // linking
			coursesList.add(updatedCourse);
		}

		updatedStudent.setCourses(coursesList);

		serviceImpl.createStudent(updatedStudent); // saving the student in student table
		
		// saving the same student in student_user table
		StudentUser studentUser = new StudentUser();
		studentUser.setName(student.getStdName());
		studentUser.setEmail(student.getStdEmail());
        studentUser.setPassword(passwordEncoder.encode(student.getPassword()));
        studentUser.setRoles(roleRepository.findByRole("ROLE_USER"));
		
        studentUserRepository.save(studentUser);
        
		return StudentResponse.responseBuilder("Student added ", HttpStatus.OK, updatedStudent);

	}

	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Integer id,
			@RequestBody Student updatedStudent) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Student requestedStudent = serviceImpl.getStudents(id);
	            
		if (authentication.getAuthorities().stream()
	            .anyMatch(r -> r.getAuthority().equals("ROLE_USER")) && 
	            !authentication.getName().equals(requestedStudent.getStdEmail())) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
		}
		
		Student student = serviceImpl.getStudents(id);

		// updating name and email in the student_user table
		StudentUser studentUser = studentUserRepository.getStudentUserByEmail(student.getStdEmail())
				.orElseThrow(() -> new RuntimeException("not found"));

		studentUser.setEmail(updatedStudent.getStdEmail());
		studentUser.setName(updatedStudent.getStdName());

		studentUserRepository.save(studentUser);

		student.setStdName(updatedStudent.getStdName());
		student.setStdEmail(updatedStudent.getStdEmail());

		List<Course> courseList = new ArrayList<>();
		for (Course c : updatedStudent.getCourses()) {

			Course updateCourse = new Course(c.getCourseName());

			courseList.add(updateCourse);

		}

		student.setCourses(courseList);

		return StudentResponse.responseBuilder("Student updated ", HttpStatus.OK, serviceImpl.updateStudent(student));

	}

	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteStudent(@PathVariable("studentId") Integer id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// logic to throw custom exception if the student is not present with given id
		Student student = serviceImpl.getStudents(id);

//		// first deleting from the user_role -> student_user -> student;
//		StudentUser studentUser = studentUserRepository.getStudentUserByEmail(student.getStdEmail())
//				.orElseThrow(() -> new RuntimeException("Student not exists"));
//////
//		Long std_id = studentUser.getId();
//		if (authentication != null && authentication.getAuthorities().stream()
//				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
//			
//			usersRoleRepository.deleteById(std_id); // detach from users_role table. 
//		}
////		roleRepository.deleteById(studentUser.getId());
//		studentUserRepository.deleteById(studentUser.getId()); // deleting the student from student_user table also.
		
		
		serviceImpl.deleteStudent(id); // deleting from the student table


	} 

	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.PATCH) // if error shows Patch not supported
																					// means problem is in the url
	@ResponseBody
	public ResponseEntity<Object> Student(@PathVariable("studentId") Integer id, @RequestBody Student updatedStudent) {
		// logic to throw custom exception if the student is not present with given id

		Student student = serviceImpl.getStudents(id); // throw error if no student exist with given id // success

		student.setStdEmail(updatedStudent.getStdEmail());

		return new StudentResponse().responseBuilder("Course updated", HttpStatus.OK,
				serviceImpl.modifyStudent(student));

	}


	// get student by email
	@RequestMapping(value = "students/email/{email}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getStudentsByEmailId(@PathVariable("email") String email) {

		return new StudentResponse().responseBuilder("Requested detail of students is :", HttpStatus.OK,
				serviceImpl.getStudentBystdEmail(email));

	}
	


}
