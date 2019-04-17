package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 成绩导入临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class ScoreImportDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//课程代码
	private String kemuid;
	//准考证号
	private String code;
	//姓名
	private String name;
	//总分
	private Float totalscore;
	//主观总分
	private Float zgscore;
	//客观总分
	private Float omrscore;
	//缺考
	private String qk;
	//违纪
	private String wj;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	private String examDate;

	/**
	 * 设置：课程代码
	 */
	public void setKemuid(String kemuid) {
		this.kemuid = kemuid;
	}
	/**
	 * 获取：课程代码
	 */
	public String getKemuid() {
		return kemuid;
	}
	/**
	 * 设置：准考证号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：准考证号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：总分
	 */
	public void setTotalscore(Float totalscore) {
		this.totalscore = totalscore;
	}
	/**
	 * 获取：总分
	 */
	public Float getTotalscore() {
		return totalscore;
	}
	/**
	 * 设置：主观总分
	 */
	public void setZgscore(Float zgscore) {
		this.zgscore = zgscore;
	}
	/**
	 * 获取：主观总分
	 */
	public Float getZgscore() {
		return zgscore;
	}
	/**
	 * 设置：客观总分
	 */
	public void setOmrscore(Float omrscore) {
		this.omrscore = omrscore;
	}
	/**
	 * 获取：客观总分
	 */
	public Float getOmrscore() {
		return omrscore;
	}
	/**
	 * 设置：缺考
	 */
	public void setQk(String qk) {
		this.qk = qk;
	}
	/**
	 * 获取：缺考
	 */
	public String getQk() {
		return qk;
	}
	/**
	 * 设置：违纪
	 */
	public void setWj(String wj) {
		this.wj = wj;
	}
	/**
	 * 获取：违纪
	 */
	public String getWj() {
		return wj;
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
