package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentCourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	//考试考区编号
	private Long regionid;
	//考试区县编号
	private Long childRegionid;
	//开考课程编号
	private Long examCourseid;
	//类别
	private String type;
	//状态
	private String status;
	//编排状态
	private String arrangeStatus;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//专业编码
	private String specialityRecordid;
	//任务编码
	private String taskid;
	//课程编号
	private String courseid;
	//学生名字
	private String name;
	//原因
	private String reason;
	//课程编号
	private String courseName;
	private  String segment;
	private String examDate;
	private String regionName;
	private String childRegionName;
	private String examTaskid;
	private String specialityid;
	private String specialityName;
	private String oldStudentid;
	private String pic;
	private String gender;
	private String certificateNo;

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

	public String getOldStudentid() {
		return oldStudentid;
	}

	public void setOldStudentid(String oldStudentid) {
		this.oldStudentid = oldStudentid;
	}

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getExamTaskid() {
		return examTaskid;
	}

	public void setExamTaskid(String examTaskid) {
		this.examTaskid = examTaskid;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getChildRegionName() {
		return childRegionName;
	}

	public void setChildRegionName(String childRegionName) {
		this.childRegionName = childRegionName;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getSpecialityRecordid() {
		return specialityRecordid;
	}

	public void setSpecialityRecordid(String specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
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
	 * 设置：考试考区编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：考试考区编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：考试区县编号
	 */
	public void setChildRegionid(Long childRegionid) {
		this.childRegionid = childRegionid;
	}
	/**
	 * 获取：考试区县编号
	 */
	public Long getChildRegionid() {
		return childRegionid;
	}
	/**
	 * 设置：开考课程编号
	 */
	public void setExamCourseid(Long examCourseid) {
		this.examCourseid = examCourseid;
	}
	/**
	 * 获取：开考课程编号
	 */
	public Long getExamCourseid() {
		return examCourseid;
	}
	/**
	 * 设置：类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类别
	 */
	public String getType() {
		return type;
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
	 * 设置：编排状态
	 */
	public void setArrangeStatus(String arrangeStatus) {
		this.arrangeStatus = arrangeStatus;
	}
	/**
	 * 获取：编排状态
	 */
	public String getArrangeStatus() {
		return arrangeStatus;
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
