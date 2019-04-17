package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 部门管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-25 15:15:11
 */
public class DeptDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //父编号
    private String parentid;
    //机构编号
    private String orgid;
    //名称
    private String name;
    //拼音
    private String pinyin;
    //联系人
    private String linkman;
    //联系电话
    private String phone;
    //传真
    private String fax;
    //联系地址
    private String address;
    //邮编
    private String postCode;
    //电子邮箱
    private String email;
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
     * 设置：父编号
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取：父编号
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 设置：机构编号
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    /**
     * 获取：机构编号
     */
    public String getOrgid() {
        return orgid;
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
