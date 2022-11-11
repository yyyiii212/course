package com.example.course.constants;

public enum CourseRtnCode {
	SUCCESSFUL("200","Successful !"),
	COURSE_ID_EXISTED("400","CourseId is existed!!"),
	COURSE_ID_IS_NOT_FOUND("400","CourseId is not found !!"),
	COURSE_ID_REQUIRED("400","CourseId cannot be null or empty !!"),
	COURSEID_OR_COURSENAME_REQUIRED("400","CourseId and CourseName cannot be null or empty !!"),
	COURSE_NAME_REQUIRED("400","CourseName cannot be null or empty !!"),
	WEEK_REQUIRED("400","Week cannot be null or empty !!"),
	START_REQUIRED("400","Start cannot be null or empty !!"),
	END_REQUIRED("400","End cannot be null or empty !!"),
	CREDIT_REQUIRED("400","Credit cannot be null or empty !!"),
	STUDENT_ID_EXISTED("400","StudentId is existed!!"),
	STUDENT_ID_REQUIRED("400","StudentId cannot be null or empty !!"),
	STUDENT_ID_IS_NOT_FOUND("400","StudentId is not found !!"),
	STUDENT_NAME_REQUIRED("400","StudentName cannot be null or empty !!"),
	CREDIT_OVER("400","Credit is over !!"),
	ERROR("400","Not found !!");
	
	private String code;
	
	private String message;
	
	private CourseRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
