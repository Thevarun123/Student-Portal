package com.studentportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentportal.bean.CourseDetails;
import com.studentportal.repository.CourseDetailsRepository;
import com.studentportal.repository.CourseRepository;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
public class CourseDetailsController {
	
	@Autowired
	CourseDetailsRepository courseDetailsRepository;
	
	
	// api to get course details
	@ResponseBody
	@RequestMapping(value = "/course/details", method = RequestMethod.GET)
	public List<CourseDetails> getCourseDetails() {
		return courseDetailsRepository.findAll();
		
	}

	// api to get course details by id
	@ResponseBody
	@RequestMapping(value = "/course/details/{id}", method = RequestMethod.GET)
	public CourseDetails getCourseDetailsById(@PathVariable("id") Integer id) throws RuntimeException {

		CourseDetails cd = courseDetailsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("course not found"));

		return cd;

	}

}
