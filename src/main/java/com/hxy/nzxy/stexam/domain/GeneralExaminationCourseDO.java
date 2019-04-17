package com.hxy.nzxy.stexam.domain;

/**
 * @author ypp
 * @Title: GeneralExaminationCourseDO
 * @Description:
 * @date 2018/12/222:27
 */
public class GeneralExaminationCourseDO {

    //准考证号
    private String studentid;
    //成绩
    private String grade;
    //序号
    private String id;

    //课程代码
    private String courseid;

    //课程名称
    private String courseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
