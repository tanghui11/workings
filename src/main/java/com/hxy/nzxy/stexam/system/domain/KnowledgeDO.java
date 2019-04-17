package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考试知识
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:05
 */
public class KnowledgeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//版本
	private String version;
	//学段
	private String gradeType;
	//科目编号
	private String subjectid;
	//描述
	private String intro;
	//发布状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

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
	 * 设置：版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：学段
	 */
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	/**
	 * 获取：学段
	 */
	public String getGradeType() {
		return gradeType;
	}
	/**
	 * 设置：科目编号
	 */
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	/**
	 * 获取：科目编号
	 */
	public String getSubjectid() {
		return subjectid;
	}
	/**
	 * 设置：描述
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	/**
	 * 获取：描述
	 */
	public String getIntro() {
		return intro;
	}
	/**
	 * 设置：发布状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：发布状态
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
}
