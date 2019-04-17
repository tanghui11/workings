package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 平时成绩比例设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class SchoolScoreRatioDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//院校编号
	private Long schoolid;
	//助学方式
	private String type;
	//助学手段
	private String method;
	//成绩比例
	private Float ratio;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	private String schoolName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	 * 设置：院校编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：院校编号
	 */
	public Long getSchoolid() {
		return schoolid;
	}
	/**
	 * 设置：助学方式
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：助学方式
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：助学手段
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：助学手段
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：成绩比例
	 */
	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	/**
	 * 获取：成绩比例
	 */
	public Float getRatio() {
		return ratio;
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
