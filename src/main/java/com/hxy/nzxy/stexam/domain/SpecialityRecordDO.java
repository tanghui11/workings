package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业开设管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class SpecialityRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业代码
	private String specialityid;
	//类别
	private String type;
	//主考院校编号
	private Long schoolid;
	//主考学院编号
	private Long collegeid;
	//专业方向
	private String direction;
	//毕业论文课程代码
	private String gradCourseid;
	//状态
	private String status;
	//办证状态
	private String gradStatus;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	private String subjectName;
	private String  schoolName;
	private String collegeName;
	private String newSpecialityid;
	private String newSpecialityName;
	private String newSpecialityRecordid;
	private String newDirection;

	public String getNewDirection() {
		return newDirection;
	}

	public void setNewDirection(String newDirection) {
		this.newDirection = newDirection;
	}

	public String getNewSpecialityRecordid() {
		return newSpecialityRecordid;
	}

	public void setNewSpecialityRecordid(String newSpecialityRecordid) {
		this.newSpecialityRecordid = newSpecialityRecordid;
	}

	public String getNewSpecialityName() {
		return newSpecialityName;
	}

	public void setNewSpecialityName(String newSpecialityName) {
		this.newSpecialityName = newSpecialityName;
	}

	public String getNewSpecialityid() {
		return newSpecialityid;
	}

	public void setNewSpecialityid(String newSpecialityid) {
		this.newSpecialityid = newSpecialityid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
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
	 * 设置：专业代码
	 */
	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
	/**
	 * 获取：专业代码
	 */
	public String getSpecialityid() {
		return specialityid;
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
	 * 设置：主考院校编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：主考院校编号
	 */
	public Long getSchoolid() {
		return schoolid;
	}
	/**
	 * 设置：主考学院编号
	 */
	public void setCollegeid(Long collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * 获取：主考学院编号
	 */
	public Long getCollegeid() {
		return collegeid;
	}
	/**
	 * 设置：专业方向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * 获取：专业方向
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * 设置：毕业论文课程代码
	 */
	public void setGradCourseid(String gradCourseid) {
		this.gradCourseid = gradCourseid;
	}
	/**
	 * 获取：毕业论文课程代码
	 */
	public String getGradCourseid() {
		return gradCourseid;
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
	 * 设置：办证状态
	 */
	public void setGradStatus(String gradStatus) {
		this.gradStatus = gradStatus;
	}
	/**
	 * 获取：办证状态
	 */
	public String getGradStatus() {
		return gradStatus;
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
