package com.hxy.nzxy.stexam.center.region.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考点上报
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public class ExamSiteSubmitDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考试任务编号
	private Long examTaskid;
	//考点编号
	private Long examSiteid;
	//数量
	private Integer num;
	//状态
	private String status;
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
	 * 设置：考点编号
	 */
	public void setExamSiteid(Long examSiteid) {
		this.examSiteid = examSiteid;
	}
	/**
	 * 获取：考点编号
	 */
	public Long getExamSiteid() {
		return examSiteid;
	}
	/**
	 * 设置：数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNum() {
		return num;
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
