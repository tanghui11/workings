package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;

public class StudentBZCDO implements Serializable {

    private String studentid;

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getSpecialityid() {
        return specialityid;
    }

    public void setSpecialityid(String specialityid) {
        this.specialityid = specialityid;
    }

    public Date getRegBeginDate() {
        return regBeginDate;
    }

    public void setRegBeginDate(Date regBeginDate) {
        this.regBeginDate = regBeginDate;
    }

    public Date getRegEndDate() {
        return regEndDate;
    }

    public void setRegEndDate(Date regEndDate) {
        this.regEndDate = regEndDate;
    }



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private String specialityid;
    private Date regBeginDate;
    private Date regEndDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private Date createDate;

    public Long getStudentSpecialityid() {
        return studentSpecialityid;
    }

    public void setStudentSpecialityid(Long studentSpecialityid) {
        this.studentSpecialityid = studentSpecialityid;
    }

    private Long studentSpecialityid;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    //0无效,1有效
    private Integer enabledFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}

