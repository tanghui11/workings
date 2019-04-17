package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class StudentOutDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//编号
	private Long id;
	//姓名
	private String name;
	//拼音
	private String pinyin;
	//转出地区编号
	private Long regionid;
	//准考证号
	private String studentid;
	//专业代码
	private String specialityid;
	//档案编号
	private String code;
	//转入省份
	private String province;
	//转入地市
	private String city;
	//转出原因
	private String cause;
	//转入准考证号
	private String zkInZkz;
	//转入专业代码
	private String zkInZyDm;
	//转入专业方向
	private String zkZyFx;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	private String regionName;
	private String specialityName;
	private String provinceName;
	private String cityName;
	private String zkInZyDmName;

	public String getZkInZyDmName() {
		return zkInZyDmName;
	}

	public void setZkInZyDmName(String zkInZyDmName) {
		this.zkInZyDmName = zkInZyDmName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	 * 设置：转出地区编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：转出地区编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：准考证号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：准考证号
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * 设置：专业代码
	 */
	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
	/**
	 * 获取：专业代码
	 */
	public String getSpecialityid() {
		return specialityid;
	}
	/**
	 * 设置：档案编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：档案编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：转入省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：转入省份
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：转入地市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：转入地市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：转出原因
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * 获取：转出原因
	 */
	public String getCause() {
		return cause;
	}
	/**
	 * 设置：转入准考证号
	 */
	public void setZkInZkz(String zkInZkz) {
		this.zkInZkz = zkInZkz;
	}
	/**
	 * 获取：转入准考证号
	 */
	public String getZkInZkz() {
		return zkInZkz;
	}
	/**
	 * 设置：转入专业代码
	 */
	public void setZkInZyDm(String zkInZyDm) {
		this.zkInZyDm = zkInZyDm;
	}
	/**
	 * 获取：转入专业代码
	 */
	public String getZkInZyDm() {
		return zkInZyDm;
	}
	/**
	 * 设置：转入专业方向
	 */
	public void setZkZyFx(String zkZyFx) {
		this.zkZyFx = zkZyFx;
	}
	/**
	 * 获取：转入专业方向
	 */
	public String getZkZyFx() {
		return zkZyFx;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
