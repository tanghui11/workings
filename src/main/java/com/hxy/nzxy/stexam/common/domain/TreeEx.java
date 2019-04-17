package com.hxy.nzxy.stexam.common.domain;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * tree TODO <br>
 *
 * @author kangxu2 2017-1-7
 */
public class TreeEx<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 显示节点文本
     */
    private String text;

    /**
     * 节点的子节点
     */
    private boolean children = false;
    /**
     * 显示节点文本
     */
    private String icon;
    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public TreeEx(String id, String text, boolean children, String icon) {
        super();
        this.id = id;
        this.text = text;
        this.children = children;
        this.icon = icon;
    }

    public TreeEx() {
        super();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}