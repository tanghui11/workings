package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 证书顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CertificateReplaceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//证书编号
	private String oldCourseid;
	//证书名称
	private String oldCourseName;
	//专业类别
	private String courseClass;
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
	 * 设置：证书编号
	 */
	public void setOldCourseid(String oldCourseid) {
		this.oldCourseid = oldCourseid;
	}
	/**
	 * 获取：证书编号
	 */
	public String getOldCourseid() {
		return oldCourseid;
	}
	/**
	 * 设置：专业类别
	 */
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	/**
	 * 获取：专业类别
	 */
	public String getCourseClass() {
		return courseClass;
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

	public String getOldCourseName() {
		return oldCourseName;
	}

	public void setOldCourseName(String oldCourseName) {
		this.oldCourseName = oldCourseName;
	}
}
