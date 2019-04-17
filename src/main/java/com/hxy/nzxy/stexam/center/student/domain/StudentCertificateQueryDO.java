package com.hxy.nzxy.stexam.center.student.domain;

/**
 *功能描述 毕业证查询
 * @author ypp
 * @date 2018/12/18
 * @param
 * @return
 */
public class StudentCertificateQueryDO {
    //姓名
    private String name;
    //证件类型
    private String certificateType;
    //证件号码
    private String certificateNo;
    //毕业证书编号
    private String diplomaNo;
    //准考证号
    private String studentid;
    //专业代码
    private String specialityid;
    //专业名称
    private String specialityName;
    //论文分数
    private String paperScore;
    //毕业时间
    private String graduationDate;
    //毕业院校
    private String gradSchool;
    //专业方向
    private String gradDirection;
    //专业层次
    private String specialityLevels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getDiplomaNo() {
        return diplomaNo;
    }

    public void setDiplomaNo(String diplomaNo) {
        this.diplomaNo = diplomaNo;
    }

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

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(String paperScore) {
        this.paperScore = paperScore;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGradSchool() {
        return gradSchool;
    }

    public void setGradSchool(String gradSchool) {
        this.gradSchool = gradSchool;
    }

    public String getGradDirection() {
        return gradDirection;
    }

    public void setGradDirection(String gradDirection) {
        this.gradDirection = gradDirection;
    }

    public String getSpecialityLevels() {
        return specialityLevels;
    }

    public void setSpecialityLevels(String specialityLevels) {
        this.specialityLevels = specialityLevels;
    }
}
