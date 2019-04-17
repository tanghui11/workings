package com.hxy.nzxy.stexam.common.utils.echarts.option.axisOption;

public class xAxisLabel {
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

    /**
     * 刻度标签旋转的角度，在类目轴的类目标签显示不下的时候可以通过旋转防止标签之间重叠。
     * 旋转的角度从 -90 度到 90 度
     */
    private Integer rotate = 30;

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

    public Integer getRotate() {
        return rotate;
    }

    public void setRotate(Integer rotate) {
        this.rotate = rotate;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
