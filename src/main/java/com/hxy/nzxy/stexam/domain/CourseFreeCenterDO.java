package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

public class CourseFreeCenterDO implements Serializable {

    private static final long serialVersionUID = 1L;
    //名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //备注
    private String remark;
    //状态
    private String status;
    //免考类别
    private String type;

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

    //操作员
    private String operator;
    //操作日期
    private String updateDate;
    //0无效,1有效
    private Integer enabledFlag;
}
