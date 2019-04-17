package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 教材管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class BookDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//教材类别
	private String classify;
	//课程编号
	private String courseid;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//主编
	private String chiefEditor;
	//出板社
	private String publisher;
	//版次
	private String version;
	//单价
	private BigDecimal price;
	//教材类别
	private String type;
	//备注
	private String remark;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	/**
	 * 设置：编号
     * @param id
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
	 * 设置：教材类别
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：教材类别
	 */
	public String getClassify() {
		return classify;
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
	 * 设置：主编
	 */
	public void setChiefEditor(String chiefEditor) {
		this.chiefEditor = chiefEditor;
	}
	/**
	 * 获取：主编
	 */
	public String getChiefEditor() {
		return chiefEditor;
	}
	/**
	 * 设置：出板社
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * 获取：出板社
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * 设置：版次
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版次
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：单价
     * @param price
     */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：教材类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：教材类别
	 */
	public String getType() {
		return type;
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
