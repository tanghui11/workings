package com.hxy.nzxy.stexam.region.exam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 犯规及处罚设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
public class FoulPunishRegionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//违规类别
	private String type;
	//处理方式
	private String flag;
	//代码
	private String code;
	//名称
	private String name;
	//处罚公式
	private String formula;
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
	 * 设置：违规类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：违规类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：处理方式
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：处理方式
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代码
	 */
	public String getCode() {
		return code;
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
	 * 设置：处罚公式
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * 获取：处罚公式
	 */
	public String getFormula() {
		return formula;
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
}
