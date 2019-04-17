package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 通用课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CommonCourseReplaceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//操作员
	private Long id;
	//课程编号
	private String courseid;
	//课程名称
	private String courseName;
	//专业类别
	private String specialityClass;
	//顶替类别
	private String flag;
	//课程层次
	private String courseClass;
	//类别
	private String type;
	//共几门课程
	private Integer courseNum;
	//至少选几门
	private Integer leastNum;
	//最少学分
	private Integer leastScore;
	//附加课程1
	private String appendCourseid1;
	//附加课程名字1
	private String appendCourseName1;
	//附加课程2
	private String appendCourseid2;
	//附加课程名字2
	private String appendCourseName2;
	//附加课程3
	private String appendCourseid3;
	//附加课程名字3
	private String appendCourseName3;
	//附加课程4
	private String appendCourseid4;
	//附加课程名字4
	private String appendCourseName4;
	private String courseidReplace;
	private String courseidReplaceName;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	public String getCourseidReplace() {
		return courseidReplace;
	}

	public void setCourseidReplace(String courseidReplace) {
		this.courseidReplace = courseidReplace;
	}

	public String getCourseidReplaceName() {
		return courseidReplaceName;
	}

	public void setCourseidReplaceName(String courseidReplaceName) {
		this.courseidReplaceName = courseidReplaceName;
	}

	/**
	 * 设置：操作员
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：操作员
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：课程编号
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：专业类别
	 */
	public void setSpecialityClass(String specialityClass) {
		this.specialityClass = specialityClass;
	}
	/**
	 * 获取：专业类别
	 */
	public String getSpecialityClass() {
		return specialityClass;
	}
	/**
	 * 设置：顶替类别
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：顶替类别
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：课程层次
	 */
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	/**
	 * 获取：课程层次
	 */
	public String getCourseClass() {
		return courseClass;
	}
	/**
	 * 设置：类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：共几门课程
	 */
	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	/**
	 * 获取：共几门课程
	 */
	public Integer getCourseNum() {
		return courseNum;
	}
	/**
	 * 设置：至少选几门
	 */
	public void setLeastNum(Integer leastNum) {
		this.leastNum = leastNum;
	}
	/**
	 * 获取：至少选几门
	 */
	public Integer getLeastNum() {
		return leastNum;
	}
	/**
	 * 设置：最少学分
	 */
	public void setLeastScore(Integer leastScore) {
		this.leastScore = leastScore;
	}
	/**
	 * 获取：最少学分
	 */
	public Integer getLeastScore() {
		return leastScore;
	}
	/**
	 * 设置：附加课程1
	 */
	public void setAppendCourseid1(String appendCourseid1) {
		this.appendCourseid1 = appendCourseid1;
	}
	/**
	 * 获取：附加课程1
	 */
	public String getAppendCourseid1() {
		return appendCourseid1;
	}
	/**
	 * 设置：附加课程2
	 */
	public void setAppendCourseid2(String appendCourseid2) {
		this.appendCourseid2 = appendCourseid2;
	}
	/**
	 * 获取：附加课程2
	 */
	public String getAppendCourseid2() {
		return appendCourseid2;
	}
	/**
	 * 设置：附加课程3
	 */
	public void setAppendCourseid3(String appendCourseid3) {
		this.appendCourseid3 = appendCourseid3;
	}
	/**
	 * 获取：附加课程3
	 */
	public String getAppendCourseid3() {
		return appendCourseid3;
	}
	/**
	 * 设置：附加课程4
	 */
	public void setAppendCourseid4(String appendCourseid4) {
		this.appendCourseid4 = appendCourseid4;
	}
	/**
	 * 获取：附加课程4
	 */
	public String getAppendCourseid4() {
		return appendCourseid4;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAppendCourseName1() {
		return appendCourseName1;
	}

	public void setAppendCourseName1(String appendCourseName1) {
		this.appendCourseName1 = appendCourseName1;
	}

	public String getAppendCourseName2() {
		return appendCourseName2;
	}

	public void setAppendCourseName2(String appendCourseName2) {
		this.appendCourseName2 = appendCourseName2;
	}

	public String getAppendCourseName3() {
		return appendCourseName3;
	}

	public void setAppendCourseName3(String appendCourseName3) {
		this.appendCourseName3 = appendCourseName3;
	}

	public String getAppendCourseName4() {
		return appendCourseName4;
	}

	public void setAppendCourseName4(String appendCourseName4) {
		this.appendCourseName4 = appendCourseName4;
	}

	public String getCourseReplaceid() {
		return courseReplaceid;
	}

	public void setCourseReplaceid(String courseReplaceid) {
		this.courseReplaceid = courseReplaceid;
	}

	private String courseReplaceid;
}
