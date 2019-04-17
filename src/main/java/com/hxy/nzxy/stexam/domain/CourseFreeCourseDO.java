package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 课程免考规则课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
public class CourseFreeCourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String courseFreeid;
	//
	private String courseid;
	//
	private String operator;
	//
	private String updateDate;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCourseFreeid(String courseFreeid) {
		this.courseFreeid = courseFreeid;
	}
	/**
	 * 获取：
	 */
	public String getCourseFreeid() {
		return courseFreeid;
	}
	/**
	 * 设置：
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public String getUpdateDate() {
		return updateDate;
	}
}
