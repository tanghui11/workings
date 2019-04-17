package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生注册_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentRegHisDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//报考专业编号
	private Long studentSpecialityid;
	//注册开始日期
	private Date regBeginDate;
	//注册结束日期
	private Date regEndDate;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	//准考证号
	private String studentid;
	private String keyValue;

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
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
	 * 设置：报考专业编号
	 */
	public void setStudentSpecialityid(Long studentSpecialityid) {
		this.studentSpecialityid = studentSpecialityid;
	}
	/**
	 * 获取：报考专业编号
	 */
	public Long getStudentSpecialityid() {
		return studentSpecialityid;
	}
	/**
	 * 设置：注册开始日期
	 */
	public void setRegBeginDate(Date regBeginDate) {
		this.regBeginDate = regBeginDate;
	}
	/**
	 * 获取：注册开始日期
	 */
	public Date getRegBeginDate() {
		return regBeginDate;
	}
	/**
	 * 设置：注册结束日期
	 */
	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}
	/**
	 * 获取：注册结束日期
	 */
	public Date getRegEndDate() {
		return regEndDate;
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
	 * 设置：数据标记
	 */
	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * 获取：数据标记
	 */
	public Integer getDbFlag() {
		return dbFlag;
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
