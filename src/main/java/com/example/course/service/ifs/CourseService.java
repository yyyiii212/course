package com.example.course.service.ifs;

import java.util.List;

import com.example.course.entity.Course;
import com.example.course.entity.Student;
import com.example.course.vo.CourseRes;

public interface CourseService {
	public Course increaseCourse(String id, String courseName, int week, int start, int end, int credit);
	
	public Course updateCourse(String id, String courseName, int week, int start, int end, int credit);
	
	public Student increaseStudent(String studentId, String studentName);

	public Student updateStudent(String studentId, String studentName, String courseId);
	
	public CourseRes courseSelect(String studentId, List<String> id);
	
	public CourseRes courseDropOut(String studentId,  List<String> id);
	
	public CourseRes searchStudentCourse(String studentId);
	
	public CourseRes searchCourseIdOrCourseIName(String id, String courseName);
}
