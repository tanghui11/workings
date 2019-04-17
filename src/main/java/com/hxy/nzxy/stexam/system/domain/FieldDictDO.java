package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 14:57:46
 */
public class FieldDictDO implements Serializable,Comparable<FieldDictDO>  {
	private static final long serialVersionUID = 1L;
	
	//数据库名称
	private String appid;
	//表名
	private String tableName;
	//字典名
	private String fieldName;
	//字段值
	private String fieldValue;
	//字段含义
	private String fieldMean;
	//类别
	private String type;
	//显示状态
	private String displayStatus;
	//启用状态
	private String status;
	//序号
	private Integer seq;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	// 数据字典显示时的编号
	private String id;
	// 数据字典显示时的名称
	private String name;
	//选中标志
	private String selectedSign;
	/**
	 * 设置：数据库名称
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取：数据库名称
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * 设置：表名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 获取：表名
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置：字典名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * 获取：字典名
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * 设置：字段值
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * 获取：字段值
	 */
	public String getFieldValue() {
		return fieldValue;
	}
	/**
	 * 设置：字段含义
	 */
	public void setFieldMean(String fieldMean) {
		this.fieldMean = fieldMean;
	}
	/**
	 * 获取：字段含义
	 */
	public String getFieldMean() {
		return fieldMean;
	}
	/**
	 * 设置：类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：显示状态
	 */
	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}
	/**
	 * 获取：显示状态
	 */
	public String getDisplayStatus() {
		return displayStatus;
	}
	/**
	 * 设置：启用状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：启用状态
	 */
	public String getStatus() {
		return status;
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
	 * 获取：数据字典显示时的编号
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：数据字典显示时的编号
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：数据字典显示时的名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：数据字典显示时的名称
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getSelectedSign() {
		return selectedSign;
	}

	public void setSelectedSign(String selectedSign) {
		this.selectedSign = selectedSign;
	}

	@Override
	public int compareTo(FieldDictDO o) {
		return this.getSeq().compareTo(o.getSeq());
	}

}
