package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-19 09:39:15
 */
public class OrgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//父编号
	private String parentid;
	//类别
	private String type;
	//代码
	private String code;
	//学校类别
	private String schoolType;
	//名称
	private String name;
	//简称
	private String abbName;
	//拼音
	private String pinyin;
	//地址
	private String address;
	//邮编
	private String postCode;
	//联系电话
	private String phone;
	//传真
	private String fax;
	//电子邮件
	private String email;
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
	 * 设置：父编号
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：父编号
	 */
	public String getParentid() {
		return parentid;
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
	 * 设置：代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 获取：学校类别
	 */
	public String getSchoolType() {
		return schoolType;
	}
	/**
	 * 设置：学校类别
	 */
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
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

	public String getAbbName() {
		return abbName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	/**
	 * 设置：拼音
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**
	 * 获取：拼音
	 */
	public String getPinyin() {
		return pinyin;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮编
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 获取：传真
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * 设置：电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮件
	 */
	public String getEmail() {
		return email;
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
