package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 通用顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CommonCourseReplaceItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//课程顶替编号
	private Long courseReplaceid;
	//课程编号
	private String courseid;
	//课程名称
	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	//备注
	private String remark;
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
	 * 设置：课程顶替编号
	 */
	public void setCourseReplaceid(Long courseReplaceid) {
		this.courseReplaceid = courseReplaceid;
	}
	/**
	 * 获取：课程顶替编号
	 */
	public Long getCourseReplaceid() {
		return courseReplaceid;
	}
	/**
	 * 设置：课程编号
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：课程编号
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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

	public String getCommomCourseReplaceid() {
		return commomCourseReplaceid;
	}

	public void setCommomCourseReplaceid(String commomCourseReplaceid) {
		this.commomCourseReplaceid = commomCourseReplaceid;
	}

	public String getCommomCourseReplaceItemid() {
		return commomCourseReplaceItemid;
	}

	public void setCommomCourseReplaceItemid(String commomCourseReplaceItemid) {
		this.commomCourseReplaceItemid = commomCourseReplaceItemid;
	}

	private String commomCourseReplaceid;
	private String commomCourseReplaceItemid;

	public String getCommomCourseReplaceName() {
		return commomCourseReplaceName;
	}

	public void setCommomCourseReplaceName(String commomCourseReplaceName) {
		this.commomCourseReplaceName = commomCourseReplaceName;
	}

	public String getCommomCourseReplaceItemName() {
		return commomCourseReplaceItemName;
	}

	public void setCommomCourseReplaceItemName(String commomCourseReplaceItemName) {
		this.commomCourseReplaceItemName = commomCourseReplaceItemName;
	}

	private String commomCourseReplaceName;
	private String commomCourseReplaceItemName;

}