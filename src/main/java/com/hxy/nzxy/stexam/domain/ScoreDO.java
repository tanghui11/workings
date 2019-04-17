package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:27
 */
public class ScoreDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	private String auditStatus;
	//专业开设编号
	private Long specialityRecordid;
	//课程编号
	private String courseid;
	//课程类别
	private String type;
	//对象编号
	private Long objid;
	//客观题
	private Float kgGrade;
	//主观题
	private Float zgGrade;
	//原始
	private Float oldGrade;
	//统考
	private Float examGrade;
	//平时
	private Float schoolGrade;
	//
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
	private Long sourceCourseid;
	private String sourceCourseName;
	//年度码
	private String etDm;
	//旧准考证
	private String oldStudentid;
	//考试日期
	private Date examDate;
	//旧课程代码
	private String oldCourseid;
	//平时编号
	private Long schoolScoreid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	//识别码
	private String keyValue;
	private float ratio;
	private String courseName;
	private String gender;
	private String certificateNo;
	private String pic;
	private String schoolid;
	private String name;
	private String score;
	private String classify;
	private String grad_certificate;
	private String homeType;
	private String politics;
	private String nation;
	private String certificate_no;
	private String courseScore;
	private String gradAuditDate;
	private String specialityName;
	private String specialityid;
	private String scoreType;
	private String mphone;

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getSourceCourseName() {
		return sourceCourseName;
	}

	public void setSourceCourseName(String sourceCourseName) {
		this.sourceCourseName = sourceCourseName;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
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
	public void setObjid(Long objid) {
		this.objid = objid;
	}
	/**
	 * 获取：对象编号
	 */
	public Long getObjid() {
		return objid;
	}
	/**
	 * 设置：客观题
	 */
	public void setKgGrade(Float kgGrade) {
		this.kgGrade = kgGrade;
	}
	/**
	 * 获取：客观题
	 */
	public Float getKgGrade() {
		return kgGrade;
	}
	/**
	 * 设置：主观题
	 */
	public void setZgGrade(Float zgGrade) {
		this.zgGrade = zgGrade;
	}
	/**
	 * 获取：主观题
	 */
	public Float getZgGrade() {
		return zgGrade;
	}
	/**
	 * 设置：原始
	 */
	public void setOldGrade(Float oldGrade) {
		this.oldGrade = oldGrade;
	}
	/**
	 * 获取：原始
	 */
	public Float getOldGrade() {
		return oldGrade;
	}
	/**
	 * 设置：统考
	 */
	public void setExamGrade(Float examGrade) {
		this.examGrade = examGrade;
	}
	/**
	 * 获取：统考
	 */
	public Float getExamGrade() {
		return examGrade;
	}
	/**
	 * 设置：平时
	 */
	public void setSchoolGrade(Float schoolGrade) {
		this.schoolGrade = schoolGrade;
	}
	/**
	 * 获取：平时
	 */
	public Float getSchoolGrade() {
		return schoolGrade;
	}
	/**
	 * 设置：
	 */
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	/**
	 * 获取：
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
	public void setSourceCourseid(Long sourceCourseid) {
		this.sourceCourseid = sourceCourseid;
	}
	/**
	 * 获取：源课程
	 */
	public Long getSourceCourseid() {
		return sourceCourseid;
	}
	/**
	 * 设置：年度码
	 */
	public void setEtDm(String etDm) {
		this.etDm = etDm;
	}
	/**
	 * 获取：年度码
	 */
	public String getEtDm() {
		return etDm;
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
	 * 设置：考试日期
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	/**
	 * 获取：考试日期
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * 设置：旧课程代码
	 */
	public void setOldCourseid(String oldCourseid) {
		this.oldCourseid = oldCourseid;
	}
	/**
	 * 获取：旧课程代码
	 */
	public String getOldCourseid() {
		return oldCourseid;
	}
	/**
	 * 设置：平时编号
	 */
	public void setSchoolScoreid(Long schoolScoreid) {
		this.schoolScoreid = schoolScoreid;
	}
	/**
	 * 获取：平时编号
	 */
	public Long getSchoolScoreid() {
		return schoolScoreid;
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
	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}


	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}



	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}


	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getGrad_certificate() {
		return grad_certificate;
	}

	public void setGrad_certificate(String grad_certificate) {
		this.grad_certificate = grad_certificate;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCertificate_no() {
		return certificate_no;
	}

	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}



	public String getCourseScore() {
		return courseScore;
	}

	public void setCourseScore(String courseScore) {
		this.courseScore = courseScore;
	}




	public String getGradAuditDate() {
		return gradAuditDate;
	}

	public void setGradAuditDate(String gradAuditDate) {
		this.gradAuditDate = gradAuditDate;
	}


	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}



	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}



	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}



	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}


}
