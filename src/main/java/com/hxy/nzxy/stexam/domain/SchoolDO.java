package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 助学组织
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class SchoolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//代码
	private String code;
	//考区编号
	private Long regionid;
	//助学组织名称
	private String name;
	//拼音
	private String pinyin;
	//办学许可证编号
	private String bCode;
	//办学许可证发放单位
	private String bSendUnit;
	//办学许可证发放日期
	private Date bSendDate;
	//助学许可证编号
	private String zCode;
	//助学许可证发放单位
	private String zSendUnit;
	//助学许可证发放日期
	private Date zSendDate;
	//助学主体类型
	private String type;
	//法人名称
	private String legalPerson;
	//法人职务
	private String legalPersonDuty;
	//兼职教学人员
	private Integer jTeacherNum;
	//专职教学人员
	private Integer zTeacherNum;
	//管理人员总数
	private Integer managerNum;
	//教学和管理人员数
	private Integer allNum;
	//负责人
	private String charger;
	//联系地址
	private String address;
	//邮政编码
	private String postCode;
	//固定电话
	private String phone;
	//移动电话
	private String mphone;
	//传真
	private String fax;
	//邮件
	private String email;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

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
	 * 设置：考区编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：考区编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：助学组织名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：助学组织名称
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
	 * 设置：办学许可证编号
	 */
	public void setBCode(String bCode) {
		this.bCode = bCode;
	}
	/**
	 * 获取：办学许可证编号
	 */
	public String getBCode() {
		return bCode;
	}
	/**
	 * 设置：办学许可证发放单位
	 */
	public void setBSendUnit(String bSendUnit) {
		this.bSendUnit = bSendUnit;
	}
	/**
	 * 获取：办学许可证发放单位
	 */
	public String getBSendUnit() {
		return bSendUnit;
	}
	/**
	 * 设置：办学许可证发放日期
	 */
	public void setBSendDate(Date bSendDate) {
		this.bSendDate = bSendDate;
	}
	/**
	 * 获取：办学许可证发放日期
	 */
	public Date getBSendDate() {
		return bSendDate;
	}
	/**
	 * 设置：助学许可证编号
	 */
	public void setZCode(String zCode) {
		this.zCode = zCode;
	}
	/**
	 * 获取：助学许可证编号
	 */
	public String getZCode() {
		return zCode;
	}
	/**
	 * 设置：助学许可证发放单位
	 */
	public void setZSendUnit(String zSendUnit) {
		this.zSendUnit = zSendUnit;
	}
	/**
	 * 获取：助学许可证发放单位
	 */
	public String getZSendUnit() {
		return zSendUnit;
	}
	/**
	 * 设置：助学许可证发放日期
	 */
	public void setZSendDate(Date zSendDate) {
		this.zSendDate = zSendDate;
	}
	/**
	 * 获取：助学许可证发放日期
	 */
	public Date getZSendDate() {
		return zSendDate;
	}
	/**
	 * 设置：助学主体类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：助学主体类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：法人名称
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	/**
	 * 获取：法人名称
	 */
	public String getLegalPerson() {
		return legalPerson;
	}
	/**
	 * 设置：法人职务
	 */
	public void setLegalPersonDuty(String legalPersonDuty) {
		this.legalPersonDuty = legalPersonDuty;
	}
	/**
	 * 获取：法人职务
	 */
	public String getLegalPersonDuty() {
		return legalPersonDuty;
	}
	/**
	 * 设置：兼职教学人员
	 */
	public void setJTeacherNum(Integer jTeacherNum) {
		this.jTeacherNum = jTeacherNum;
	}
	/**
	 * 获取：兼职教学人员
	 */
	public Integer getJTeacherNum() {
		return jTeacherNum;
	}
	/**
	 * 设置：专职教学人员
	 */
	public void setZTeacherNum(Integer zTeacherNum) {
		this.zTeacherNum = zTeacherNum;
	}
	/**
	 * 获取：专职教学人员
	 */
	public Integer getZTeacherNum() {
		return zTeacherNum;
	}
	/**
	 * 设置：管理人员总数
	 */
	public void setManagerNum(Integer managerNum) {
		this.managerNum = managerNum;
	}
	/**
	 * 获取：管理人员总数
	 */
	public Integer getManagerNum() {
		return managerNum;
	}
	/**
	 * 设置：教学和管理人员数
	 */
	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
	/**
	 * 获取：教学和管理人员数
	 */
	public Integer getAllNum() {
		return allNum;
	}
	/**
	 * 设置：负责人
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}
	/**
	 * 获取：负责人
	 */
	public String getCharger() {
		return charger;
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
	 * 设置：邮政编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostCode() {
		return postCode;
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
	 * 设置：邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮件
	 */
	public String getEmail() {
		return email;
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
