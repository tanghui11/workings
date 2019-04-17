package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生报考专业信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentSpecialityHisDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	//地州市考办编号
	private Long regionid;
	//县区考办编号
	private Long childRegionid;
	//助学组织编号
	private Long schoolid;
	//学院编号
	private Long collegeid;
	//教学点编号
	private Long teachSiteid;
	//毕业院校
	private String gradSchool;
	//毕业证书号
	private String gradCertificate;
	//原学专业编号
	private String gradSpecialityid;
	//原学历
	private String education;
	//专业招生备案编号
	private Long schoolSpecialityRegid;
	//专业开设编号
	private Long specialityRecordid;
	//状态
	private String status;
	//毕业申请状态
	private String graduate;
	//打印毕业证
	private String printCertificate;
	//申请人
	private Long applyOperator;
	//申请日期
	private Date applyDate;
	//审核状态
	private String auditStatus;
	//审核人
	private Long auditOperator;
	//审核日期
	private Date auditDate;
	//毕业审核状态
	private String gradAuditStatus;
	//毕业审核人
	private Long gradAuditOperator;
	//毕业审核日期
	private Date gradAuditDate;
	//旧准考证
	private String oldStudentid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	private String createDate;
	private Integer enabledFlag;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
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
	 * 设置：地州市考办编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：地州市考办编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：县区考办编号
	 */
	public void setChildRegionid(Long childRegionid) {
		this.childRegionid = childRegionid;
	}
	/**
	 * 获取：县区考办编号
	 */
	public Long getChildRegionid() {
		return childRegionid;
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
	 * 设置：学院编号
	 */
	public void setCollegeid(Long collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * 获取：学院编号
	 */
	public Long getCollegeid() {
		return collegeid;
	}
	/**
	 * 设置：教学点编号
	 */
	public void setTeachSiteid(Long teachSiteid) {
		this.teachSiteid = teachSiteid;
	}
	/**
	 * 获取：教学点编号
	 */
	public Long getTeachSiteid() {
		return teachSiteid;
	}
	/**
	 * 设置：毕业院校
	 */
	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}
	/**
	 * 获取：毕业院校
	 */
	public String getGradSchool() {
		return gradSchool;
	}
	/**
	 * 设置：毕业证书号
	 */
	public void setGradCertificate(String gradCertificate) {
		this.gradCertificate = gradCertificate;
	}
	/**
	 * 获取：毕业证书号
	 */
	public String getGradCertificate() {
		return gradCertificate;
	}
	/**
	 * 设置：原学专业编号
	 */
	public void setGradSpecialityid(String gradSpecialityid) {
		this.gradSpecialityid = gradSpecialityid;
	}
	/**
	 * 获取：原学专业编号
	 */
	public String getGradSpecialityid() {
		return gradSpecialityid;
	}
	/**
	 * 设置：原学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * 获取：原学历
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * 设置：专业招生备案编号
	 */
	public void setSchoolSpecialityRegid(Long schoolSpecialityRegid) {
		this.schoolSpecialityRegid = schoolSpecialityRegid;
	}
	/**
	 * 获取：专业招生备案编号
	 */
	public Long getSchoolSpecialityRegid() {
		return schoolSpecialityRegid;
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
	 * 设置：毕业申请状态
	 */
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	/**
	 * 获取：毕业申请状态
	 */
	public String getGraduate() {
		return graduate;
	}
	/**
	 * 设置：打印毕业证
	 */
	public void setPrintCertificate(String printCertificate) {
		this.printCertificate = printCertificate;
	}
	/**
	 * 获取：打印毕业证
	 */
	public String getPrintCertificate() {
		return printCertificate;
	}
	/**
	 * 设置：申请人
	 */
	public void setApplyOperator(Long applyOperator) {
		this.applyOperator = applyOperator;
	}
	/**
	 * 获取：申请人
	 */
	public Long getApplyOperator() {
		return applyOperator;
	}
	/**
	 * 设置：申请日期
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	/**
	 * 获取：申请日期
	 */
	public Date getApplyDate() {
		return applyDate;
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
	 * 设置：审核人
	 */
	public void setAuditOperator(Long auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核人
	 */
	public Long getAuditOperator() {
		return auditOperator;
	}
	/**
	 * 设置：审核日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审核日期
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	/**
	 * 设置：毕业审核状态
	 */
	public void setGradAuditStatus(String gradAuditStatus) {
		this.gradAuditStatus = gradAuditStatus;
	}
	/**
	 * 获取：毕业审核状态
	 */
	public String getGradAuditStatus() {
		return gradAuditStatus;
	}
	/**
	 * 设置：毕业审核人
	 */
	public void setGradAuditOperator(Long gradAuditOperator) {
		this.gradAuditOperator = gradAuditOperator;
	}
	/**
	 * 获取：毕业审核人
	 */
	public Long getGradAuditOperator() {
		return gradAuditOperator;
	}
	/**
	 * 设置：毕业审核日期
	 */
	public void setGradAuditDate(Date gradAuditDate) {
		this.gradAuditDate = gradAuditDate;
	}
	/**
	 * 获取：毕业审核日期
	 */
	public Date getGradAuditDate() {
		return gradAuditDate;
	}
	/**
	 * 设置：旧准考证
	 */
	public void setOldStudentid(String oldStudentid) {
		this.oldStudentid = oldStudentid;
	}
	/**
	 * 获取：旧准考证
	 */
	public String getOldStudentid() {
		return oldStudentid;
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
	 * 设置：数据标记
	 */
	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * 获取：数据标记
	 */
	public Integer getDbFlag() {
		return dbFlag;
	}
}
