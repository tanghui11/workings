package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 学科素养及目标
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-16 21:56:48
 */
public class SubjectScienceAbilityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//类别
	private String type;
	//类别
	private String subjectid;
	//类别
	private String gradeType;
	//类别
	private String name;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

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
	 * 设置：类别
	 */
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	/**
	 * 获取：类别
	 */
	public String getSubjectid() {
		return subjectid;
	}
	/**
	 * 设置：类别
	 */
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	/**
	 * 获取：类别
	 */
	public String getGradeType() {
		return gradeType;
	}
	/**
	 * 设置：类别
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：类别
	 */
	public String getName() {
		return name;
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
}
