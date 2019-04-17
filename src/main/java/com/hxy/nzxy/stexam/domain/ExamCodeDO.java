package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 准考证流水号
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class ExamCodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//固定编号
	private String fixed;
	//流水号
	private Integer seq;
	//0无效,1有效
	private Integer enabledFlag;

	/**
	 * 设置：固定编号
	 */
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
	/**
	 * 获取：固定编号
	 */
	public String getFixed() {
		return fixed;
	}
	/**
	 * 设置：流水号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：流水号
	 */
	public Integer getSeq() {
		return seq;
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
