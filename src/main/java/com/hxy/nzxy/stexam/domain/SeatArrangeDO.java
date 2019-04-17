package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 座次安排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class SeatArrangeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考场编排编号
	private Long roomArrangeid;
	//考生报考编号
	private Long studentCourseid;
	//开考课程编号
	private Long examCourseid;
	//座位序号
	private Integer seq;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	private String message;


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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomArrangeid() {
		return roomArrangeid;
	}

	public void setRoomArrangeid(Long roomArrangeid) {
		this.roomArrangeid = roomArrangeid;
	}

	public Long getStudentCourseid() {
		return studentCourseid;
	}

	public void setStudentCourseid(Long studentCourseid) {
		this.studentCourseid = studentCourseid;
	}

	public Long getExamCourseid() {
		return examCourseid;
	}

	public void setExamCourseid(Long examCourseid) {
		this.examCourseid = examCourseid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public Integer getDbFlag() {
		return dbFlag;
	}

	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}

	public Integer getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
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
}
