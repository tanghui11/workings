package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2019-03-19 11:58:48
 */
public class NativeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String parentid;
	//
	private String code;
	//
	private String name;
	//
	private String pinyin;
	//
	private String operator;
	//
	private String updateDate;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * 设置：
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**
	 * 获取：
	 */
	public String getPinyin() {
		return pinyin;
	}
	/**
	 * 设置：
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public String getUpdateDate() {
		return updateDate;
	}
}
