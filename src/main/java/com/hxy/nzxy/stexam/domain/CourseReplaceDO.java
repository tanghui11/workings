package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseReplaceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业开设编号
	private Long specialityRecordid;
	//课程编号
	private String courseid;
	private String specialityRecordName;
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
	//附加课程2
	private String appendCourseid2;
	//附加课程3
	private String appendCourseid3;
	//附加课程4
	private String appendCourseid4;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

	//0无效,1有效
	private Integer enabledFlag;
	private String courseidReplace;
	private String courseidReplaceName;

	public String getSpecialityRecordName() {
		return specialityRecordName;
	}

	public void setSpecialityRecordName(String specialityRecordName) {
		this.specialityRecordName = specialityRecordName;
	}

	public String getCourseidReplaceName() {
		return courseidReplaceName;
	}

	public void setCourseidReplaceName(String courseidReplaceName) {
		this.courseidReplaceName = courseidReplaceName;
	}

	public String getCourseidReplace() {
		return courseidReplace;
	}

	public void setCourseidReplace(String courseidReplace) {
		this.courseidReplace = courseidReplace;
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
	 * 设置：专业开设编号
	 */
	public void setSpecialityRecordid(Long specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
	/**
	 * 获取：专业开设编号
	 */
	public Long getSpecialityRecordid() {
		return specialityRecordid;
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

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	private String courseName;
	private String specialityName;

	public String getAppendCourseid1Name() {
		return appendCourseid1Name;
	}

	public void setAppendCourseid1Name(String appendCourseid1Name) {
		this.appendCourseid1Name = appendCourseid1Name;
	}

	public String getAppendCourseid2Name() {
		return appendCourseid2Name;
	}

	public void setAppendCourseid2Name(String appendCourseid2Name) {
		this.appendCourseid2Name = appendCourseid2Name;
	}

	public String getAppendCourseid3Name() {
		return appendCourseid3Name;
	}

	public void setAppendCourseid3Name(String appendCourseid3Name) {
		this.appendCourseid3Name = appendCourseid3Name;
	}

	public String getAppendCourseid4Name() {
		return appendCourseid4Name;
	}

	public void setAppendCourseid4Name(String appendCourseid4Name) {
		this.appendCourseid4Name = appendCourseid4Name;
	}

	private String appendCourseid1Name;
	private String appendCourseid2Name;
	private String appendCourseid3Name;
	private String appendCourseid4Name;



}
