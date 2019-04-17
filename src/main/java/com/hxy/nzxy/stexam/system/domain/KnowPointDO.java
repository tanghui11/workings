package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
public class KnowPointDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//章节编号
	private String chapterid;
	//名称
	private String name;
	//名称
	private String pinyin;
	//描述
	private String intro;
	//状态
	private String status;
	//操作员
	private Long operator;
	//操作日期
	private Date updateDate;

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
	 * 设置：章节编号
	 */
	public void setChapterid(String chapterid) {
		this.chapterid = chapterid;
	}
	/**
	 * 获取：章节编号
	 */
	public String getChapterid() {
		return chapterid;
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
	 * 设置：名称
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**
	 * 获取：名称
	 */
	public String getPinyin() {
		return pinyin;
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
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作员
	 */
	public Long getOperator() {
		return operator;
	}
	/**
	 * 设置：操作日期
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：操作日期
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
}
