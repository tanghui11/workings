package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;



/**
 * 考区专业课程报考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class RegionSpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业编号
	private String specialityid;
	//考区编号
	private Long regionid;
	//专业开设编号
	private Long specialityRecordid;
	//报考专业名称
	private String name;
	//报考专业名称
	private String type;
	//专业代码
	private String subjectCode;
	//专业名称
	private String subjectName;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	private String schoolSpecialityRegid;
	//专业名称
	private String specialityName;
	//方向
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSchoolSpecialityRegid() {
		return schoolSpecialityRegid;
	}

	public void setSchoolSpecialityRegid(String schoolSpecialityRegid) {
		this.schoolSpecialityRegid = schoolSpecialityRegid;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	 * 设置：专业编号
	 */
	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
	/**
	 * 获取：专业编号
	 */
	public String getSpecialityid() {
		return specialityid;
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
	 * 设置：专业开设编号
	 */
	public void setSpecialityRecordid(Long specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
	/**
	 * 获取：专业开设编号
	 */
	public Long getSpecialityRecordid() {
		return specialityRecordid;
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
	 * 设置：报考专业名称
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * 获取：报考专业名称
	 */
	public String getName() { return name; }
	/**
	 * 设置：层次
	 */
	public void setType(String type) { this.type = type; }
	/**
	 * 获取：层次
	 */
	public String getType() { return type; }
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
