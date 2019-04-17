package com.hxy.nzxy.stexam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentRegDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//学生报考专业编号
	private Long studentSpecialityid;
	//专业编号
	private String specialityid;
	//报考专业名称
	private  String subjectName;
	//姓名
	private String name;
	//性别
	private String gender;
	//户籍
	private String homeType;
	//身份证号
	private String certificateNo;
	//政治面貌
	private String politics;
	//民族
	private String nation;
	//出生日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	//注册开始日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regBeginDate;
	//注册结束日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regEndDate;
	//状态
	private String status;
	//操作员
	private String operator;
	//操作日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	//0无效,1有效
	private Integer enabledFlag;
	//识别码
	private String keyValue;
	//操作日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private String createDate;
	//准考证号
	private String studentid;

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	 * 设置：报考专业编号
	 */
	public void setStudentSpecialityid(Long studentSpecialityid) {
		this.studentSpecialityid = studentSpecialityid;
	}
	/**
	 * 获取：报考专业编号
	 */
	public Long getStudentSpecialityid() {
		return studentSpecialityid;
	}
	/**
	 * 设置：注册开始日期
	 */
	public void setRegBeginDate(Date regBeginDate) {
		this.regBeginDate = regBeginDate;
	}
	/**
	 * 获取：注册开始日期
	 */
	public Date getRegBeginDate() {
		return regBeginDate;
	}
	/**
	 * 设置：注册结束日期
	 */
	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}
	/**
	 * 获取：注册结束日期
	 */
	public Date getRegEndDate() {
		return regEndDate;
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

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}
}
