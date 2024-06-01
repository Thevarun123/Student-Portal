package com.studentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentportal.bean.Student;
import com.studentportal.bean.StudentUser;


public interface StudentUserRepository extends JpaRepository<StudentUser, Long>{

	 Optional<StudentUser> getStudentUserByEmail(String email); 

//	 void deleteStudentUserByEmail(String email);
}
