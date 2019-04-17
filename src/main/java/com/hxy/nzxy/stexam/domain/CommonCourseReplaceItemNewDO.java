package com.hxy.nzxy.stexam.domain;

public class CommonCourseReplaceItemNewDO {


    //课程编号
    private String courseid;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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

    //课程名称
    private String courseName;

    //备注
    private String remark;
    //学分
    private float score;
    //类别
    private String type;
    //分类
    private  String classify;
    // 属性
    private String attribute;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

    public String getCourseReplaceid() {
        return courseReplaceid;
    }

    public void setCourseReplaceid(String courseReplaceid) {
        this.courseReplaceid = courseReplaceid;
    }



    private String courseReplaceid;

    public String getCourseReplaceName() {
        return courseReplaceName;
    }

    public void setCourseReplaceName(String courseReplaceName) {
        this.courseReplaceName = courseReplaceName;
    }

    private String courseReplaceName;


}
