package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

public class StudentCourseMovieDO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    //准考证号
    private Long studentid;

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setStudentName(String studentName) {
        this.name = studentName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRegionid() {
        return regionid;
    }

    public void setRegionid(Long regionid) {
        this.regionid = regionid;
    }

    public Long getChildRegionid() {
        return childRegionid;
    }

    public void setChildRegionid(Long childRegionid) {
        this.childRegionid = childRegionid;
    }

    //姓名
    private String name;
    //课程代码
    private String courseid;
    //课程名称
    private String courseName;
    //报考类别
    private  String type;
    //报考地州市
    private Long regionid;
    //报考县区
    private Long childRegionid;
}
