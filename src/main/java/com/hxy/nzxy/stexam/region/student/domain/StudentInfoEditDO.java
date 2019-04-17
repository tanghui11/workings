package com.hxy.nzxy.stexam.region.student.domain;

/**
 * @author ypp
 * @Title: StudentInfoEditDO
 * @Description:
 * @date 2018/12/1210:53
 */

public class StudentInfoEditDO {
    //准考证号
    private String studentid;
    //姓名
    private String name;
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
    //电子邮件
    private String email;
    //集体代码
    private String groupid;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;
    private String regionid;

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }
}
