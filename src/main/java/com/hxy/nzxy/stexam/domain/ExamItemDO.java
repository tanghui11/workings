package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考试项目
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:24
 */
public class ExamItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考试任务编号
	private Long examTaskid;
	//考试项目
	private String examMonth;
	//考试项目
	private String examCodeFixed;
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
	 * 设置：考试任务编号
	 */
	public void setExamTaskid(Long examTaskid) {
		this.examTaskid = examTaskid;
	}
	/**
	 * 获取：考试任务编号
	 */
	public Long getExamTaskid() {
		return examTaskid;
	}
	/**
	 * 设置：考试项目
	 */
	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}
	/**
	 * 获取：考试项目
	 */
	public String getExamMonth() {
		return examMonth;
	}
	/**
	 * 设置：考试项目
	 */
	public void setExamCodeFixed(String examCodeFixed) {
		this.examCodeFixed = examCodeFixed;
	}
	/**
	 * 获取：考试项目
	 */
	public String getExamCodeFixed() {
		return examCodeFixed;
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
