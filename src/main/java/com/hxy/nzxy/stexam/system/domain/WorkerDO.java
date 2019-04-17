package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 职员管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-23 11:47:43
 */
public class WorkerDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //部门编号
    private String deptid;
    //部门编号
    private String deptName;
    //部门编号
    private String deptWorkerid;
    //机构编号
    private String orgid;
    //机构名称
    private String orgName;
    //姓名
    private String name;
    //拼音
    private String pinyin;
    //性别
    private String gender;
    //身份证号
    private String certificateNo;
    //出生日期
    private String birthday;
    //参加工作时间
    private String workDate;
    //第一学历
    private String firstEducation;
    //最高学历
    private String lastEducation;
    //联系电话
    private String phone;
    //移动电话
    private String mphone;
    //通讯地址
    private String address;
    //邮编
    private String postCode;
    //电子邮件
    private String email;
    //微信
    private String weixin;
    // qq
    private String qq;
    //状态
    private String status;
    //离职状态
    private String stopStatus;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

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
     * 获取：部门编号
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 设置：部门编号
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * 获取：部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置：部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 设置：部门职员ID
     */
    public String getDeptWorkerid() {
        return deptWorkerid;
    }

    /**
     * 获取：部门职员ID
     */
    public void setDeptWorkerid(String deptWorkerid) {
        this.deptWorkerid = deptWorkerid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
     * 设置：出生日期
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：出生日期
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置：参加工作时间
     */
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取：参加工作时间
     */
    public String getWorkDate() {
        return workDate;
    }

    /**
     * 设置：第一学历
     */
    public void setFirstEducation(String firstEducation) {
        this.firstEducation = firstEducation;
    }

    /**
     * 获取：第一学历
     */
    public String getFirstEducation() {
        return firstEducation;
    }

    /**
     * 设置：最高学历
     */
    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    /**
     * 获取：最高学历
     */
    public String getLastEducation() {
        return lastEducation;
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
     * 设置：电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：微信
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * 获取：微信
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * 设置： qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取： qq
     */
    public String getQq() {
        return qq;
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
     * 获取：离职状态
     */
    public String getStopStatus() {
        return stopStatus;
    }

    /**
     * 设置：离职状态
     */
    public void setStopStatus(String stopStatus) {
        this.stopStatus = stopStatus;
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
}
