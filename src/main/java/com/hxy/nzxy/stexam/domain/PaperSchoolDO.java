package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 阅卷点设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class PaperSchoolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//课程代码
	private String courseid;
	//学校编号
	private Long schoolid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	//操作员
	private String schoolName;
	private String courseName;
	private String allCount;
	private String shorCount;
	private String longCount;
	private String examTaskid;
	private String examCourseid;

	public String getAllCount() {
		return allCount;
	}

	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	public String getShorCount() {
		return shorCount;
	}

	public void setShorCount(String shorCount) {
		this.shorCount = shorCount;
	}

	public String getLongCount() {
		return longCount;
	}

	public void setLongCount(String longCount) {
		this.longCount = longCount;
	}

	public String getExamTaskid() {
		return examTaskid;
	}

	public void setExamTaskid(String examTaskid) {
		this.examTaskid = examTaskid;
	}

	public String getExamCourseid() {
		return examCourseid;
	}

	public void setExamCourseid(String examCourseid) {
		this.examCourseid = examCourseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：课程代码
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：课程代码
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：学校编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：学校编号
	 */
	public Long getSchoolid() {
		return schoolid;
	}
	/**
	 * 设置：操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作员
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：操作日期
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：操作日期
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：0无效,1有效
	 */
	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * 获取：0无效,1有效
	 */
	public Integer getEnabledFlag() {
		return enabledFlag;
	}
}
