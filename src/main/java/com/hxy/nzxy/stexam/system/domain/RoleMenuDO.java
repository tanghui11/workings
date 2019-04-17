package com.hxy.nzxy.stexam.system.domain;

public class RoleMenuDO {
    private Long id;
    private String roleid;
    private String menuid;
    private String operator;
    private String updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
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
        return "RoleMenuDO{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", menuid=" + menuid +
                ", operator='" + operator + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
