package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 部门职员
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 09:58:49
 */
public class DeptWorkerDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //部门编号
    private String deptid;
    //职员编号
    private String workerid;
    //状态
    private String status;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

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
     * 设置：部门编号
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * 获取：部门编号
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 设置：职员编号
     */
    public void setWorkerid(String workerid) {
        this.workerid = workerid;
    }

    /**
     * 获取：职员编号
     */
    public String getWorkerid() {
        return workerid;
    }

    /**
     * 设置：状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public String getStatus() {
        return status;
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
}
