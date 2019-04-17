package com.hxy.nzxy.stexam.common.utils.echarts.option;

/**
 * 图例组件。
 * 图例组件展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制哪些系列不显示。
 */
public class legend {

    private Boolean show = true;

    /**
     * 图例的数据数组。
     * 数组项通常为一个字符串，
     * 每一项代表一个系列的 name（如果是饼图，也可以是饼图单个数据的 name）。
     * 图例组件会自动根据对应系列的图形标记（symbol）来绘制自己的颜色和标记，
     * 特殊字符串 ''（空字符串）或者 '\n'（换行字符串）用于图例的换行。
     */
    private Object data;

    private String left = "center";

    private String right = "center";

    private String top = "auto";

    private String bottom = "10";

    /**
     * 用来格式化图例文本，支持字符串模板和回调函数两种形式。
     */
    private String formatter = null;

    /**
     * 图例列表的布局朝向。
     * 可选：
     * 'horizontal' 横
     * 'vertical' 竖
     */
    private String orient = "horizontal";

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }
}
