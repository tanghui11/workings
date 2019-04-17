package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 课程限报专业
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:24
 */
public class ExamCourseSpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//开考课程编号
	private Long examCourseid;
	//专业开设编号
	private Long specialityRecordid;
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
