package com.hxy.nzxy.stexam.system.domain;

public class UserRoleDO {
    private String id;
    private String userid;
    private String roleid;
    // 操作员
    private String operator;
    // 操作日期
    private String updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

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

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "id=" + id +
                ", userid=" + userid +
                ", roleid=" + roleid +
                '}';
    }
}
