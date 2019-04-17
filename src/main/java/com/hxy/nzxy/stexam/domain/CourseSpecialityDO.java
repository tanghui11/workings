package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 须加考专业
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseSpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//加考编号
	private Long courseAppendid;
	//专业编号
	private String specialityid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	private String courseid;

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
	 * 设置：加考编号
	 */
	public void setCourseAppendid(Long courseAppendid) {
		this.courseAppendid = courseAppendid;
	}
	/**
	 * 获取：加考编号
	 */
	public Long getCourseAppendid() {
		return courseAppendid;
	}
	/**
	 * 设置：专业编号
	 */
	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
	/**
	 * 获取：专业编号
	 */
	public String getSpecialityid() {
		return specialityid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getCourseUpdate() {
		return courseUpdate;
	}

	public void setCourseUpdate(String courseUpdate) {
		this.courseUpdate = courseUpdate;
	}

	public String getCourseOperator() {
		return courseOperator;
	}

	public void setCourseOperator(String courseOperator) {
		this.courseOperator = courseOperator;
	}

	private String pinyin;
	private String ename;
	private String score;
	private String type;
	private String classify;
	private String attribute ;
	private String courseUpdate;
	private String courseOperator;
}
