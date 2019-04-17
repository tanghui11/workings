package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 * 准考证号表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 14:32:12
 */
public class SystemStudentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//账号名称
	private String userName;
	//序列号值
	private Integer value;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0,无效,1有效
	private Integer enabledFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 设置：账号名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：账号名称
	 */
	public String getUserName() {
		return userName;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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
