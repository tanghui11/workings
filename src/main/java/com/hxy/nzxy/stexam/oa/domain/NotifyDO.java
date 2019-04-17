package com.hxy.nzxy.stexam.oa.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;



/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
public class NotifyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//机构编号
	private String orgid;
	//类型
	private String type;
	//标题
	private String title;
	//内容
	private String content;
	//置顶
	private String isTop;
	//状态
	private String status;
	//操作员
	private Long operator;
	//操作时间
	private Date updateDate;

	private Long[] userIds;

	private String orgIds;

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
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
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	public Long[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Long[] userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "NotifyDO{" + "id=" + id + ", orgid=" + orgid + ", type='" + type + '\'' + ", title='" + title + '\'' + ", content='" + content + '\'' + ", isTop='" + isTop + '\'' + ", status='" + status + '\'' + ", operator=" + operator + ", updateDate=" + updateDate + '}';
	}
}
