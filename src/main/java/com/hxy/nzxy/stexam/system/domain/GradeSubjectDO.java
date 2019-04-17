package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 年级科目
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
public class GradeSubjectDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //年级编号
    private String gradeid;
    //科目编号
    private String subjectid;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

    private List<String> subjects;

    /**
     * 设置：编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：年级编号
     */
    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    /**
     * 获取：年级编号
     */
    public String getGradeid() {
        return gradeid;
    }

    /**
     * 设置：科目编号
     */
    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * 获取：科目编号
     */
    public String getSubjectid() {
        return subjectid;
    }

    /**
     * 设置：操作员
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取：操作员
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置：操作日期
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：操作日期
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置：科目
     */
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    /**
     * 获取：科目
     *
     * @return
     */
    public List<String> getSubjects() {
        return subjects;
    }
}
