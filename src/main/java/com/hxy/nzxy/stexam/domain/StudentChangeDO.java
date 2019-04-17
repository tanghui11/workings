package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生信息变更表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentChangeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证号
	private String studentid;
	//姓名
	private String name;
	//考生报考专业编号
	private Long specialityRecordid;
	//变更类别
	private String type;
	//有效期开始日期
	private Date beginDate;
	//有效期截止日期
	private Date endDate;
	//对象编号
	private Long objid;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

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
	 * 设置：考生报考专业编号
	 */
	public void setSpecialityRecordid(Long specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
	/**
	 * 获取：考生报考专业编号
	 */
	public Long getSpecialityRecordid() {
		return specialityRecordid;
	}
	/**
	 * 设置：变更类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：变更类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：有效期开始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * 获取：有效期开始日期
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * 设置：有效期截止日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：有效期截止日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：对象编号
	 */
	public void setObjid(Long objid) {
		this.objid = objid;
	}
	/**
	 * 获取：对象编号
	 */
	public Long getObjid() {
		return objid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
