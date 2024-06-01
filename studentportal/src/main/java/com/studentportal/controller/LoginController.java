package com.studentportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.bean.LoginRequest;
import com.studentportal.bean.LoginResponse;
import com.studentportal.service.jwt.StudentDetailsServiceImpl;
import com.studentportal.service.utils.JwtUtil;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {

	@Autowired
	StudentDetailsServiceImpl studentDetailsServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
//	
//	@Autowired
//	Authentication authenticationUser;
	
	@RequestMapping(method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){

//		Authentication authenticationUser = authenticationManager.authenticate(null);		 
//		try {
//        	Authentication authenticationUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        }catch (AuthenticationException e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
		
    	Authentication authenticationUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticationUser);
		
        UserDetails userDetails;
        try {
			userDetails = studentDetailsServiceImpl.loadUserByUsername(loginRequest.getEmail());
            System.out.println("From login controller " + userDetails.getUsername() + " " + userDetails.getPassword());
        } catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
        
         String jwt = jwtUtil.generateToken(authenticationUser); // will generate jwt token using username i.e email of the student
         System.out.println(jwt);
         
         List<LoginResponse> res = List.of(new LoginResponse(jwt));
         
         return ResponseEntity.ok(res);
	}
}
