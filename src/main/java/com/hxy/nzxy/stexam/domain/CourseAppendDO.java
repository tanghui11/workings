package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业加考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseAppendDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业开设编号
	private Long specialityRecordid;
	private String specialityRecordName;
	//名称
	private String name;
	//分类
	private String classify;
	//类别
	private String type;
	//共几门课程
	private Integer courseNum;
	//至少选几门
	private Integer leastNum;
	//最少学分
	private Integer leastScore;
	//备注
	private String remark;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	public String getSpecialityRecordName() {
		return specialityRecordName;
	}

	public void setSpecialityRecordName(String specialityRecordName) {
		this.specialityRecordName = specialityRecordName;
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
	 * 设置：分类
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：分类
	 */
	public String getClassify() {
		return classify;
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
	 * 设置：共几门课程
	 */
	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	/**
	 * 获取：共几门课程
	 */
	public Integer getCourseNum() {
		return courseNum;
	}
	/**
	 * 设置：至少选几门
	 */
	public void setLeastNum(Integer leastNum) {
		this.leastNum = leastNum;
	}
	/**
	 * 获取：至少选几门
	 */
	public Integer getLeastNum() {
		return leastNum;
	}
	/**
	 * 设置：最少学分
	 */
	public void setLeastScore(Integer leastScore) {
		this.leastScore = leastScore;
	}
	/**
	 * 获取：最少学分
	 */
	public Integer getLeastScore() {
		return leastScore;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private String courseName;
}
