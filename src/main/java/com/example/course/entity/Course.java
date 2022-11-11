package com.example.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String courseName;

	@Column(name = "week")
	private int week;

	@Column(name = "start")
	private int start;

	@Column(name = "end")
	private int end;

	@Column(name = "credit")
	private int credit;

	public Course() {

	}

	public Course(String id, String courseName, int week, int start, int end, int credit) {
		this.id = id;
		this.courseName = courseName;
		this.week = week;
		this.start = start;
		this.end = end;
		this.credit = credit;
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

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}
