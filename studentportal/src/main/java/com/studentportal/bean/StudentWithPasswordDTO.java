package com.studentportal.bean;

import java.util.List;

public class StudentWithPasswordDTO {

	int stdId;
	String stdName;
	String stdEmail;
	List<Course> courses;
	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public StudentWithPasswordDTO() {
		super();
	}

	public int getStdId() {
		return stdId;
	}

	public void setStdId(int stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdEmail() {
		return stdEmail;
	}

	public void setStdEmail(String stdEmail) {
		this.stdEmail = stdEmail;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourse(List<Course> course) {
		this.courses = course;
	}
}
