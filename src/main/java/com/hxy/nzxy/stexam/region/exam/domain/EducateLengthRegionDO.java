package com.hxy.nzxy.stexam.region.exam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 学制定义
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
public class EducateLengthRegionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业层次
	private String classify;
	//助学方式
	private String type;
	//学制
	private String length;
	//学制长度
	private Float studyLength;
	//毕业学制长度
	private Float graduateLength;
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
	 * 设置：专业层次
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：专业层次
	 */
	public String getClassify() {
		return classify;
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
	 * 设置：学制
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * 获取：学制
	 */
	public String getLength() {
		return length;
	}
	/**
	 * 设置：学制长度
	 */
	public void setStudyLength(Float studyLength) {
		this.studyLength = studyLength;
	}
	/**
	 * 获取：学制长度
	 */
	public Float getStudyLength() {
		return studyLength;
	}
	/**
	 * 设置：毕业学制长度
	 */
	public void setGraduateLength(Float graduateLength) {
		this.graduateLength = graduateLength;
	}
	/**
	 * 获取：毕业学制长度
	 */
	public Float getGraduateLength() {
		return graduateLength;
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
