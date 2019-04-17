package com.hxy.nzxy.stexam.center.region.domain;

/**
 * @author ypp
 * @Title: StudentRegionQueryDO
 * @Description:
 * @date 2018/12/1212:08
 */
public class StudentRegionQueryDO {
    //考试任务
    private String examTaskid;
    //考区id
    private String regionid;
    //考区
    private String parentid;
    //考区县id
    private String childRegionid;
    //考区名
    private String parentName;
    //考区县名
    private String childName;
    //准考证号
    private String studentid;
    //姓名
    private String name;
    //课程代码
    private String courseid;
    //课程名称
    private String courseName;
    //报考类别
    private String type;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getExamTaskid() {
        return examTaskid;
    }

    public void setExamTaskid(String examTaskid) {
        this.examTaskid = examTaskid;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getChildRegionid() {
        return childRegionid;
    }

    public void setChildRegionid(String childRegionid) {
        this.childRegionid = childRegionid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
