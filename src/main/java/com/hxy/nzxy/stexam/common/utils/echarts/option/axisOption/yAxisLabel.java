package com.hxy.nzxy.stexam.common.utils.echarts.option.axisOption;

public class yAxisLabel {
    /**
     * 是否显示坐标轴轴线。
     */
    private Boolean show = true;

    /**
     * 坐标轴刻度标签的显示间隔，在类目轴中有效。
     * 默认会采用标签不重叠的策略间隔显示标签。
     * 可以设置成 0 强制显示所有标签。
     * 如果设置为 1，表示『隔一个标签显示一个标签』，如果值为 2，表示隔两个标签显示一个标签，以此类推。
     */
    private Integer interval = 0;

    private String formatter = null;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
