package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;


/**
 * 帮助文件
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-13 16:44:38
 */
public class HelpDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //菜单编号
    private String menuid;
    //内容
    private String content;
    //状态
    private String status;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;

    /**
     * 设置：菜单编号
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取：菜单编号
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
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
