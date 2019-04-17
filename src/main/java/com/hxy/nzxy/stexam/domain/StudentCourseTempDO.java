package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生报考临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentCourseTempDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//市县代码
	private String sxDm;
	//准考证号
	private String ksZkz;
	//姓名
	private String ksXm;
	//专业代码
	private String zydm;
	//专业名称
	private String zymc;
	//课程代码
	private String kcdm;
	//课程名称
	private String kcmc;
	//考试时间
	private String kssj;
	//考试时间序号
	private String kssjxh;
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
	 * 设置：市县代码
	 */
	public void setSxDm(String sxDm) {
		this.sxDm = sxDm;
	}
	/**
	 * 获取：市县代码
	 */
	public String getSxDm() {
		return sxDm;
	}
	/**
	 * 设置：准考证号
	 */
	public void setKsZkz(String ksZkz) {
		this.ksZkz = ksZkz;
	}
	/**
	 * 获取：准考证号
	 */
	public String getKsZkz() {
		return ksZkz;
	}
	/**
	 * 设置：姓名
	 */
	public void setKsXm(String ksXm) {
		this.ksXm = ksXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getKsXm() {
		return ksXm;
	}
	/**
	 * 设置：专业代码
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * 获取：专业代码
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * 设置：专业名称
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * 获取：专业名称
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * 设置：课程代码
	 */
	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
	}
	/**
	 * 获取：课程代码
	 */
	public String getKcdm() {
		return kcdm;
	}
	/**
	 * 设置：课程名称
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	/**
	 * 获取：课程名称
	 */
	public String getKcmc() {
		return kcmc;
	}
	/**
	 * 设置：考试时间
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * 获取：考试时间
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * 设置：考试时间序号
	 */
	public void setKssjxh(String kssjxh) {
		this.kssjxh = kssjxh;
	}
	/**
	 * 获取：考试时间序号
	 */
	public String getKssjxh() {
		return kssjxh;
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

	public Long getExamCourseid() {
		return examCourseid;
	}

	public void setExamCourseid(Long examCourseid) {
		this.examCourseid = examCourseid;
	}

	private Long examCourseid;
}
