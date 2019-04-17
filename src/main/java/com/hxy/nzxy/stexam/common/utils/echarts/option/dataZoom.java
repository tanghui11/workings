package com.hxy.nzxy.stexam.common.utils.echarts.option;

/**
 * dataZoom 组件 用于区域缩放，从而能自由关注细节的数据信息，或者概览数据整体，或者去除离群点的影响。
 * 现在支持这几种类型的 dataZoom 组件：
 * 内置型数据区域缩放组件（dataZoomInside）：内置于坐标系中，使用户可以在坐标系上通过鼠标拖拽、鼠标滚轮、手指滑动（触屏上）来缩放或漫游坐标系。
 * 滑动条型数据区域缩放组件（dataZoomSlider）：有单独的滑动条，用户在滑动条上进行缩放或漫游。
 */
public class dataZoom {

    /**
     * inside: 内置型数据区域缩放组件
     * slider: 滑动条型数据区域缩放组件
     */
    private String type = "slider";

    /**
     * type => inside 时有效
     * 是否停止组件的功能。
     */
    private Boolean disabled = false;

    /**
     * type => slider 时有效
     * 是否停止组件的功能。
     */
    private Boolean show = true;

    /**
     * 数据窗口范围的起始百分比。范围是：0 ~ 100。表示 0% ~ 100%。
     */
    private Integer start = 0;

    /**
     * 数据窗口范围的结束百分比。范围是：0 ~ 100。表示 0% ~ 100%。
     */
    private Integer end = 100;

    /**
     * 设置组件控制的x轴
     */
    private Integer[] xAxisIndex = new Integer[]{0};

    /**
     * 设置组件控制的y轴
     */
    private Integer[] yAxisIndex = new Integer[]{0};

    /**
     * dataZoom 的运行原理是通过 数据过滤 来达到 数据窗口缩放 的效果。数据过滤模式的设置不同，效果也不同。
     * 可选值为：
     * 'filter'：当前数据窗口外的数据，被 过滤掉。即 会 影响其他轴的数据范围。每个数据项，只要有一个维度在数据窗口外，整个数据项就会被过滤掉。
     * 'weakFilter'：当前数据窗口外的数据，被 过滤掉。即 会 影响其他轴的数据范围。每个数据项，只有当全部维度都在数据窗口同侧外部，整个数据项才会被过滤掉。
     * 'empty'：当前数据窗口外的数据，被 设置为空。即 不会 影响其他轴的数据范围。
     * 'none': 不过滤数据，只改变数轴范围。
     */
    private String filterMode = "empty";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer[] getxAxisIndex() {
        return xAxisIndex;
    }

    public void setxAxisIndex(Integer[] xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
    }

    public Integer[] getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(Integer[] yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public String getFilterMode() {
        return filterMode;
    }

    public void setFilterMode(String filterMode) {
        this.filterMode = filterMode;
    }
}
