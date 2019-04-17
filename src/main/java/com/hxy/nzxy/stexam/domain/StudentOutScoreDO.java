package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

/**
 * @author ypp
 * @Title: StudentOutScoreDO
 * @Description:
 * @date 2018/11/2811:14
 */
public class StudentOutScoreDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //课程代码
    private String courseid;
    //课程名称
    private String courseName;
    //类别
    private String type;
    //成绩
    private String grade;
    //合格时间
    private String passDate;
    //准考证号
    private String studentid;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
