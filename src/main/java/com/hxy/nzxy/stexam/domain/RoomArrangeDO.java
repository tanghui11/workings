package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考场编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class RoomArrangeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考区编号
	private Long regionid;
	//考试时间编号
	private Long examTimeid;
	//考场编号
	private Long examRoomid;
	//开考课程编号
	private Long examCourseid;
	//课程编号
	private String courseid;
	//考生类别
	private String type;
	//课程类别
	private String examType;
	//座次开始号
	private Integer seatStart;
	//座次结束号
	private Integer seatEnd;
	//保密号
	private String secretCode;
	//首考号
	private String firstStudentid;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	private String inRoomArrangeOutid;
	private String inRoomArrangeInid;
	private String courseName;
	private String siteName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getInRoomArrangeOutid() {
		return inRoomArrangeOutid;
	}

	public void setInRoomArrangeOutid(String inRoomArrangeOutid) {
		this.inRoomArrangeOutid = inRoomArrangeOutid;
	}

	public String getInRoomArrangeInid() {
		return inRoomArrangeInid;
	}

	public void setInRoomArrangeInid(String inRoomArrangeInid) {
		this.inRoomArrangeInid = inRoomArrangeInid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
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
	 * 设置：考区编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：考区编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：考试时间编号
	 */
	public void setExamTimeid(Long examTimeid) {
		this.examTimeid = examTimeid;
	}
	/**
	 * 获取：考试时间编号
	 */
	public Long getExamTimeid() {
		return examTimeid;
	}
	/**
	 * 设置：考场编号
	 */
	public void setExamRoomid(Long examRoomid) {
		this.examRoomid = examRoomid;
	}
	/**
	 * 获取：考场编号
	 */
	public Long getExamRoomid() {
		return examRoomid;
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
	 * 设置：考生类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：考生类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：课程类别
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	/**
	 * 获取：课程类别
	 */
	public String getExamType() {
		return examType;
	}
	/**
	 * 设置：座次开始号
	 */
	public void setSeatStart(Integer seatStart) {
		this.seatStart = seatStart;
	}
	/**
	 * 获取：座次开始号
	 */
	public Integer getSeatStart() {
		return seatStart;
	}
	/**
	 * 设置：座次结束号
	 */
	public void setSeatEnd(Integer seatEnd) {
		this.seatEnd = seatEnd;
	}
	/**
	 * 获取：座次结束号
	 */
	public Integer getSeatEnd() {
		return seatEnd;
	}
	/**
	 * 设置：保密号
	 */
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	/**
	 * 获取：保密号
	 */
	public String getSecretCode() {
		return secretCode;
	}
	/**
	 * 设置：首考号
	 */
	public void setFirstStudentid(String firstStudentid) {
		this.firstStudentid = firstStudentid;
	}
	/**
	 * 获取：首考号
	 */
	public String getFirstStudentid() {
		return firstStudentid;
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
}
