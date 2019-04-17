package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ypp
 * @Title: StudentOutSpecialityDO
 * @Description:
 * @date 2018/11/2710:38
 */
public class StudentOutSpecialityDO implements Serializable {


    //准考证号
    private  String studentid;
    //毕业院校
    private String gradSchool;
    //毕业证书号
    private String gradCertificate;
    //原学专业编号
    private String gradSpecialityid;
    //原学历
    private String education;
    //专业开设编号
    private Long specialityRecordid;
    //状态
    private String status;
    //毕业申请状态
    private String graduate;
    //审核状态
    private String auditStatus;
    //审核人
    private Long auditOperator;
    //审核日期
    private Date auditDate;
    //毕业审核状态
    private String gradAuditStatus;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

    //原学专业编号
    private String gradSpecialityName;

    //专业编号
    private String specialityid;

    //专业名称
    private String specialityName;

    //学院名称
    private String collegeName;

    public String getGradSchool() {
        return gradSchool;
    }

    public void setGradSchool(String gradSchool) {
        this.gradSchool = gradSchool;
    }

    public String getGradCertificate() {
        return gradCertificate;
    }

    public void setGradCertificate(String gradCertificate) {
        this.gradCertificate = gradCertificate;
    }

    public String getGradSpecialityid() {
        return gradSpecialityid;
    }

    public void setGradSpecialityid(String gradSpecialityid) {
        this.gradSpecialityid = gradSpecialityid;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getSpecialityRecordid() {
        return specialityRecordid;
    }

    public void setSpecialityRecordid(Long specialityRecordid) {
        this.specialityRecordid = specialityRecordid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditOperator() {
        return auditOperator;
    }

    public void setAuditOperator(Long auditOperator) {
        this.auditOperator = auditOperator;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getGradAuditStatus() {
        return gradAuditStatus;
    }

    public void setGradAuditStatus(String gradAuditStatus) {
        this.gradAuditStatus = gradAuditStatus;
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

    public String getGradSpecialityName() {
        return gradSpecialityName;
    }

    public void setGradSpecialityName(String gradSpecialityName) {
        this.gradSpecialityName = gradSpecialityName;
    }

    public String getSpecialityid() {
        return specialityid;
    }

    public void setSpecialityid(String specialityid) {
        this.specialityid = specialityid;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
