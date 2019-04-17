package com.hxy.nzxy.stexam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class SpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//类别
	private String type;
	//专业层次
	private String classify;
	//层次类型
	private String flag;
	//委托类型
	private String grantType;
	//学分要求
	private Integer score;
	//审批状态
	private String auditStatus;
	//审批时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date auditDate;
	//专科代码
	private String zkSpecialityid;
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
	 * 设置：专业层次
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：专业层次
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * 设置：层次类型
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：层次类型
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：委托类型
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	/**
	 * 获取：委托类型
	 */
	public String getGrantType() {
		return grantType;
	}
	/**
	 * 设置：学分要求
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：学分要求
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：审批状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审批时间
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审批时间
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	/**
	 * 设置：专科代码
	 */
	public void setZkSpecialityid(String zkSpecialityid) {
		this.zkSpecialityid = zkSpecialityid;
	}
	/**
	 * 获取：专科代码
	 */
	public String getZkSpecialityid() {
		return zkSpecialityid;
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

	public String getSpecialityRecordid() {
		return specialityRecordid;
	}

	public void setSpecialityRecordid(String specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}

	private String specialityRecordid;
}
