package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentCertificateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//毕业证书编号
	private String code;
	//准考证号
	private String studentid;
	//姓名
	private String studentName;
	//考生类别
	private String studentType;
	//专业报考编号
	private Long studentSpecialityid;
	//主考院校
	private String gradSchool;
	//专业代码
	private String specialityid;
	//专业名称
	private String specialityName;
	//论文分数
	private Float paperScore;
	//毕业时间
	private Date graduateDate;
	//报考时间
	private Date studentDate;
	//毕业院校
	private String schoolName;
	//专业方向
	private String graduateDirection;
	//专业层次
	private String specialityLevels;
	//审批时间
	private String auditDate;
	//审批人
	private String auditOperator;
	//审批状态
	private String auditStatus;
	//选择年份
	private String time1;
	private String time2;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	//识别码
	private String key;
	//准考证是否已经生成
    private String isCode;

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
	 * 设置：毕业证书编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：毕业证书编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：准考证号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：准考证号
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * 设置：专业报考编号
	 */
	public void setStudentSpecialityid(Long studentSpecialityid) {
		this.studentSpecialityid = studentSpecialityid;
	}
	/**
	 * 获取：专业报考编号
	 */
	public Long getStudentSpecialityid() {
		return studentSpecialityid;
	}
	/**
	 * 设置：专业代码
	 */
	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
	/**
	 * 获取：专业代码
	 */
	public String getSpecialityid() {
		return specialityid;
	}
	/**
	 * 设置：专业名称
	 */
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	/**
	 * 获取：专业名称
	 */
	public String getSpecialityName() {
		return specialityName;
	}
	/**
	 * 设置：论文分数
	 */
	public void setPaperScore(Float paperScore) {
		this.paperScore = paperScore;
	}
	/**
	 * 获取：论文分数
	 */
	public Float getPaperScore() {
		return paperScore;
	}
	/**
	 * 设置：毕业时间
	 */
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	/**
	 * 获取：毕业时间
	 */
	public Date getGraduateDate() {
		return graduateDate;
	}
	/**
	 * 设置：毕业院校
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * 获取：毕业院校
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * 设置：专业方向
	 */
	public void setGraduateDirection(String graduateDirection) {
		this.graduateDirection = graduateDirection;
	}
	/**
	 * 获取：专业方向
	 */
	public String getGraduateDirection() {
		return graduateDirection;
	}
	/**
	 * 设置：专业层次
	 */
	public void setSpecialityLevels(String specialityLevels) {
		this.specialityLevels = specialityLevels;
	}
	/**
	 * 获取：专业层次
	 */
	public String getSpecialityLevels() {
		return specialityLevels;
	}
	/**
	 * 设置：审批时间
	 */
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审批时间
	 */
	public String getAuditDate() {
		return auditDate;
	}
	/**
	 * 设置：审批人
	 */
	public void setAuditOperator(String auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审批人
	 */
	public String getAuditOperator() {
		return auditOperator;
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
	/**
	 * 设置：识别码
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：识别码
	 */
	public String getKey() {
		return key;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGradSchool() {
		return gradSchool;
	}

	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}

    public String getIsCode() {
        return isCode;
    }

    public void setIsCode(String isCode) {
        this.isCode = isCode;
    }

	public Date getStudentDate() {
		return studentDate;
	}

	public void setStudentDate(Date studentDate) {
		this.studentDate = studentDate;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}
}
