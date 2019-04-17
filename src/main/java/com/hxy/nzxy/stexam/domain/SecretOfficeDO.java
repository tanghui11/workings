package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 保密室维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class SecretOfficeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考试任务编号
	private Long examTaskid;
	//考区编号
	private Long regionid;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//类型
	private String type;
	//保密室固定电话区号
	private String phonePre;
	//保密室固定电话号码
	private String phone;
	//负责人姓名
	private String chargeman;
	//负责人固定电话区号
	private String chargemanPhonePre;
	//负责人固定电话号码
	private String chargemanPhone;
	//负责人手机
	private String chargemanTel;
	//值班人员名单
	private String dutyMan;
	//是否配备武警/公安人员值班
	private String hasForce;
	//武警/公安人员数量
	private Integer forceNum;
	//考区主任姓名
	private String leader;
	//考区主任电话
	private String leaderPhone;
	//考区主任手机
	private String leaderMphone;
	//审核状态
	private String auditStatus;
	//审核操作员
	private String auditOperator;
	//审核日期
	private Date auditDate;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	private String examTaskName;

	public String getExamTaskName() {
		return examTaskName;
	}

	public void setExamTaskName(String examTaskName) {
		this.examTaskName = examTaskName;
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
	 * 设置：考试任务编号
	 */
	public void setExamTaskid(Long examTaskid) {
		this.examTaskid = examTaskid;
	}
	/**
	 * 获取：考试任务编号
	 */
	public Long getExamTaskid() {
		return examTaskid;
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
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：保密室固定电话区号
	 */
	public void setPhonePre(String phonePre) {
		this.phonePre = phonePre;
	}
	/**
	 * 获取：保密室固定电话区号
	 */
	public String getPhonePre() {
		return phonePre;
	}
	/**
	 * 设置：保密室固定电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：保密室固定电话号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：负责人姓名
	 */
	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}
	/**
	 * 获取：负责人姓名
	 */
	public String getChargeman() {
		return chargeman;
	}
	/**
	 * 设置：负责人固定电话区号
	 */
	public void setChargemanPhonePre(String chargemanPhonePre) {
		this.chargemanPhonePre = chargemanPhonePre;
	}
	/**
	 * 获取：负责人固定电话区号
	 */
	public String getChargemanPhonePre() {
		return chargemanPhonePre;
	}
	/**
	 * 设置：负责人固定电话号码
	 */
	public void setChargemanPhone(String chargemanPhone) {
		this.chargemanPhone = chargemanPhone;
	}
	/**
	 * 获取：负责人固定电话号码
	 */
	public String getChargemanPhone() {
		return chargemanPhone;
	}
	/**
	 * 设置：负责人手机
	 */
	public void setChargemanTel(String chargemanTel) {
		this.chargemanTel = chargemanTel;
	}
	/**
	 * 获取：负责人手机
	 */
	public String getChargemanTel() {
		return chargemanTel;
	}
	/**
	 * 设置：值班人员名单
	 */
	public void setDutyMan(String dutyMan) {
		this.dutyMan = dutyMan;
	}
	/**
	 * 获取：值班人员名单
	 */
	public String getDutyMan() {
		return dutyMan;
	}
	/**
	 * 设置：是否配备武警/公安人员值班
	 */
	public void setHasForce(String hasForce) {
		this.hasForce = hasForce;
	}
	/**
	 * 获取：是否配备武警/公安人员值班
	 */
	public String getHasForce() {
		return hasForce;
	}
	/**
	 * 设置：武警/公安人员数量
	 */
	public void setForceNum(Integer forceNum) {
		this.forceNum = forceNum;
	}
	/**
	 * 获取：武警/公安人员数量
	 */
	public Integer getForceNum() {
		return forceNum;
	}
	/**
	 * 设置：考区主任姓名
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	/**
	 * 获取：考区主任姓名
	 */
	public String getLeader() {
		return leader;
	}
	/**
	 * 设置：考区主任电话
	 */
	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	/**
	 * 获取：考区主任电话
	 */
	public String getLeaderPhone() {
		return leaderPhone;
	}
	/**
	 * 设置：考区主任手机
	 */
	public void setLeaderMphone(String leaderMphone) {
		this.leaderMphone = leaderMphone;
	}
	/**
	 * 获取：考区主任手机
	 */
	public String getLeaderMphone() {
		return leaderMphone;
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
	public void setAuditOperator(String auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核操作员
	 */
	public String getAuditOperator() {
		return auditOperator;
	}
	/**
	 * 设置：审核日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审核日期
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
