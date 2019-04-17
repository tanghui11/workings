package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 报表管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-21 09:35:51
 */
public class RptSqlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//类别
	private String type;
	//sql
	private String sqlText;
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
	 * 设置：sql
	 */
	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}
	/**
	 * 获取：sql
	 */
	public String getSqlText() {
		return sqlText;
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
