package com.studentportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentportal.bean.Role;
import com.studentportal.bean.StudentUser;
import com.studentportal.repository.RoleRepository;
import com.studentportal.repository.StudentUserRepository;


public class StudentUserController {
	
	
	@Autowired
	StudentUserRepository studentUserRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	// an api just to update the role of previous students already logged in to the
	// system
	@RequestMapping(value = "students/{studentId}/roles", method = RequestMethod.PUT)
	@ResponseBody
	public void updateExistingStudentsRole(@PathVariable("studentId") Long id) {

		StudentUser studentUser = studentUserRepository.findById(id).get();
		Role role = roleRepository.findByRole("ROLE_USER");
		studentUser.setRoles(role);

		studentUserRepository.save(studentUser);

		System.out.println(studentUser.getRoles().toString());
	}

	@RequestMapping(value = "studentsUser/{studentId}", method = RequestMethod.GET)
	@ResponseBody
	public StudentUser getStudentUserById(@PathVariable("studentId") Long id) {

		StudentUser studentUser = studentUserRepository.findById(id).get();

		return studentUser;
	}
}
