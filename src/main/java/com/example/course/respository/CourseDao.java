package com.example.course.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.course.entity.Course;

@Repository
public interface CourseDao extends JpaRepository<Course,String>{
	public List<Course> findByCourseName(String courseName);
	
	public List<Course> findByIdOrCourseName(String id,String courseName);
}
