package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统选项
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 11:21:06
 */
public class SystemOptionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//应用名称
	private String appid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//编号
	private String id;
	private String name;
	//值
	private String value;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	private  String enabledFlag;

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	/**
	 * 设置：应用名称
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取：应用名称
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
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
}
