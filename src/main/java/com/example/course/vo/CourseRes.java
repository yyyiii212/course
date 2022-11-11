package com.example.course.vo;

import java.util.List;

import com.example.course.entity.Course;
import com.example.course.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseRes {
	private String message;
	
	private Course course;
	
	private List<String> strList;

	private Student student;
	
	private List<Course> courseList;
	
	private Integer totalCredit;
	
	public CourseRes() {
		
	}

	public CourseRes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public Integer getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Integer totalCredit) {
		this.totalCredit = totalCredit;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
}
