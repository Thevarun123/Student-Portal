//package com.studentportal.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.studentportal.bean.Student;
//import com.studentportal.repository.StudentRepository;
//
//public class UserDetailServiceImpl implements UserDetailsService{
//
//	@Autowired
//	StudentRepository repository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//	  Student student =	repository.getStudentByUsername(username);
//	  if(student == null) {
//		  throw new UsernameNotFoundException("Student doesn not exist. ");
//	  }
//	  
//	  
//      CustomUserDetails customUserDetails = new CustomUserDetails(student);
//      return customUserDetails;
//	}
//
//}
