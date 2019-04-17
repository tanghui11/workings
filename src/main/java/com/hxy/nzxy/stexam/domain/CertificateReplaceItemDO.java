package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 证书顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CertificateReplaceItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//证书顶替编号
	private Long certificateReplaceid;
	//证书名称
	private String oldCourseName;
	//课程代码
	private String courseid;
	//课程名称
	private String courseName;
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
	 * 设置：证书顶替编号
	 */
	public void setCertificateReplaceid(Long certificateReplaceid) {
		this.certificateReplaceid = certificateReplaceid;
	}
	/**
	 * 获取：证书顶替编号
	 */
	public Long getCertificateReplaceid() {
		return certificateReplaceid;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
