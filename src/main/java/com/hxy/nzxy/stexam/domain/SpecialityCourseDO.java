package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专业课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class SpecialityCourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//专业开设编号
	private Long specialityRecordid;
	//课程编号
	private String courseid;
	//课程名称
	private String courseName;
	//教材编号
	private Long bookid;
	//教材名称
	private String bookName;
	//类别
	private String type;
	//分类
	private String classify;
	//序号
	private Integer seq;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//考生编号
	private String studentid;
	//0无效,1有效
	private Integer enabledFlag;

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getCourseName() { return courseName; }

	public void setCourseName(String courseName) { this.courseName = courseName; }

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
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
	 * 设置：教材编号
	 */
	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：教材编号
	 */
	public Long getBookid() {
		return bookid;
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
	 * 设置：分类
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：分类
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * 设置：序号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：序号
	 */
	public Integer getSeq() {
		return seq;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSourceCourseid() {
		return sourceCourseid;
	}

	public void setSourceCourseid(String sourceCourseid) {
		this.sourceCourseid = sourceCourseid;
	}

	private String grade;//成绩
	private String sourceCourseid;//源课程
}
