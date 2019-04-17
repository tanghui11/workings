package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 复选课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseCheckItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//课程复选编号
	private Long courseCheckid;
	//课程编号
	private String courseid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

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
	 * 设置：课程复选编号
	 */
	public void setCourseCheckid(Long courseCheckid) {
		this.courseCheckid = courseCheckid;
	}
	/**
	 * 获取：课程复选编号
	 */
	public Long getCourseCheckid() {
		return courseCheckid;
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


	//编号
	private String courseids;
	//名称
	private String name;
	//开考日期
	private String examDate;
	//时段
	private String segment;
	//拼音
	private String pinyin;
	//英文名称
	private String ename;
	//学分
	private float score;
	//类别
	private String type;
	//分类
	private String classify;
	//属性
	private String attribute;

	public String getCourseids() {
		return courseids;
	}

	public void setCourseids(String courseids) {
		this.courseids = courseids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
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

	public String getPracticeCourseid() {
		return practiceCourseid;
	}

	public void setPracticeCourseid(String practiceCourseid) {
		this.practiceCourseid = practiceCourseid;
	}

	//实践课程代码
	private String practiceCourseid;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private String courseName;
}
