package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 学院考试课程选择
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class SchoolExamCourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//学院专业招生编号
	private Long schoolSpecialityRegid;
	//专业课程编号
	private Long specialityCourseid;
	//开考课程编号
	private Long examCourseid;
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
	 * 设置：学院专业招生编号
	 */
	public void setSchoolSpecialityRegid(Long schoolSpecialityRegid) {
		this.schoolSpecialityRegid = schoolSpecialityRegid;
	}
	/**
	 * 获取：学院专业招生编号
	 */
	public Long getSchoolSpecialityRegid() {
		return schoolSpecialityRegid;
	}
	/**
	 * 设置：专业课程编号
	 */
	public void setSpecialityCourseid(Long specialityCourseid) {
		this.specialityCourseid = specialityCourseid;
	}
	/**
	 * 获取：专业课程编号
	 */
	public Long getSpecialityCourseid() {
		return specialityCourseid;
	}
	/**
	 * 设置：开考课程编号
	 */
	public void setExamCourseid(Long examCourseid) {
		this.examCourseid = examCourseid;
	}
	/**
	 * 获取：开考课程编号
	 */
	public Long getExamCourseid() {
		return examCourseid;
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
