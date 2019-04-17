package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentInDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//姓名
	private String name;
	//性别
	private String gender;
	//证件号码
	private String certificateNo;
	//转入省
	private String inProvince;
	//状态
	private String status;
	//转档准考证号
	private String studentid;
	//审核状态
	private String auditStatus;
	//审核人员
	private Long auditOperator;
	//审核日期
	private Date auditUpdateDate;
	//调档操作员
	private Long outOperator;
	//调档日期
	private Date outUpdateDate;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	//搜索内容
	private String searchName;

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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：证件号码
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * 获取：证件号码
	 */
	public String getCertificateNo() {
		return certificateNo;
	}
	/**
	 * 设置：转入省
	 */
	public void setInProvince(String inProvince) {
		this.inProvince = inProvince;
	}
	/**
	 * 获取：转入省
	 */
	public String getInProvince() {
		return inProvince;
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
	 * 设置：转档准考证号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：转档准考证号
	 */
	public String getStudentid() {
		return studentid;
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
	 * 设置：审核人员
	 */
	public void setAuditOperator(Long auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核人员
	 */
	public Long getAuditOperator() {
		return auditOperator;
	}
	/**
	 * 设置：审核日期
	 */
	public void setAuditUpdateDate(Date auditUpdateDate) {
		this.auditUpdateDate = auditUpdateDate;
	}
	/**
	 * 获取：审核日期
	 */
	public Date getAuditUpdateDate() {
		return auditUpdateDate;
	}
	/**
	 * 设置：调档操作员
	 */
	public void setOutOperator(Long outOperator) {
		this.outOperator = outOperator;
	}
	/**
	 * 获取：调档操作员
	 */
	public Long getOutOperator() {
		return outOperator;
	}
	/**
	 * 设置：调档日期
	 */
	public void setOutUpdateDate(Date outUpdateDate) {
		this.outUpdateDate = outUpdateDate;
	}
	/**
	 * 获取：调档日期
	 */
	public Date getOutUpdateDate() {
		return outUpdateDate;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}
