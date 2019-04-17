package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

/**
 * @author ypp
 * @Title: WarehousingScoreEditDO
 * @Description:
 * @date 2018/11/2720:47
 */

public class WarehousingScoreEditDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //序号
    private String id;
    //课程代码
    private String courseid;
    //课程名称
    private String courseName;
    //缺考
    private String examFlag;
    //违规
    private String status;
    //修改后成绩
    private String newGrade;
    //备注
    private String remark;
    //成绩
    private String grade;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;


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

    public String getExamFlag() {
        return examFlag;
    }

    public void setExamFlag(String examFlag) {
        this.examFlag = examFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(String newGrade) {
        this.newGrade = newGrade;
    }
}