package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentGradeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//姓名
	private String name;
	//姓名1
	private String nameOld;
	//英文名
	private String ename;
	//英文名1
	private String enameOld;
	//拼音
	private String pinyin;
	//拼音1
	private String pinyinOld;
	//性别
	private String gender;
	//性别1
	private String genderOld;
	//户籍
	private String homeType;
	//户籍1
	private String homeTypeOld;
	//政治面貌
	private String politics;
	//政治面貌1
	private String politicsOld;
	//民族
	private String nation;
	//民族1
	private String nationOld;
	//职业
	private String profession;
	//职业1
	private String professionOld;
	//出生日期
	private Date birthday;
	//出生日期1
	private Date birthdayOld;
	//籍贯
	private String nativePlace;
	//籍贯1
	private String nativeOld;
	//证件类别
	private String certificateType;
	//证件类别1
	private String certificateTypeOld;
	//身份证号
	private String certificateNo;
	//身份证号1
	private String certificateNoOld;
	//固定电话
	private String phone;
	//固定电话1
	private String phoneOld;
	//移动电话
	private String mphone;
	//移动电话1
	private String mphoneOld;
	//通讯地址
	private String address;
	//通讯地址1
	private String addressOld;
	//邮编
	private String postCode;
	//邮编1
	private String postCodeOld;
	//电子邮箱
	private String email;
	//电子邮箱1
	private String emailOld;
	//审核状态
	private String auditStatus;
	//审核操作员
	private Long auditOperator;
	//审核操作日期
	private Date auditDate;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：姓名1
	 */
	public void setNameOld(String nameOld) {
		this.nameOld = nameOld;
	}
	/**
	 * 获取：姓名1
	 */
	public String getNameOld() {
		return nameOld;
	}
	/**
	 * 设置：英文名
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：英文名
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * 设置：英文名1
	 */
	public void setEnameOld(String enameOld) {
		this.enameOld = enameOld;
	}
	/**
	 * 获取：英文名1
	 */
	public String getEnameOld() {
		return enameOld;
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
	 * 设置：拼音1
	 */
	public void setPinyinOld(String pinyinOld) {
		this.pinyinOld = pinyinOld;
	}
	/**
	 * 获取：拼音1
	 */
	public String getPinyinOld() {
		return pinyinOld;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：性别1
	 */
	public void setGenderOld(String genderOld) {
		this.genderOld = genderOld;
	}
	/**
	 * 获取：性别1
	 */
	public String getGenderOld() {
		return genderOld;
	}
	/**
	 * 设置：户籍
	 */
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	/**
	 * 获取：户籍
	 */
	public String getHomeType() {
		return homeType;
	}
	/**
	 * 设置：户籍1
	 */
	public void setHomeTypeOld(String homeTypeOld) {
		this.homeTypeOld = homeTypeOld;
	}
	/**
	 * 获取：户籍1
	 */
	public String getHomeTypeOld() {
		return homeTypeOld;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setPolitics(String politics) {
		this.politics = politics;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getPolitics() {
		return politics;
	}
	/**
	 * 设置：政治面貌1
	 */
	public void setPoliticsOld(String politicsOld) {
		this.politicsOld = politicsOld;
	}
	/**
	 * 获取：政治面貌1
	 */
	public String getPoliticsOld() {
		return politicsOld;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * 设置：民族1
	 */
	public void setNationOld(String nationOld) {
		this.nationOld = nationOld;
	}
	/**
	 * 获取：民族1
	 */
	public String getNationOld() {
		return nationOld;
	}
	/**
	 * 设置：职业
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * 获取：职业
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * 设置：职业1
	 */
	public void setProfessionOld(String professionOld) {
		this.professionOld = professionOld;
	}
	/**
	 * 获取：职业1
	 */
	public String getProfessionOld() {
		return professionOld;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：出生日期1
	 */
	public void setBirthdayOld(Date birthdayOld) {
		this.birthdayOld = birthdayOld;
	}
	/**
	 * 获取：出生日期1
	 */
	public Date getBirthdayOld() {
		return birthdayOld;
	}
	/**
	 * 设置：籍贯
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	/**
	 * 获取：籍贯
	 */
	public String getNativePlace() {
		return nativePlace;
	}
	/**
	 * 设置：籍贯1
	 */
	public void setNativeOld(String nativeOld) {
		this.nativeOld = nativeOld;
	}
	/**
	 * 获取：籍贯1
	 */
	public String getNativeOld() {
		return nativeOld;
	}
	/**
	 * 设置：证件类别
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	/**
	 * 获取：证件类别
	 */
	public String getCertificateType() {
		return certificateType;
	}
	/**
	 * 设置：证件类别1
	 */
	public void setCertificateTypeOld(String certificateTypeOld) {
		this.certificateTypeOld = certificateTypeOld;
	}
	/**
	 * 获取：证件类别1
	 */
	public String getCertificateTypeOld() {
		return certificateTypeOld;
	}
	/**
	 * 设置：身份证号
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * 获取：身份证号
	 */
	public String getCertificateNo() {
		return certificateNo;
	}
	/**
	 * 设置：身份证号1
	 */
	public void setCertificateNoOld(String certificateNoOld) {
		this.certificateNoOld = certificateNoOld;
	}
	/**
	 * 获取：身份证号1
	 */
	public String getCertificateNoOld() {
		return certificateNoOld;
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
	 * 设置：固定电话1
	 */
	public void setPhoneOld(String phoneOld) {
		this.phoneOld = phoneOld;
	}
	/**
	 * 获取：固定电话1
	 */
	public String getPhoneOld() {
		return phoneOld;
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
	 * 设置：移动电话1
	 */
	public void setMphoneOld(String mphoneOld) {
		this.mphoneOld = mphoneOld;
	}
	/**
	 * 获取：移动电话1
	 */
	public String getMphoneOld() {
		return mphoneOld;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：通讯地址1
	 */
	public void setAddressOld(String addressOld) {
		this.addressOld = addressOld;
	}
	/**
	 * 获取：通讯地址1
	 */
	public String getAddressOld() {
		return addressOld;
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
	 * 设置：邮编1
	 */
	public void setPostCodeOld(String postCodeOld) {
		this.postCodeOld = postCodeOld;
	}
	/**
	 * 获取：邮编1
	 */
	public String getPostCodeOld() {
		return postCodeOld;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：电子邮箱1
	 */
	public void setEmailOld(String emailOld) {
		this.emailOld = emailOld;
	}
	/**
	 * 获取：电子邮箱1
	 */
	public String getEmailOld() {
		return emailOld;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审核操作员
	 */
	public void setAuditOperator(Long auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核操作员
	 */
	public Long getAuditOperator() {
		return auditOperator;
	}
	/**
	 * 设置：审核操作日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审核操作日期
	 */
	public Date getAuditDate() {
		return auditDate;
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
