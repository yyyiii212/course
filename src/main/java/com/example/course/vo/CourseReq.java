package com.example.course.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseReq {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("course_name")
	private String courseName;

	private Integer week;

	private Integer start;

	private Integer end;

	private Integer credit;
	
	@JsonProperty("student_id")
	private String studentId;
	
	@JsonProperty("student_name")
	private String studentName;
	
	@JsonProperty("course_id")
	private List<String> courseId;
	
	public CourseReq() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<String> getCourseId() {
		return courseId;
	}

	public void setCourseId(List<String> courseId) {
		this.courseId = courseId;
	}

	
}
