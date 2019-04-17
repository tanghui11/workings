package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 免考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class ScoreReplaceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	//专业开设编号
	private Long specialityRecordid;
	//专业课程编号
	private String courseid;
	//顶替类别编号
	private Long courseFreeid;
	//
	private Float grade;
	//备注
	private String remark;
	//审核状态
	private String auditStatus;
	//审核人
	private Long auditOperator;
	//审核日期
	private String auditDate;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;

	private String specialityName;
	private String freeCourseName;

	public String getCourseFreeName() {
		return courseFreeName;
	}

	public void setCourseFreeName(String courseFreeName) {
		this.courseFreeName = courseFreeName;
	}

	private String courseFreeName;

	public String getCourseFree() {
		return courseFree;
	}

	public void setCourseFree(String courseFree) {
		this.courseFree = courseFree;
	}

	private String courseFree;

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getFreeCourseName() {
		return freeCourseName;
	}

	public void setFreeCourseName(String freeCourseName) {
		this.freeCourseName = freeCourseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private  String courseName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private  String type;

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}

	private  String specialityid;


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
	 * 设置：准考证编号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：准考证编号
	 */
	public String getStudentid() {
		return studentid;
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
	 * 设置：专业课程编号
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：专业课程编号
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：顶替类别编号
	 */
	public void setCourseFreeid(Long courseFreeid) {
		this.courseFreeid = courseFreeid;
	}
	/**
	 * 获取：顶替类别编号
	 */
	public Long getCourseFreeid() {
		return courseFreeid;
	}
	/**
	 * 设置：
	 */
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	/**
	 * 获取：
	 */
	public Float getGrade() {
		return grade;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审核人
	 */
	public void setAuditOperator(Long auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核人
	 */
	public Long getAuditOperator() {
		return auditOperator;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
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
	 * 设置：数据标记
	 */
	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * 获取：数据标记
	 */
	public Integer getDbFlag() {
		return dbFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
