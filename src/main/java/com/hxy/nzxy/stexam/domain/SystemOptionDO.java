package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统选项
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 11:35:19
 */
public class SystemOptionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//应用名称
	private String appid;
	//编号
	private String name;
	//值
	private String value;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：编号
	 */
	public String getName() {
		return name;
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
