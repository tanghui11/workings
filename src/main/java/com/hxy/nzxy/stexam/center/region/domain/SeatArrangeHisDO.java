package com.hxy.nzxy.stexam.center.region.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 座次安排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public class SeatArrangeHisDO implements Serializable {
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
	 * 设置：考场编排编号
	 */
	public void setRoomArrangeid(Long roomArrangeid) {
		this.roomArrangeid = roomArrangeid;
	}
	/**
	 * 获取：考场编排编号
	 */
	public Long getRoomArrangeid() {
		return roomArrangeid;
	}
	/**
	 * 设置：考生报考编号
	 */
	public void setStudentCourseid(Long studentCourseid) {
		this.studentCourseid = studentCourseid;
	}
	/**
	 * 获取：考生报考编号
	 */
	public Long getStudentCourseid() {
		return studentCourseid;
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
	 * 设置：座位序号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：座位序号
	 */
	public Integer getSeq() {
		return seq;
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
	 * 设置：数据标记
	 */
	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * 获取：数据标记
	 */
	public Integer getDbFlag() {
		return dbFlag;
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
