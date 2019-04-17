package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考试任务
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 16:59:07
 */
public class TaskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//年份
	private String examYear;
	//月份
	private String examMonth;
	//年月
	private String examName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	//助学生报考开始日期
	private Date beginDate;
	//助学生报考结束日期
	private Date endDate;
	//助学生补报开始日期
	private Date beginDateAppend;
	//助学生补报结束日期
	private Date endDateAppend;
	//社会生报考开始日期
	private Date beginDate1;
	//社会生报考结束日期
	private Date endDate1;
	//社会生补报开始日期
	private Date beginDateAppend1;
	//社会生补报结束日期
	private Date endDateAppend1;
	//考场编排考试日期
	private Date arrangeBeginDate;
	//考场编排考试日期
	private Date arrangeEndDate;
	//备注
	private String remark;
	//状态
	private String status;
	//确认状态
	private String confirmStatus;
	//审核状态
	private String auditStatus;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0,无效,1有效
	private Integer enabledFlag;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	 * 设置：年份
	 */
	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}
	/**
	 * 获取：年份
	 */
	public String getExamYear() {
		return examYear;
	}
	/**
	 * 设置：年月
	 */
	public void setExamName(String examName) {
		this.examName = examName;
	}
	/**
	 * 获取：年月
	 */
	public String getExamName() {
		return examName;
	}
	/**
	 * 设置：月份
	 */
	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}
	/**
	 * 获取：月份
	 */
	public String getExamMonth() {
		return examMonth;
	}
	/**
	 * 设置：助学生报考开始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * 获取：助学生报考开始日期
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * 设置：助学生报考结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：助学生报考结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：助学生补报开始日期
	 */
	public void setBeginDateAppend(Date beginDateAppend) {
		this.beginDateAppend = beginDateAppend;
	}
	/**
	 * 获取：助学生补报开始日期
	 */
	public Date getBeginDateAppend() {
		return beginDateAppend;
	}
	/**
	 * 设置：助学生补报结束日期
	 */
	public void setEndDateAppend(Date endDateAppend) {
		this.endDateAppend = endDateAppend;
	}
	/**
	 * 获取：助学生补报结束日期
	 */
	public Date getEndDateAppend() {
		return endDateAppend;
	}
	/**
	 * 设置：社会生报考开始日期
	 */
	public void setBeginDate1(Date beginDate1) {
		this.beginDate1 = beginDate1;
	}
	/**
	 * 获取：社会生报考开始日期
	 */
	public Date getBeginDate1() {
		return beginDate1;
	}
	/**
	 * 设置：社会生报考结束日期
	 */
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	/**
	 * 获取：社会生报考结束日期
	 */
	public Date getEndDate1() {
		return endDate1;
	}
	/**
	 * 设置：社会生补报开始日期
	 */
	public void setBeginDateAppend1(Date beginDateAppend1) {
		this.beginDateAppend1 = beginDateAppend1;
	}
	/**
	 * 获取：社会生补报开始日期
	 */
	public Date getBeginDateAppend1() {
		return beginDateAppend1;
	}
	/**
	 * 设置：社会生补报结束日期
	 */
	public void setEndDateAppend1(Date endDateAppend1) {
		this.endDateAppend1 = endDateAppend1;
	}
	/**
	 * 获取：社会生补报结束日期
	 */
	public Date getEndDateAppend1() {
		return endDateAppend1;
	}
	/**
	 * 设置：考场编排考试日期
	 */
	public void setArrangeBeginDate(Date arrangeBeginDate) {
		this.arrangeBeginDate = arrangeBeginDate;
	}
	/**
	 * 获取：考场编排考试日期
	 */
	public Date getArrangeBeginDate() {
		return arrangeBeginDate;
	}
	/**
	 * 设置：考场编排考试日期
	 */
	public void setArrangeEndDate(Date arrangeEndDate) {
		this.arrangeEndDate = arrangeEndDate;
	}
	/**
	 * 获取：考场编排考试日期
	 */
	public Date getArrangeEndDate() {
		return arrangeEndDate;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：确认状态
	 */
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	/**
	 * 获取：确认状态
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditStatus() {
		return auditStatus;
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
	 * 设置：0,无效,1有效
	 */
	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * 获取：0,无效,1有效
	 */
	public Integer getEnabledFlag() {
		return enabledFlag;
	}
}
