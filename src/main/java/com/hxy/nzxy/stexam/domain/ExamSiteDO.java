package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考点维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class ExamSiteDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//考区编号
	private Long regionid;
	//考点代码
	private String code;
	//名称
	private String name;
	//拼音
	private String pinyin;
	//考场数量
	private Integer num;
	//联系人
	private String linkman;
	//联系电话
	private String phone;
	//传真
	private String fax;
	//地址
	private String address;
	//邮政编码
	private String postCode;
	//备注
	private String remark;
	//是否助学组织
	private String school;
	//办学许可证编号
	private String schoolCode;
	//助学许可证编号
	private String teachCode;
	//考点主考姓名
	private String leader;
	//是否标准化考场
	private String standard;
	//状态
	private String status;
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
	 * 设置：考点代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：考点代码
	 */
	public String getCode() {
		return code;
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
	 * 设置：考场数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：考场数量
	 */
	public Integer getNum() {
		return num;
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
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
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
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：是否助学组织
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * 获取：是否助学组织
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * 设置：办学许可证编号
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	/**
	 * 获取：办学许可证编号
	 */
	public String getSchoolCode() {
		return schoolCode;
	}
	/**
	 * 设置：助学许可证编号
	 */
	public void setTeachCode(String teachCode) {
		this.teachCode = teachCode;
	}
	/**
	 * 获取：助学许可证编号
	 */
	public String getTeachCode() {
		return teachCode;
	}
	/**
	 * 设置：考点主考姓名
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	/**
	 * 获取：考点主考姓名
	 */
	public String getLeader() {
		return leader;
	}
	/**
	 * 设置：是否标准化考场
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}
	/**
	 * 获取：是否标准化考场
	 */
	public String getStandard() {
		return standard;
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
}
