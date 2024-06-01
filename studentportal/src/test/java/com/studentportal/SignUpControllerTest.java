//package com.studentportal;
//
//
//import static org.mockito.Mockito.*;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.studentportal.bean.SignupRequest;
//import com.studentportal.bean.StudentUser;
//import com.studentportal.controller.SignUpController;
//import com.studentportal.service.StudentServiceImpl;
//import com.studentportal.service.jwt.StudentDetailsServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SignUpControllerTest {
//
//	@InjectMocks
//	SignUpController signUpController;
//
//	@Mock
//	StudentDetailsServiceImpl detailsServiceImpl;
//
//	@Mock
//	StudentServiceImpl serviceImpl;
//
//	@Mock
//	PasswordEncoder passwordEncoder;
//
//	@Before(value = "")
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testSaveStudent_Success() {
//		SignupRequest signupRequest = new SignupRequest();
//		// Set up signupRequest with required data
//
//		StudentUser updatedStudent = new StudentUser();
//		// Set up updatedStudent with required data
//
//		// Mock behavior of detailsServiceImpl.save(signupRequest)
//		when(detailsServiceImpl.save(signupRequest)).thenReturn(updatedStudent);
//
//		// Mock behavior of passwordEncoder.encode(signupRequest.getPassword())
//		when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("encodedPassword");
//
//		// Mock behavior of serviceImpl.createStudent(student)
//		doNothing().when(serviceImpl).createStudent(any(Student.class));
//
//		// Call the method under test
//		ResponseEntity<Object> response = signUpController.saveStudent(signupRequest);
//
//		// Verify that the response is as expected
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		// Add more assertions as needed
//	}
//
//	// Add more test cases for other scenarios like validation failure, exceptions,
//	// etc.
//}