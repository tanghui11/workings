package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业招生备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class SchoolSpecialityRegDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//助学组织编号
	private Long schoolid;
	//专业开设编号
	private Long specialityRecordid;
	//教学点代码
	private Long teachid;
	//学院代码
	private Long collegeid;
	//专业层次
	private String classify;
	//助学方式
	private String type;
	//助学手段
	private String method;
	//学制
	private String educateLength;
	//招生年份
	private String regYear;
	//招生季节
	private String regSeason;
	//招生人数
	private Integer num;
	//申请来源
	private Long sourceRegid;
	//主考学校
	private Long gradSchoolid;
	//审核状态
	private String auditStatus;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	private String teachSiteName;
	private String specialityId;
	private String specialityName;;
	private String	schoolName;
	//专业代码
	private String subjectCode;
	//专业名称
	private String subjectName;
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeachSiteName() {
		return teachSiteName;
	}

	public void setTeachSiteName(String teachSiteName) {
		this.teachSiteName = teachSiteName;
	}

	public String getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(String specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	 * 设置：助学组织编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：助学组织编号
	 */
	public Long getSchoolid() {
		return schoolid;
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

	public Long getTeachid() {
		return teachid;
	}

	public void setTeachid(Long teachid) {
		this.teachid = teachid;
	}

	/**
	 * 设置：专业层次
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：专业层次
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * 设置：助学方式
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：助学方式
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：助学手段
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：助学手段
	 */
	public String getMethod() {
		return method;
	}

	public String getEducateLength() {
		return educateLength;
	}

	public void setEducateLength(String educateLength) {
		this.educateLength = educateLength;
	}

	/**
	 * 设置：招生年份
	 */
	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}
	/**
	 * 获取：招生年份
	 */
	public String getRegYear() {
		return regYear;
	}
	/**
	 * 设置：招生季节
	 */
	public void setRegSeason(String regSeason) {
		this.regSeason = regSeason;
	}
	/**
	 * 获取：招生季节
	 */
	public String getRegSeason() {
		return regSeason;
	}
	/**
	 * 设置：招生人数
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：招生人数
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：申请来源
	 */
	public void setSourceRegid(Long sourceRegid) {
		this.sourceRegid = sourceRegid;
	}
	/**
	 * 获取：申请来源
	 */
	public Long getSourceRegid() {
		return sourceRegid;
	}
	/**
	 * 设置：主考学校
	 */
	public void setGradSchoolid(Long gradSchoolid) {
		this.gradSchoolid = gradSchoolid;
	}
	/**
	 * 获取：主考学校
	 */
	public Long getGradSchoolid() {
		return gradSchoolid;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditStatus() {
		return auditStatus;
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

	public Long getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(Long collegeid) {
		this.collegeid = collegeid;
	}
}
