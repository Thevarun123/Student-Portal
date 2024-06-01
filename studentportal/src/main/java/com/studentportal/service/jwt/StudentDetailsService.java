package com.studentportal.service.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.studentportal.bean.SignupRequest;
import com.studentportal.bean.StudentUser;

public interface StudentDetailsService extends UserDetailsService{
	StudentUser save(SignupRequest request);
}
