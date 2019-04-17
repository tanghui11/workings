package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 教学点管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class TeachSiteDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//办学点编号
	private Long schoolSiteid;
	//考区编号
	private Long regionid;
	//学院编号
	private Long collegeid;
	//单位名称
	private String name;
	//拼音
	private String pinyin;
	//联系人
	private String linkman;
	//固定电话
	private String phone;
	//移动电话
	private String mphone;
	//邮箱
	private String email;
	//邮编
	private String postCode;
	//联系地址
	private String address;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	private String schoolSiteName;
	private String regionName;
	private String parentName;

	public Long getRegionid() {
		return regionid;
	}

	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getSchoolSiteName() {
		return schoolSiteName;
	}

	public void setSchoolSiteName(String schoolSiteName) {
		this.schoolSiteName = schoolSiteName;
	}

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：办学点编号
	 */
	public void setSchoolSiteid(Long schoolSiteid) {
		this.schoolSiteid = schoolSiteid;
	}
	/**
	 * 获取：办学点编号
	 */
	public Long getSchoolSiteid() {
		return schoolSiteid;
	}
	/**
	 * 设置：学院编号
	 */
	public void setCollegeid(Long collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * 获取：学院编号
	 */
	public Long getCollegeid() {
		return collegeid;
	}
	/**
	 * 设置：单位名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：单位名称
	 */
	public String getName() {
		return name;
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
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置：固定电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：固定电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	/**
	 * 获取：移动电话
	 */
	public String getMphone() {
		return mphone;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
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
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
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
	 * 设置：0无效,1有效
	 */
	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * 获取：0无效,1有效
	 */
	public Integer getEnabledFlag() {
		return enabledFlag;
	}
}
