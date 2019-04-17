package com.hxy.nzxy.stexam.common.utils.echarts.option;

/**
 * 直角坐标系内绘图网格，单个 grid 内最多可以放置上下两个 X 轴，左右两个 Y 轴。
 */
public class grid {
    /**
     * 是否显示直角坐标系网格
     */
    private Boolean show = false;

    /**
     * 组件离容器四周的距离
     */
    private String left = "10%";

    private String right = "10%";

    private String top = "60";

    private String bottom = "60";

    /**
     * containLabel 为 false 的时候：
     * grid.left grid.right grid.top grid.bottom grid.width grid.height 决定的是由坐标轴形成的矩形的尺寸和位置。
     * 这比较适用于多个 grid 进行对齐的场景，因为往往多个 grid 对齐的时候，是依据坐标轴来对齐的。
     * containLabel 为 true 的时候：
     * grid.left grid.right grid.top grid.bottom grid.width grid.height 决定的是包括了坐标轴标签在内的所有内容所形成的矩形的位置。
     * 这常用于『防止标签溢出』的场景，标签溢出指的是，标签长度动态变化时，可能会溢出容器或者覆盖其他组件。
     */
    private Boolean containLabel = true;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
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

    public Boolean getContainLabel() {
        return containLabel;
    }

    public void setContainLabel(Boolean containLabel) {
        this.containLabel = containLabel;
    }
}
