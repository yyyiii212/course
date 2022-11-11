package com.example.course.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.constants.CourseRtnCode;
import com.example.course.entity.Course;
import com.example.course.entity.Student;
import com.example.course.respository.CourseDao;
import com.example.course.respository.StudentDao;
import com.example.course.service.ifs.CourseService;
import com.example.course.vo.CourseRes;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;

	private boolean betweenExclude(int start1, int end1, int start2, int end2) {
//		return !(start1 >= end2) && !(end1 <= start2); // true ==衝堂
		return start1 >= end2 || end1 <= start2; // true == 不衝堂
	}
	
	private Student studentCheck(String studentId) {
		Optional<Student> studentOp = studentDao.findById(studentId);
		if (!studentOp.isPresent()) {
			return null;
		}
		Student student = studentOp.get();
		return student;
	}

	@Override
	public Course increaseCourse(String id, String courseName, int week, int start, int end, int credit) {
		Optional<Course> courseOp = courseDao.findById(id);
		if (courseOp.isPresent()) {
			return null;
		}
		if (week > 0 || week < 6) {
			return null;
		} else if (start > end || start >= 25 || end >= 25) {
			return null;
		}
		Course course = new Course(id, courseName, week, start, end, credit);
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(String id, String courseName, int week, int start, int end, int credit) {
		Optional<Course> courseOp = courseDao.findById(id);
		if (courseOp.isPresent()) {
			return null;
		}
		if (week > 0 || week < 6) {
			return null;
		} else if (start > end || start >= 25 || end >= 25) {
			return null;
		}
		Course course = courseOp.get();
		course.setCourseName(courseName);
		course.setWeek(week);
		course.setStart(start);
		course.setEnd(end);
		course.setCredit(credit);
		courseDao.save(course);
		return course;
	}

	@Override
	public Student increaseStudent(String studentId, String studentName) {
		Optional<Student> studentOp = studentDao.findById(studentId);
		if (studentOp.isPresent()) {
			return null;
		}
		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName(studentName);
		studentDao.save(student);
		return student;
	}

	@Override
	public Student updateStudent(String studentId, String studentName, String courseId) {
		Optional<Student> studentOp = studentDao.findById(studentId);
		if (!studentOp.isPresent()) {
			return null;
		}
		Optional<Course> courseOp = courseDao.findById(courseId);
		if (!courseOp.isPresent()) {
			return null;
		}
		Student student = studentOp.get();
		student.setStudentName(studentName);
		student.setCourseId(courseId);
		studentDao.save(student);
		return student;
	}

	@Override
	public CourseRes courseSelect(String studentId, List<String> id) {// 選課以及加選
		int totalCredit = 0;
		CourseRes res = new CourseRes();
		Student student = studentCheck(studentId);
		List<String> studentList = new ArrayList<>();
		if (student.getCourseId() != null) {
			String[] strArray = student.getCourseId().split(",");
			for (int i = 0; i < strArray.length; i++) {
				String item = strArray[i].trim();// trim去除前後空白
				studentList.add(item);// 把DB裡面student的courseId放進List
			}
		}
		Set<String> courseIdList = new HashSet<>();
		String courseStr = "";
		List<Course> courseList = courseDao.findAllById(id);//找出輸入(ID)的課程
		courseList.addAll(courseDao.findAllById(studentList));//找出學生已有的課程加進輸入(ID)的課程
		for (int i = 0; i < courseList.size(); i++) {//從第0筆跟第1筆比較 依序往下
			for (int j = i + 1; j < courseList.size(); j++) {
				if (courseList.get(i).getWeek() == courseList.get(j).getWeek()) {//比較星期幾
					if (betweenExclude(courseList.get(i).getStart(), courseList.get(i).getEnd(),
							courseList.get(j).getStart(), courseList.get(j).getEnd()) == false) {//比較是否衝堂
						return null;
					}
				}
			}
			courseStr += courseList.get(i).getId() + ",";//把沒衝堂的課放進String裡
			totalCredit += courseList.get(i).getCredit();//計算總學分
		}
		if (totalCredit >= 11) {//學分數大於(等於)11就掛掉
			return new CourseRes(CourseRtnCode.CREDIT_OVER.getMessage());
		}
		String str = courseStr;
		String[] strArray1 = str.split(",");//依照,切割
		for (int i = 0; i < strArray1.length; i++) {
			String item = strArray1[i].trim();// trim去除前後空白
			courseIdList.add(item);// 把DB的courseId放進List
		}
		student.setCourseId(courseIdList.toString().substring(1, courseIdList.toString().length() - 1));//去除前後括號
		studentDao.save(student);
		res.setStudent(student);
		res.setTotalCredit(totalCredit);
		return res;
	}

	@Override
	public CourseRes courseDropOut(String studentId, List<String> id) {
		CourseRes res = new CourseRes();
		Student student = studentCheck(studentId);
		List<String> strList = new ArrayList<>();
		if (student.getCourseId() != null) {
			String[] strArray = student.getCourseId().split(",");
			for (int i = 0; i < strArray.length; i++) {
				String item = strArray[i].trim();// trim去除前後空白
				strList.add(item);// 把DB的courseId放進List
			}
		}
		List<String> str = new ArrayList<>();
		for (String str1 : id) {
			for (String str2 : strList) {
				if (str1.equals(str2)) {//比較課程相同就儲存
					str.add(str1);
				}
			}
		}
		int x = strList.size();
		strList.removeAll(str);
		if (strList.size() == x) {//用size比較看有無刪除，如無刪除就return null
			return null;
		}
		student.setCourseId(strList.toString().substring(1, strList.toString().length() - 1));
		studentDao.save(student);
		res.setStudent(student);
		return res;
	}

	@Override
	public CourseRes searchStudentCourse(String studentId) {
		CourseRes res = new CourseRes();
		Student student = studentCheck(studentId);
		List<String> strList = new ArrayList<>();
		if (student.getCourseId() != null) {
			String[] strArray = student.getCourseId().split(",");
			for (int i = 0; i < strArray.length; i++) {
				String item = strArray[i].trim();// trim去除前後空白
				strList.add(item);// 把DB的courseId放進List
			}
		}
		int totalCredit = 0;
		List<Course> courseList = courseDao.findAllById(strList);
		for (Course course : courseList) {
			totalCredit += course.getCredit();
		}
		res.setStudent(student);
		res.setCourseList(courseList);
		res.setTotalCredit(totalCredit);
		return res;
	}

	@Override
	public CourseRes searchCourseIdOrCourseIName(String id, String courseName) {
		CourseRes res = new CourseRes();
		List<Course> courseList1 = courseDao.findByIdOrCourseName(id, courseName);
		res.setCourseList(courseList1);
		return res;
	}
}
