package com.example.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@Column(name = "id")
	private String studentId;

	@Column(name = "name")
	private String studentName;

	@Column(name = "course_id")
	private String courseId;

	public Student() {

	}

	public Student(String studentId, String studentName, String courseId) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.courseId = courseId;
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
}
