package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 * 考场编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class RoomArrangeKeepSecretDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String examDate;
	private String segment;
	private String examSiteid;
	private String examSiteName;
	private String examRoomid;
	private String courseid;
	private String courseName;
	private String secretCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getExamSiteid() {
		return examSiteid;
	}

	public void setExamSiteid(String examSiteid) {
		this.examSiteid = examSiteid;
	}

	public String getExamSiteName() {
		return examSiteName;
	}

	public void setExamSiteName(String examSiteName) {
		this.examSiteName = examSiteName;
	}

	public String getExamRoomid() {
		return examRoomid;
	}

	public void setExamRoomid(String examRoomid) {
		this.examRoomid = examRoomid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
}
