package com.studentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.bean.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
