package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;

public class MenuDO implements Serializable {

	private static final long serialVersionUID = 1L;
	// 编号
	private String id;
	// 应用编号
	private String appid;
	// 父菜单ID，一级菜单为0
	private String parentid;
	// 菜单名称
	private String name;
	// 菜单URL
	private String url;
	// 授权(多个用逗号分隔，如：user:list,user:create)
	private String perms;
	// 类型 0：目录 1：菜单 2：按钮
	private Integer type;
	// 菜单图标
	private String icon;
	// 打开目标
	private String target;
	// 排序
	private String seq;;
	// 状态
	private String status;
	// 操作员
	private String operator;
	// 操作日期
	private Date updateDate;
	//自动增加按钮标志
	private String autoInsertButton;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAutoInsertButton() {
		return autoInsertButton;
	}

	public void setAutoInsertButton(String autoInsertButton) {
		this.autoInsertButton = autoInsertButton;
	}

	@Override
	public String toString() {
		return "MenuDO{" +
				"id=" + id +
				", appid=" + appid +
				", parentid=" + parentid +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", perms='" + perms + '\'' +
				", type=" + type +
				", icon='" + icon + '\'' +
				", target='" + target + '\'' +
				", seq=" + seq +
				", operator='" + operator + '\'' +
				", updateDate=" + updateDate +
				", autoInsertButton='" + autoInsertButton + '\'' +
				'}';
	}
}
