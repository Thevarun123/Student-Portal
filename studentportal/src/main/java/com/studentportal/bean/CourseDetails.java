package com.studentportal.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "std_course_details")
public class CourseDetails {
	@Id
	@Column(name = "course_id")
	int courseId;
	@Column(name = "course_name")
	String courseName;


	public CourseDetails() {
		super();
	}


	public CourseDetails(String courseName) {
		super();
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
}
