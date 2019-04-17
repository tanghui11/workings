package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 科目管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
public class SubjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//名称
	private String name;
	//课程类别
	private String type;
	//子课程列表
	private String childList;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获取：名称
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：课程类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：子课程列表
	 */
	public String getChildList() {
		return childList;
	}
	/**
	 * 设置：子课程列表
	 */
	public void setChildList(String childList) {
		this.childList = childList;
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
