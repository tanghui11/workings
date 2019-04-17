package com.hxy.nzxy.stexam.oa.domain;

import java.io.Serializable;
import java.util.Date;

public class NotifyOrgDO implements Serializable {

    //编号
    private String id;
    //通知编号
    private String notifyid;
    //机构编号
    private Long orgid;
    //操作员
    private Long operator;
    //操作日期
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(String notifyid) {
        this.notifyid = notifyid;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
