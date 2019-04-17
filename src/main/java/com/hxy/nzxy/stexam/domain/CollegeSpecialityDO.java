package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业管理对应
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class CollegeSpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//学院编号
	private Long collegeid;
	//专业注册备案编号
	private Long specialityRegid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	private  String status;
	private  String specialityid;
	private  String specialityname;
	private  String flag;
	private  String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}

	public String getSpecialityname() {
		return specialityname;
	}

	public void setSpecialityname(String specialityname) {
		this.specialityname = specialityname;
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
	 * 设置：专业注册备案编号
	 */
	public void setSpecialityRegid(Long specialityRegid) {
		this.specialityRegid = specialityRegid;
	}
	/**
	 * 获取：专业注册备案编号
	 */
	public Long getSpecialityRegid() {
		return specialityRegid;
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
