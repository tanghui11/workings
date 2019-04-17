package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考区设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class RegionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//父编号
	private Long parentid;
	//代码
	private String code;
	//类别
	private String type;
	//名称
	private String name;
	//父名称
	private String parentName;
	//拼音
	private String pinyin;
	//联系人
	private String linkman;
	//联系地址
	private String address;
	//邮编
	private String postCode;
	//固定电话
	private String phone;
	//移动电话
	private String mphone;
	//传真
	private String fax;
	//是否能照相
	private String photoFlag;
	//考区转移
	private String examTransfer;
	//考场规格
	private Integer roomSize;
	//考试提示
	private String examMsg;
	//考场编排序号
	private String seq;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;
	//邮箱
	private String email;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	 * 设置：父编号
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：父编号
	 */
	public Long getParentid() {
		return parentid;
	}
	/**
	 * 设置：代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代码
	 */
	public String getCode() {
		return code;
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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：拼音
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**
	 * 获取：拼音
	 */
	public String getPinyin() {
		return pinyin;
	}
	/**
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮编
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：固定电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：固定电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	/**
	 * 获取：移动电话
	 */
	public String getMphone() {
		return mphone;
	}
	/**
	 * 设置：传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 获取：传真
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * 设置：是否能照相
	 */
	public void setPhotoFlag(String photoFlag) {
		this.photoFlag = photoFlag;
	}
	/**
	 * 获取：是否能照相
	 */
	public String getPhotoFlag() {
		return photoFlag;
	}
	/**
	 * 设置：考区转移
	 */
	public void setExamTransfer(String examTransfer) {
		this.examTransfer = examTransfer;
	}
	/**
	 * 获取：考区转移
	 */
	public String getExamTransfer() {
		return examTransfer;
	}
	/**
	 * 设置：考场规格
	 */
	public void setRoomSize(Integer roomSize) {
		this.roomSize = roomSize;
	}
	/**
	 * 获取：考场规格
	 */
	public Integer getRoomSize() {
		return roomSize;
	}
	/**
	 * 设置：考试提示
	 */
	public void setExamMsg(String examMsg) {
		this.examMsg = examMsg;
	}
	/**
	 * 获取：考试提示
	 */
	public String getExamMsg() {
		return examMsg;
	}
	/**
	 * 设置：考场编排序号
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 获取：考场编排序号
	 */
	public String getSeq() {
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
