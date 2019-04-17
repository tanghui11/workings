package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 知识点
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:06
 */
public class KnowledgePointDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//知识模块编号
	private String knowledgeModuleid;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//描述
	private String intro;
	//序号
	private String seq;
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
	 * 设置：知识模块编号
	 */
	public void setKnowledgeModuleid(String knowledgeModuleid) {
		this.knowledgeModuleid = knowledgeModuleid;
	}
	/**
	 * 获取：知识模块编号
	 */
	public String getKnowledgeModuleid() {
		return knowledgeModuleid;
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
	 * 设置：序号
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 获取：序号
	 */
	public void setSeq(String seq) {
		this.seq = seq;
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
