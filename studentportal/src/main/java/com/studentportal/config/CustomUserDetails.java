//package com.studentportal.config;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.studentportal.bean.Student;
//
//public class CustomUserDetails implements UserDetails {
//
//	
//	Student student;
//	
//	public CustomUserDetails(Student student) {
//		this.student = student;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority authority =  new SimpleGrantedAuthority();
//        return List.of(authority);	    
//	}
//
//	@Override
//	public String getPassword() {
//	    return student.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		  return student.getStdEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
