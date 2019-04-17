package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

/**
 * @author ypp
 * @Title: CourseExemptionDO
 * @Description:
 * @date 2018/11/2014:40
 */
public class CourseExemptionDO implements Serializable {

    //成绩
    private String grade;
    //准考证号
    private String studentid;
    //毕业院校
    private String gradSchool;
    //毕业证书号
    private String gradCertificate;
    //原学专业
    private String gradSpecialityName;
    //原学历
    private String education;
    //报考专业
    private String specialityName;
    //审核状态
    private String auditStatus;
    //状态
    private String status;
    //操作员
    private String Operator;
    //操作日期
    private String updateDate;


    public String getGradCertificate() {
        return gradCertificate;
    }

    public void setGradCertificate(String gradCertificate) {
        this.gradCertificate = gradCertificate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getGradSpecialityName() {
        return gradSpecialityName;
    }

    public void setGradSpecialityName(String gradSpecialityName) {
        this.gradSpecialityName = gradSpecialityName;
    }

    public String getGradSchool() {
        return gradSchool;
    }

    public void setGradSchool(String gradSchool) {
        this.gradSchool = gradSchool;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}