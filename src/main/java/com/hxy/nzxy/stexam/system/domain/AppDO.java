package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 应用管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 09:50:18
 */
public class AppDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //名称
    private String name;
    //类别
    private String type;
    //地址
    private String url;
    //图标
    private String icon;
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
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：类别
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：图标
     */
    public String getIcon() {
        return icon;
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
