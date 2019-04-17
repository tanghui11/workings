package com.hxy.nzxy.stexam.system.domain;

import java.sql.Timestamp;
import java.util.List;

public class RoleDO {

    private String id;
    private String appid;
    private String name;
    private String intro;
    private String status;
    private String operator;
    private String updateDate;
    private List<String> menuIds;
    private String roleSign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", status='" + status + '\'' +
                ", operator='" + operator + '\'' +
                ", updateDate=" + updateDate +
                ", menuIds=" + menuIds +
                '}';
    }
}
