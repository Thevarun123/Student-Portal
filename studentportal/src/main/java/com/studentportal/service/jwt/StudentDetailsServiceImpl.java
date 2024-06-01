package com.studentportal.service.jwt;

import java.io.Console;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentportal.bean.Role;
import com.studentportal.bean.SignupRequest;
import com.studentportal.bean.StudentUser;
import com.studentportal.repository.RoleRepository;
import com.studentportal.repository.StudentUserRepository;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {

	@Autowired
	StudentUserRepository studentRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		StudentUser student = studentRepository.getStudentUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Student not found."));

		System.out.println(student.getEmail() + " " + student.getPassword());
		return new User(student.getEmail(), student.getPassword(), mapRolesToAuthorities(student.getRoles()));
	}

	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	
	public StudentUser save(SignupRequest signupRequest) {
		Role role = roleRepository.findByRole("ROLE_USER");
		 
//		if (signupRequest.getRole().equals("USER"))
//			role = roleRepository.findByRole("ROLE_USER");
//		else if (signupRequest.getRole().equals("ADMIN"))
//			role = roleRepository.findByRole("ROLE_ADMIN");
        StudentUser user = new StudentUser();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setRoles(role);

		return studentRepository.save(user);
	}

	
}
