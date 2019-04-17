package com.hxy.nzxy.stexam.common.utils.echarts.option;

/**
 * 提示框组件。
 */
public class tooltip {

    /**
     * 是否显示提示框组件，包括提示框浮层和 axisPointer
     */
    private Boolean show = true;

    /**
     * 触发类型。
     * 可选：
     * 'item' : 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
     * 'axis' : 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
     * 'none' : 什么都不触发。
     */
    private String trigger = "axis";

    private String formatter;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
