package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 报表管理表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
public class ReportDO implements Serializable {
	private static final long serialVersionUID = 1L;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	//
	private String id;

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppid() {
		return appid;
	}

	//
	private String appid;
	//编码
	private String code;
	//名称
	private String name;
	//代码
	private String param;
	//
	private Integer paramFlag;
	//模块编号
	private Integer modelid;
	//报表路径
	private String path;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	private String menuType;



	/**
	 * 设置：编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码
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
	 * 设置：代码
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * 获取：代码
	 */
	public String getParam() {
		return param;
	}
	/**
	 * 设置：
	 */
	public void setParamFlag(Integer paramFlag) {
		this.paramFlag = paramFlag;
	}
	/**
	 * 获取：
	 */
	public Integer getParamFlag() {
		return paramFlag;
	}
	/**
	 * 设置：模块编号
	 */
	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}
	/**
	 * 获取：模块编号
	 */
	public Integer getModelid() {
		return modelid;
	}
	/**
	 * 设置：报表路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：报表路径
	 */
	public String getPath() {
		return path;
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
