package com.studentportal.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.studentportal.bean.Course;
import com.studentportal.bean.Role;
import com.studentportal.bean.Student;
import com.studentportal.repository.CourseDetailsRepository;
import com.studentportal.repository.CourseRepository;
import com.studentportal.repository.RoleRepository;
import com.studentportal.repository.StudentRepository;
import com.studentportal.repository.StudentUserRepository;
import com.studentportal.repository.UsersRoleRepository;
import com.studentportal.service.StudentService;
import com.studentportal.service.StudentServiceImpl;
import com.studentportal.service.jwt.StudentDetailsServiceImpl;
import com.studentportal.service.utils.JwtUtil;

import ch.qos.logback.core.net.ObjectWriter;
import ch.qos.logback.core.status.Status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	StudentRepository studentRepository;
	
	@MockBean
	StudentUserRepository studentUserRepository;

	@MockBean
	CourseRepository courseRepository;

	@MockBean
	CourseDetailsRepository courseDetailsRepository;
	
	@MockBean
	RoleRepository roleRepository;
	
	@MockBean
	UsersRoleRepository usersRoleRepository;
	
	@MockBean
	PasswordEncoder passwordEncoder;
	
	@MockBean
	StudentServiceImpl studentService;
	
	@MockBean
	StudentDetailsServiceImpl studentDetailsServiceImpl;

	@MockBean
	AuthenticationManager authentication;
	
	@MockBean
	JwtUtil jwtUtil;
	
	Student studentOne;
	Student studentTwo;

	List<Student> studentsList = new ArrayList<>();
	
	Set<Role> roles;
	Role roleOne;
	Role roleTwo;

	@BeforeEach
	void setUp() {
		roleOne = new Role(1, "ROLE_ADMIN");
		roleTwo = new Role(2, "ROLE_USER");
		roles = new HashSet<>();
		roles.add(roleOne);
		roles.add(roleTwo);
		studentOne = new Student(1, "Varun Gupta", "varun9@gmail.com", List.of(new Course(1, "Maths")));
		studentTwo = new Student(2, "Shivam Gupta", "shivam9@gmail.com", List.of(new Course(2, "English")));

		studentsList.add(studentOne);
		studentsList.add(studentTwo);
	}

	@AfterEach
	void tearDown() {

	}

	@Test
	public void getStudents() throws Exception {
		// Mock user authentication and authority
		Authentication authentication = mock(Authentication.class);
//		when(authentication.getAuthorities()).thenReturn((Collection<? extends GrantedAuthority>) roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()));

		// Mock SecurityContextHolder to return the mocked authentication
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		when(studentService.getAllStudents()).thenReturn(studentsList);

		this.mockMvc.perform(get("/students")).andDo(print()).andExpect(status().isOk());
	}

//	@Test
//	public void getStudentsById() throws Exception {
//		when(studentService.getStudents(1)).thenReturn(studentOne);
//
//		this.mockMvc.perform(get("/students/1")).andDo(print()).andExpect(status().isOk());
//
//	}
//
//	@Test
//	public void saveStudent() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = (ObjectWriter) mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ((ObjectMapper) ow).writeValueAsString(studentOne);
//
//		when(studentService.createStudent(studentOne)).thenReturn("Success");
//		
//		this.mockMvc.perform(post("/students/1"))
//		              .contentType(MediaType.APPLICATION_JSON)
//		              .content(requestJson))
//		              .andDo(print()).andExpect(status().isOk());
//	}
//
//	@Test
//	public void updateStudent()  throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = (ObjectWriter) mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ((ObjectMapper) ow).writeValueAsString(studentOne);
//
//		when(studentService.createStudent(studentOne)).thenReturn("Success");
//		
//		this.mockMvc.perform(put("/students/1"))
//		              .contentType(MediaType.APPLICATION_JSON)
//		              .content(requestJson))
//		              .andDo(print()).andExpect(status().isOk());
//	}
//
//	@Test
//	public void deleteStudent()  throws Exception {
//		when(studentService.deleteStudent(1)).thenReturn("Success");
//		
//		this.mockMvc.perform(delete("/students/1"))
//                        .andDo(print()).andExpect(status().isOk());
//	}
//
//	@Test
//	public void getStudentsByEmailId()  throws Exception {
//	     when(studentService.getStudentBystdEmail("varun9@gmail.com")).thenReturn(studentOne);
//		
//		this.mockMvc.perform(get("/students/email/varun9@gmail.com"))
//                        .andDo(print()).andExpect(status().isOk());
//	}

}
