package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 *功能描述 免考课程顶替 院校端
 * @author ypp
 * @date 2018/12/4
 * @param
 * @return
 */
public class ExemptionCourseReplaceItemDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//编号
	private String id;

	//准考证号
	private String studentid;
	//顶替名称
	private  String replaceName;
	//成绩
	private String grade;
	//备注
	private String remark;
	//顶替备注
	private String replaceRemark;
	//免考规则id
	private String courseFreeid;
	//审核状态
	private String auditStatus;
	//审核日期
	private String auditDate;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;


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

	public String getReplaceName() {
		return replaceName;
	}

	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReplaceRemark() {
		return replaceRemark;
	}

	public void setReplaceRemark(String replaceRemark) {
		this.replaceRemark = replaceRemark;
	}

	public String getCourseFreeid() {
		return courseFreeid;
	}

	public void setCourseFreeid(String courseFreeid) {
		this.courseFreeid = courseFreeid;
	}
}
