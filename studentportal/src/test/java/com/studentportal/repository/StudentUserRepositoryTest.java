package com.studentportal.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Executable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.studentportal.bean.Role;
import com.studentportal.bean.StudentUser;
import com.studentportal.exception.StudentNotFoundException;

@DataJpaTest
public class StudentUserRepositoryTest {
   
	@Autowired
    StudentUserRepository studentUserRepository;
	
	StudentUser studentUser;
	
	Set<Role> role;
	
	@BeforeEach
	void setup() {
		role = new HashSet<>();
		role.add(new Role(1, "ROLE_USER"));
		studentUser = new StudentUser(1l, "Varun Gupta", "varun9@gmail.com", "varungupta", role);
		studentUserRepository.save(studentUser);
	}
	
	@AfterEach
	void tearDown() {
           role = null;
           studentUser = null;
           studentUserRepository.deleteAll();           
	}
	
	// given when then 
	
	// Test success
	@Test
	 void getStudentUserByEmail_Found() {
		Optional<StudentUser> studentuserOptional = studentUserRepository
				                                         .getStudentUserByEmail("varun9@gmail.com");
	 
	    assertThat(studentuserOptional.get().getName()).isEqualTo(studentUser.getName());
	 }
	
	// Test failure
	@Test
	void getStudentUserByEmail_NotFound() {
		Optional<StudentUser> studentUserOptional = studentUserRepository
                .getStudentUserByEmail("shivam9@gmail.com");

		assertThat(studentUserOptional.isPresent()).isFalse();

		
	}
	
}
