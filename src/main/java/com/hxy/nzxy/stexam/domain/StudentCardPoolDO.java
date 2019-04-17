package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 准考证打印池
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentCardPoolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//准考证号
	private String studentid;
	//打印状态
	private String status;
	//地州市代码
	private Long regionid;
	//县区代码
	private Long childRegionid;
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
	 * 设置：打印状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：打印状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：地州市代码
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：地州市代码
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：县区代码
	 */
	public void setChildRegionid(Long childRegionid) {
		this.childRegionid = childRegionid;
	}
	/**
	 * 获取：县区代码
	 */
	public Long getChildRegionid() {
		return childRegionid;
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
