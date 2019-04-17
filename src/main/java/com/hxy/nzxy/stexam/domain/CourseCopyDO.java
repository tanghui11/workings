package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseCopyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//英文名称
	private String ename;
	//学分
	private Float score;
	//类别
	private String type;
	//分类
	private String classify;
	//属性
	private String attribute;
	//实践课程代码
	private String practiceCourseid;
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
	 * 设置：英文名称
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：英文名称
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * 设置：学分
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	/**
	 * 获取：学分
	 */
	public Float getScore() {
		return score;
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
	 * 设置：属性
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * 获取：属性
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * 设置：实践课程代码
	 */
	public void setPracticeCourseid(String practiceCourseid) {
		this.practiceCourseid = practiceCourseid;
	}
	/**
	 * 获取：实践课程代码
	 */
	public String getPracticeCourseid() {
		return practiceCourseid;
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
