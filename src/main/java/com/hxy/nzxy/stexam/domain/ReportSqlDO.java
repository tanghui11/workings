package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 报表sql表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
public class ReportSqlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private  String name;
	//报表编号
	private Integer reportId;
	//sql类型
	private String type;
	//SQL语句
	private String sql;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：报表编号
	 */
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	/**
	 * 获取：报表编号
	 */
	public Integer getReportId() {
		return reportId;
	}
	/**
	 * 设置：sql类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：sql类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：SQL语句
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * 获取：SQL语句
	 */
	public String getSql() {
		return sql;
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
