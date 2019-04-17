package com.hxy.nzxy.stexam.region.exam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 开考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
public class ExamCourseRegionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//课程编号
	private String courseid;
	//教材编号
	private Long bookid;
	//开考时间编号
	private Long examTimeid;
	//任务编号
	private Long examTaskid;
	//满分
	private Float fullScore;
	//及格分数
	private Float passScore;
	//客观题总分
	private Float objectivesScore;
	//主观题总分
	private Float subjectiveScore;
	//超员人数
	private Integer exceedNumber;
	//类别
	private String type;
	//命题类别
	private String classify;
	//题卡类别
	private String cardType;
	//操作员
	private String remark;
	//操作员
	private Integer seq;
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
	 * 设置：开考时间编号
	 */
	public void setExamTimeid(Long examTimeid) {
		this.examTimeid = examTimeid;
	}
	/**
	 * 获取：开考时间编号
	 */
	public Long getExamTimeid() {
		return examTimeid;
	}
	/**
	 * 设置：任务编号
	 */
	public void setExamTaskid(Long examTaskid) {
		this.examTaskid = examTaskid;
	}
	/**
	 * 获取：任务编号
	 */
	public Long getExamTaskid() {
		return examTaskid;
	}
	/**
	 * 设置：满分
	 */
	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}
	/**
	 * 获取：满分
	 */
	public Float getFullScore() {
		return fullScore;
	}
	/**
	 * 设置：及格分数
	 */
	public void setPassScore(Float passScore) {
		this.passScore = passScore;
	}
	/**
	 * 获取：及格分数
	 */
	public Float getPassScore() {
		return passScore;
	}
	/**
	 * 设置：客观题总分
	 */
	public void setObjectivesScore(Float objectivesScore) {
		this.objectivesScore = objectivesScore;
	}
	/**
	 * 获取：客观题总分
	 */
	public Float getObjectivesScore() {
		return objectivesScore;
	}
	/**
	 * 设置：主观题总分
	 */
	public void setSubjectiveScore(Float subjectiveScore) {
		this.subjectiveScore = subjectiveScore;
	}
	/**
	 * 获取：主观题总分
	 */
	public Float getSubjectiveScore() {
		return subjectiveScore;
	}
	/**
	 * 设置：超员人数
	 */
	public void setExceedNumber(Integer exceedNumber) {
		this.exceedNumber = exceedNumber;
	}
	/**
	 * 获取：超员人数
	 */
	public Integer getExceedNumber() {
		return exceedNumber;
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
	 * 设置：命题类别
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：命题类别
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * 设置：题卡类别
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * 获取：题卡类别
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * 设置：操作员
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：操作员
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：操作员
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：操作员
	 */
	public Integer getSeq() {
		return seq;
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
