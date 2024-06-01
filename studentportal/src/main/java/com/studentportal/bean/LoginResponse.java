package com.studentportal.bean;

public class LoginResponse {

	
	String jwt;

	public LoginResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
