package com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption;

/**
 * 数据区域缩放。目前只支持直角坐标系的缩放。
 */
public class dataZoom {
    /**
     * 是否显示该工具。
     */
    private Boolean show = true;

    /**
     * 指定哪些 xAxis 被控制。
     * 如果缺省则控制所有的x轴。如果设置为 false 则不控制任何x轴。
     * 如果设置成 3 则控制 axisIndex 为 3 的x轴。
     * 如果设置为 [0, 3] 则控制 axisIndex 为 0 和 3 的x轴。
     */
    private Integer[] xAxisIndex = new Integer[]{0};

    /**
     * 指定哪些 yAxis 被控制。
     * 如果缺省则控制所有的y轴。如果设置为 false 则不控制任何y轴。
     * 如果设置成 3 则控制 axisIndex 为 3 的y轴。
     * 如果设置为 [0, 3] 则控制 axisIndex 为 0 和 3 的y轴。
     */
    private Integer[] yAxisIndex = new Integer[]{0};

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
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
}
