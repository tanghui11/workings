package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 成绩变更日志表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class ScoreChangeLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//变更类别
	private String changeType;
	//用户类别
	private String userType;
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	//专业开设编号
	private Long specialityRecordid;
	//课程编号
	private String courseid;
	//课程类别
	private String type;
	//对象编号
	private String objid;
	//客观题成绩
	private Float kgGrade;
	//主观题成绩
	private Float zgGrade;
	//原始成绩
	private Float oldGrade;
	//成绩
	private Float grade;
	//缺考
	private String examFlag;
	//违纪
	private String status;
	//使用状态
	private String useStatus;
	//顶替标志
	private String flag;
	//源课程
	private String sourceCourseid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	/**
	 * 设置：变更类别
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	/**
	 * 获取：变更类别
	 */
	public String getChangeType() {
		return changeType;
	}
	/**
	 * 设置：用户类别
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类别
	 */
	public String getUserType() {
		return userType;
	}
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
	 * 设置：准考证编号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：准考证编号
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * 设置：专业开设编号
	 */
	public void setSpecialityRecordid(Long specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
	/**
	 * 获取：专业开设编号
	 */
	public Long getSpecialityRecordid() {
		return specialityRecordid;
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
	 * 设置：课程类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：课程类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：对象编号
	 */
	public void setObjid(String objid) {
		this.objid = objid;
	}
	/**
	 * 获取：对象编号
	 */
	public String getObjid() {
		return objid;
	}
	/**
	 * 设置：客观题成绩
	 */
	public void setKgGrade(Float kgGrade) {
		this.kgGrade = kgGrade;
	}
	/**
	 * 获取：客观题成绩
	 */
	public Float getKgGrade() {
		return kgGrade;
	}
	/**
	 * 设置：主观题成绩
	 */
	public void setZgGrade(Float zgGrade) {
		this.zgGrade = zgGrade;
	}
	/**
	 * 获取：主观题成绩
	 */
	public Float getZgGrade() {
		return zgGrade;
	}
	/**
	 * 设置：原始成绩
	 */
	public void setOldGrade(Float oldGrade) {
		this.oldGrade = oldGrade;
	}
	/**
	 * 获取：原始成绩
	 */
	public Float getOldGrade() {
		return oldGrade;
	}
	/**
	 * 设置：成绩
	 */
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	/**
	 * 获取：成绩
	 */
	public Float getGrade() {
		return grade;
	}
	/**
	 * 设置：缺考
	 */
	public void setExamFlag(String examFlag) {
		this.examFlag = examFlag;
	}
	/**
	 * 获取：缺考
	 */
	public String getExamFlag() {
		return examFlag;
	}
	/**
	 * 设置：违纪
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：违纪
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：使用状态
	 */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	/**
	 * 获取：使用状态
	 */
	public String getUseStatus() {
		return useStatus;
	}
	/**
	 * 设置：顶替标志
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：顶替标志
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：源课程
	 */
	public void setSourceCourseid(String sourceCourseid) {
		this.sourceCourseid = sourceCourseid;
	}
	/**
	 * 获取：源课程
	 */
	public String getSourceCourseid() {
		return sourceCourseid;
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
