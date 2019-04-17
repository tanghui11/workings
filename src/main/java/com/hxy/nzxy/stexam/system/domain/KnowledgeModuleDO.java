package com.hxy.nzxy.stexam.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 知识模块
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:06
 */
public class KnowledgeModuleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //编号
    private String id;
    //编号
    private String knowledgeid;
    //编号
    private String parentid;
    //名称
    private String name;
    //拼音
    private String pinyin;
    //层级
    private Integer level;
    //序号
    private Integer seq;
    //是否有子节点
    private String hasChild;
    //是否有知识点
    private String hasPoint;
    //操作员
    private String operator;
    //操作日期
    private String updateDate;
    //类别，查询是使用，区分知识模块，知识点
    private String type;
    //简介，兼容知识点的简介
    private String intro;

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
     * 设置：编号
     */
    public void setKnowledgeid(String knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    /**
     * 获取：编号
     */
    public String getKnowledgeid() {
        return knowledgeid;
    }

    /**
     * 设置：编号
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取：编号
     */
    public String getParentid() {
        return parentid;
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
     * 设置：拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * 获取：拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 设置：层级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取：层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置：序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 获取：序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getHasPoint() {
        return hasPoint;
    }

    public void setHasPoint(String hasPoint) {
        this.hasPoint = hasPoint;
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
     * 获取：类别
     */
    public String getType() {
        return type;
    }
    /**
     * 设置：类别
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * 获取：简介
     */
    public String getIntro() {
        return intro;
    }
    /**
     * 设置：简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "KnowledgeModuleDO{" +
                "id='" + id + '\'' +
                ", knowledgeid='" + knowledgeid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", level=" + level +
                ", seq=" + seq +
                ", operator='" + operator + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
