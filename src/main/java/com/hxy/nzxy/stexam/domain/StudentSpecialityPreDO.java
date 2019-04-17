package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentSpecialityPreDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考生编号
	private Long studentid;
	//地州市考办编号
	private Long regionid;
	//县区考办编号
	private Long childRegionid;
	//地州市考办/助学组织编号
	private Long schoolid;
	//县区考办/教学点编号
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
	 * 设置：考生编号
	 */
	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：考生编号
	 */
	public Long getStudentid() {
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
	 * 设置：地州市考办/助学组织编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：地州市考办/助学组织编号
	 */
	public Long getSchoolid() {
		return schoolid;
	}
	/**
	 * 设置：县区考办/教学点编号
	 */
	public void setTeachSiteid(Long teachSiteid) {
		this.teachSiteid = teachSiteid;
	}
	/**
	 * 获取：县区考办/教学点编号
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
