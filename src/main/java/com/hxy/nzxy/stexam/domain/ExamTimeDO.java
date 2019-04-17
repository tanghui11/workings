package com.hxy.nzxy.stexam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 考试时间
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:24
 */
public class ExamTimeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//任务编号
	private Long examTaskid;
	//序号
	private Integer seq;
	//开考日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date examDate;
	//时段
	private String segment;
	//开考时间
	private String beginTime;
	//结束时间
	private String endTime;
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
	 * 设置：任务编号
	 */
	public void setExamTaskid(Long examTaskid) {
		this.examTaskid = examTaskid;
	}
	/**
	 * 获取：任务编号
	 */
	public Long getExamTaskid() {
		return examTaskid;
	}
	/**
	 * 设置：序号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：序号
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：开考日期
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	/**
	 * 获取：开考日期
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * 设置：时段
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}
	/**
	 * 获取：时段
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * 设置：开考时间
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：开考时间
	 */
	public String getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndTime() {
		return endTime;
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
