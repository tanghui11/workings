package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class ScoreTempDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//操作员
	private Long seatArrangeid;
	//操作员
	private Float grade1;
	//操作员
	private String examFlag1;
	//操作员
	private Float grade2;
	//操作员
	private String examFlag2;
	//操作员
	private Float grade;
	//操作员
	private String examFlag;
	//操作员
	private String operator1;
	//操作员
	private Date updateDate1;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	public Long getSeatArrangeid() {
		return seatArrangeid;
	}

	public void setSeatArrangeid(Long seatArrangeid) {
		this.seatArrangeid = seatArrangeid;
	}

	public Float getGrade1() {
		return grade1;
	}

	public void setGrade1(Float grade1) {
		this.grade1 = grade1;
	}

	public String getExamFlag1() {
		return examFlag1;
	}

	public void setExamFlag1(String examFlag1) {
		this.examFlag1 = examFlag1;
	}

	public Float getGrade2() {
		return grade2;
	}

	public void setGrade2(Float grade2) {
		this.grade2 = grade2;
	}

	public String getExamFlag2() {
		return examFlag2;
	}

	public void setExamFlag2(String examFlag2) {
		this.examFlag2 = examFlag2;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public String getExamFlag() {
		return examFlag;
	}

	public void setExamFlag(String examFlag) {
		this.examFlag = examFlag;
	}

	public String getOperator1() {
		return operator1;
	}

	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}

	public Date getUpdateDate1() {
		return updateDate1;
	}

	public void setUpdateDate1(Date updateDate1) {
		this.updateDate1 = updateDate1;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
}
