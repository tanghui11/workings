package com.hxy.nzxy.stexam.center.region.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考试违规
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public class StudentFoulDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//课程报考编号
	private Long studentCourseid;
	//违规编号
	private Long foulPunishid;
	//备注
	private String remark;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	/**
	 * 设置：课程报考编号
	 */
	public void setStudentCourseid(Long studentCourseid) {
		this.studentCourseid = studentCourseid;
	}
	/**
	 * 获取：课程报考编号
	 */
	public Long getStudentCourseid() {
		return studentCourseid;
	}
	/**
	 * 设置：违规编号
	 */
	public void setFoulPunishid(Long foulPunishid) {
		this.foulPunishid = foulPunishid;
	}
	/**
	 * 获取：违规编号
	 */
	public Long getFoulPunishid() {
		return foulPunishid;
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
