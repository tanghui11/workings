package com.hxy.nzxy.stexam.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 编号
    private String id;
    // 机构编号
    private String orgid;
    // 机构名称
    private String orgName;
    // 部门编号
    private String deptid;
    // 部门名称
    private String deptName;
    // 职员编号
    private String workerid;
    // 职员名称
    private String workerName;
    // 职员状态
    private String workerStatus;
    // 职员联系电话
    private String workerPhone;
    // 职员手机号码
    private String workerMphone;
    // 职员邮箱
    private String workerEmail;
    // 部门职员ID
    private String deptWorkerid;
    // 职员在职状态
    private String deptWorkerStatus;
    // 名称炸
    private String name;
    // 密码
    private String pwd;
    // 用户头像
    private String img;
    // 找回密码问题
    private String question;
    // 找回密码问题答案
    private String answer;
    // 状态
    private String status;
    // 操作员
    private String operator;
    // 操作日期
    private String updateDate;
    // 角色
    private List<String> roleIds;

    // 组织编号
    private String schoolid;
    // 组织名称
    private String schoolName;
    // 组织编号
    private String collegeid;
    // 组织名称
    private String collegeName;
    // 考区编号
    private String regionid;
    //考区类型`
    private String regType;
    //用户类型 1为中心端2为考区端3为组织机构4为学院
    private String type;

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(String collegeid) {
        this.collegeid = collegeid;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getWorkerid() {
        return workerid;
    }

    public void setWorkerid(String workerid) {
        this.workerid = workerid;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String workerStatus) {
        this.workerStatus = workerStatus;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerMphone() {
        return workerMphone;
    }

    public void setWorkerMphone(String workerMphone) {
        this.workerMphone = workerMphone;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }

    public String getDeptWorkerid() {
        return deptWorkerid;
    }

    public void setDeptWorkerid(String deptWorkerid) {
        this.deptWorkerid = deptWorkerid;
    }

    public String getDeptWorkerStatus() {
        return deptWorkerStatus;
    }

    public void setDeptWorkerStatus(String deptWorkerStatus) {
        this.deptWorkerStatus = deptWorkerStatus;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", workerid='" + workerid + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", img='" + img + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status='" + status + '\'' +
                ", operator=" + operator +
                ", updateDate=" + updateDate +
                '}';
    }
}
