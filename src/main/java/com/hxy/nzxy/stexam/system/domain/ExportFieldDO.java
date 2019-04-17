package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 导出数据字段
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 17:39:29
 */
public class ExportFieldDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//表编号
	private Long exportTableid;
	//序号
	private Integer seq;
	//字段名称
	private String name;
	//字段类型
	private String type;
	//字段长度
	private Integer length;
	//小数位
	private Integer decimalLength;
	//默认值
	private String defaultValue;
	//转义类型
	private String transType;
	//表名
	private String tableName;
	//字典名
	private String fieldName;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExportTableid() {
		return exportTableid;
	}

	public void setExportTableid(Long exportTableid) {
		this.exportTableid = exportTableid;
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
	 * 设置：字段名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：字段名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：字段类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：字段类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：字段长度
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * 获取：字段长度
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * 设置：小数位
	 */
	public void setDecimalLength(Integer decimalLength) {
		this.decimalLength = decimalLength;
	}
	/**
	 * 获取：小数位
	 */
	public Integer getDecimalLength() {
		return decimalLength;
	}
	/**
	 * 设置：默认值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * 获取：默认值
	 */
	public String getDefaultValue() {
		return defaultValue;
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
