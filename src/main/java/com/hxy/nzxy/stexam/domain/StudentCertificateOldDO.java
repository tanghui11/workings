package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 老毕业生数据
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentCertificateOldDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//类别
	private Long id;
	//类别
	private String type;
	//毕业证号
	private String code;
	//姓名
	private String name;
	//身份证号
	private String certificateNo;
	//性别
	private String gender;
	//籍贯省
	private String nativeProvince;
	//籍贯县
	private String nativeCounty;
	//年龄
	private Integer age;
	//专业名称
	private String specialityName;
	//专业层次
	private String specialityLevels;
	//毕业院校
	private String schoolName;
	//毕业时间
	private Date graduateDate;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	//搜索内容
	private String searchName;

	/**
	 * 设置：类别
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：类别
	 */
	public Long getId() {
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
	 * 设置：毕业证号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：毕业证号
	 */
	public String getCode() {
		return code;
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
	 * 设置：籍贯省
	 */
	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}
	/**
	 * 获取：籍贯省
	 */
	public String getNativeProvince() {
		return nativeProvince;
	}
	/**
	 * 设置：籍贯县
	 */
	public void setNativeCounty(String nativeCounty) {
		this.nativeCounty = nativeCounty;
	}
	/**
	 * 获取：籍贯县
	 */
	public String getNativeCounty() {
		return nativeCounty;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：专业名称
	 */
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	/**
	 * 获取：专业名称
	 */
	public String getSpecialityName() {
		return specialityName;
	}
	/**
	 * 设置：专业层次
	 */
	public void setSpecialityLevels(String specialityLevels) {
		this.specialityLevels = specialityLevels;
	}
	/**
	 * 获取：专业层次
	 */
	public String getSpecialityLevels() {
		return specialityLevels;
	}
	/**
	 * 设置：毕业院校
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * 获取：毕业院校
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * 设置：毕业时间
	 */
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	/**
	 * 获取：毕业时间
	 */
	public Date getGraduateDate() {
		return graduateDate;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}
