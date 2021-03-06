package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentHisDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//类别
	private String type;
	//姓名
	private String name;
	//英文名
	private String ename;
	//拼音
	private String pinyin;
	//性别
	private String gender;
	//户籍
	private String homeType;
	//政治面貌
	private String politics;
	//民族
	private String nation;
	//职业
	private String profession;
	//出生日期
	private Date birthday;
	//籍贯
	private String nativePlace;
	//证件类别
	private String certificateType;
	//身份证号
	private String certificateNo;
	//固定电话
	private String phone;
	//移动电话
	private String mphone;
	//通讯地址
	private String address;
	//邮编
	private String postCode;
	//电子邮箱
	private String email;
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
	//集体代码
	private Long groupid;
	//旧准考证
	private String oldStudentid;
	//报考类别
	private String classify;
	//状态
	private String status;
	//审核状态
	private String auditStatus;
	//确认状态
	private String confirmStatus;
	//退学操作员
	private Long backOperator;
	//退学日期
	private Date backDate;
	//确认操作员
	private Long confirmOperator;
	//确认操作日期
	private Date confirmDate;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	private String createDate;
	private String keyValue;
	private String password;
	private String workAddress;
	private String question;
	private String answer;
	//数据标记
	private Integer enabledFalag;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getEnabledFalag() {
		return enabledFalag;
	}

	public void setEnabledFalag(Integer enabledFalag) {
		this.enabledFalag = enabledFalag;
	}

	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：英文名
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：英文名
	 */
	public String getEname() {
		return ename;
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
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：户籍
	 */
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	/**
	 * 获取：户籍
	 */
	public String getHomeType() {
		return homeType;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setPolitics(String politics) {
		this.politics = politics;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getPolitics() {
		return politics;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * 设置：职业
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * 获取：职业
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：籍贯
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	/**
	 * 获取：籍贯
	 */
	public String getNativePlace() {
		return nativePlace;
	}
	/**
	 * 设置：证件类别
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	/**
	 * 获取：证件类别
	 */
	public String getCertificateType() {
		return certificateType;
	}
	/**
	 * 设置：身份证号
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * 获取：身份证号
	 */
	public String getCertificateNo() {
		return certificateNo;
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
	 * 设置：通讯地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：通讯地址
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
	 * 设置：电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEmail() {
		return email;
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
	 * 设置：集体代码
	 */
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	/**
	 * 获取：集体代码
	 */
	public Long getGroupid() {
		return groupid;
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
	 * 设置：报考类别
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * 获取：报考类别
	 */
	public String getClassify() {
		return classify;
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
	 * 设置：确认状态
	 */
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	/**
	 * 获取：确认状态
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}
	/**
	 * 设置：退学操作员
	 */
	public void setBackOperator(Long backOperator) {
		this.backOperator = backOperator;
	}
	/**
	 * 获取：退学操作员
	 */
	public Long getBackOperator() {
		return backOperator;
	}
	/**
	 * 设置：退学日期
	 */
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	/**
	 * 获取：退学日期
	 */
	public Date getBackDate() {
		return backDate;
	}
	/**
	 * 设置：确认操作员
	 */
	public void setConfirmOperator(Long confirmOperator) {
		this.confirmOperator = confirmOperator;
	}
	/**
	 * 获取：确认操作员
	 */
	public Long getConfirmOperator() {
		return confirmOperator;
	}
	/**
	 * 设置：确认操作日期
	 */
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	/**
	 * 获取：确认操作日期
	 */
	public Date getConfirmDate() {
		return confirmDate;
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
