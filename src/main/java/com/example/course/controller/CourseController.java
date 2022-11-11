package com.example.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.constants.CourseRtnCode;
import com.example.course.entity.Course;
import com.example.course.entity.Student;
import com.example.course.service.ifs.CourseService;
import com.example.course.vo.CourseReq;
import com.example.course.vo.CourseRes;

@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;

	public CourseRes checkCourse(CourseReq req) {
		if (!StringUtils.hasText(req.getId())) {
			return new CourseRes(CourseRtnCode.COURSE_ID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getCourseName())) {
			return new CourseRes(CourseRtnCode.COURSE_NAME_REQUIRED.getMessage());
		} else if (req.getWeek() == null) {
			return new CourseRes(CourseRtnCode.WEEK_REQUIRED.getMessage());
		} else if (req.getStart() == null) {
			return new CourseRes(CourseRtnCode.START_REQUIRED.getMessage());
		} else if (req.getEnd() == null) {
			return new CourseRes(CourseRtnCode.END_REQUIRED.getMessage());
		} else if (req.getCredit() == null) {
			return new CourseRes(CourseRtnCode.CREDIT_REQUIRED.getMessage());
		}
		return null;
	}
	
	public CourseRes checkStudent(CourseReq req) {
		if (!StringUtils.hasText(req.getStudentId())) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getStudentName())) {
			return new CourseRes(CourseRtnCode.STUDENT_NAME_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getId())) {
			return new CourseRes(CourseRtnCode.COURSE_ID_REQUIRED.getMessage());
		}
		return null;
	}

	@PostMapping(value = "/api/increaseCourse")
	public CourseRes increaseCourse(@RequestBody CourseReq req) {
		CourseRes check = checkCourse(req);
		if (check != null) {
			return check;
		}
		Course course = courseService.increaseCourse(req.getId(), req.getCourseName(), req.getWeek(), req.getStart(),
				req.getEnd(), req.getCredit());
		if(course == null) {
			return new CourseRes(CourseRtnCode.COURSE_ID_EXISTED.getMessage());
		}
		CourseRes res = new CourseRes();
		res.setCourse(course);
		res.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	@PostMapping(value = "/api/updateCourse")
	public CourseRes updateCourse(@RequestBody CourseReq req) {
		CourseRes check = checkCourse(req);
		if (check != null) {
			return check;
		}
		Course course = courseService.updateCourse(req.getId(), req.getCourseName(), req.getWeek(), req.getStart(),
				req.getEnd(), req.getCredit());
		if(course == null) {
			return new CourseRes(CourseRtnCode.COURSE_ID_IS_NOT_FOUND.getMessage());
		}
		CourseRes res = new CourseRes();
		res.setCourse(course);
		res.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return res;
	}
	
	@PostMapping(value = "/api/increaseStudent")
	public CourseRes increaseStudent(@RequestBody CourseReq req) {
		if (!StringUtils.hasText(req.getStudentId())) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getStudentName())) {
			return new CourseRes(CourseRtnCode.STUDENT_NAME_REQUIRED.getMessage());
		}
		Student student = courseService.increaseStudent(req.getStudentId(), req.getStudentName());
		if(student == null) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_EXISTED.getMessage());
		}
		CourseRes res = new CourseRes();
		res.setStudent(student);
		res.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return res ;
	}
	
	@PostMapping(value = "/api/updateStudent")
	public CourseRes updateStudent(@RequestBody CourseReq req) {
		CourseRes check = checkStudent(req);
		if (check != null) {
			return check;
		}
		Student student = courseService.updateStudent(req.getStudentId(), req.getStudentName(), req.getId());
		if(student == null) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_IS_NOT_FOUND.getMessage());
		}
		CourseRes res = new CourseRes();
		res.setStudent(student);
		res.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return res ;
	}
	
	@PostMapping(value = "/api/courseSelect")
	public CourseRes courseSelect(@RequestBody CourseReq req) {
		if(CollectionUtils.isEmpty(req.getCourseId())) {
			return new CourseRes(CourseRtnCode.COURSE_ID_REQUIRED.getMessage());
		}else if (!StringUtils.hasText(req.getStudentId())) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_REQUIRED.getMessage());
		} 
		CourseRes student = courseService.courseSelect(req.getStudentId(), req.getCourseId());
		if(student == null) {
			return new CourseRes(CourseRtnCode.ERROR.getMessage());
		}
		student.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return student;
	}
	
	@PostMapping(value = "/api/courseDropOut")
	public CourseRes courseDropOut(@RequestBody CourseReq req) {
		if(CollectionUtils.isEmpty(req.getCourseId())) {
			return new CourseRes(CourseRtnCode.COURSE_ID_REQUIRED.getMessage());
		}else if (!StringUtils.hasText(req.getStudentId())) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_REQUIRED.getMessage());
		} 
		CourseRes student = courseService.courseDropOut(req.getStudentId(), req.getCourseId());
		if(student == null) {
			return new CourseRes(CourseRtnCode.ERROR.getMessage());
		}
		student.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return student;
	}
	
	@PostMapping(value = "/api/searchStudentCourse")
	public CourseRes searchStudentCourse(@RequestBody CourseReq req) {
		if (!StringUtils.hasText(req.getStudentId())) {
			return new CourseRes(CourseRtnCode.STUDENT_ID_REQUIRED.getMessage());
		} 
		CourseRes student = courseService.searchStudentCourse(req.getStudentId());
		if(student == null) {
			return new CourseRes(CourseRtnCode.ERROR.getMessage());
		}
		student.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return student;
	}
	
	@PostMapping(value = "/api/searchCourseIdOrCourseIName")
	public CourseRes searchCourseIdOrCourseIName(@RequestBody CourseReq req) {
		if(!StringUtils.hasText(req.getId()) && !StringUtils.hasText(req.getCourseName())) {
			return new CourseRes(CourseRtnCode.COURSEID_OR_COURSENAME_REQUIRED.getMessage());
		}
		CourseRes course = courseService.searchCourseIdOrCourseIName(req.getId(), req.getCourseName());
		if(course == null) {
			return new CourseRes(CourseRtnCode.ERROR.getMessage());
		}
		course.setMessage(CourseRtnCode.SUCCESSFUL.getMessage());
		return course;
	}
}
