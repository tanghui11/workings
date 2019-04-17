package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考场
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class ExamRoomDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考区编号
	private Long regionid;
	//考试任务编号
	private Long examTaskid;
	//考点编号
	private Long examSiteSubmitid;
	//考点编号
	private Long examSiteid;
	//考场编号
	private Integer roomNo;
	//座位数
	private Integer seatNum;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	private String num;
	private String code;
	private String name;
	private String roomArrangeid;

	public String getRoomArrangeid() {
		return roomArrangeid;
	}

	public void setRoomArrangeid(String roomArrangeid) {
		this.roomArrangeid = roomArrangeid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
	 * 设置：考试任务编号
	 */
	public void setExamTaskid(Long examTaskid) {
		this.examTaskid = examTaskid;
	}
	/**
	 * 获取：考试任务编号
	 */
	public Long getExamTaskid() {
		return examTaskid;
	}
	/**
	 * 设置：考点编号
	 */
	public void setExamSiteSubmitid(Long examSiteSubmitid) {
		this.examSiteSubmitid = examSiteSubmitid;
	}
	/**
	 * 获取：考点编号
	 */
	public Long getExamSiteSubmitid() {
		return examSiteSubmitid;
	}
	/**
	 * 设置：考场编号
	 */
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	/**
	 * 获取：考场编号
	 */
	public Integer getRoomNo() {
		return roomNo;
	}
	/**
	 * 设置：座位数
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	/**
	 * 获取：座位数
	 */
	public Integer getSeatNum() {
		return seatNum;
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

	public Long getExamSiteid() {
		return examSiteid;
	}

	public void setExamSiteid(Long examSiteid) {
		this.examSiteid = examSiteid;
	}
}
