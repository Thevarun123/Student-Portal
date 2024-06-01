package com.studentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jayway.jsonpath.Option;
import com.studentportal.bean.Student;
import com.studentportal.bean.StudentUser;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	Optional<Student> getStudentBystdEmail(String email); 
	
}