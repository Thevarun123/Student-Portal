package com.studentportal.response.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StudentResponse {
	// Get(all students) method
	public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object result) {

		Map<String, Object> response = new HashMap<>();
		response.put("data", result);
		response.put("status", httpStatus);
		response.put("message", message);

		return new ResponseEntity<>(response, httpStatus);
	}
}
