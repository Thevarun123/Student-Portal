package com.studentportal.bean;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int stdId; // auto generated
	@Column(name = "student_name")
	String stdName;
	@Column(name = "student_email")
	String stdEmail;
	

	
	// oneToMany mapping
	@OneToMany(fetch = FetchType.LAZY,  targetEntity = Course.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id_no", referencedColumnName = "student_id")
	List<Course> course;


	public Student(int stdId, String stdName, String stdEmail, List<Course> course) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.stdEmail = stdEmail;
		this.course = course;
	}

	public List<Course> getCourses() {
		return course;
	}

	public void setCourses(List<Course> course) {
		this.course = course;
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

	public void setStdEmail(String stdCourse) {
		this.stdEmail = stdCourse;
	}

	@Override
	public String toString() {
		return "Student [stdId=" + stdId + ", stdName=" + stdName + ", stdEmail=" + stdEmail + "]";
	}

	public Student() {
		super();
	}
}
