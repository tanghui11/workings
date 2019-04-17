package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

/**
 * @author ypp
 * @Title: WarehousingScoreEditRecordDO
 * @Description:
 * @date 2018/11/299:24
 */
public class WarehousingScoreEditRecordDO implements Serializable {
    //log id
    private String id;
    //成绩id
    private String scoreid;
    //原始成绩
    private String grade;
    //修改后成绩
    private String newGrade;
    //备注
    private String remark;
    //状态
    private String operator;
    //操作日期
    private String updateDate;



    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getScoreid() {
        return scoreid;
    }

    public void setScoreid(String scoreid) {
        this.scoreid = scoreid;
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
