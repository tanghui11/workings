package com.hxy.nzxy.stexam.common.utils.echarts.option;

import com.hxy.nzxy.stexam.common.utils.echarts.option.axisOption.yAxisLabel;
import com.hxy.nzxy.stexam.common.utils.echarts.option.axisOption.yAxisLabel;

/**
 * 直角坐标系 grid 中的 x 轴，
 * 一般情况下单个 grid 组件最多只能放上下两个 x 轴，
 * 多于两个 x 轴需要通过配置 offset 属性防止同个位置多个 x 轴的重叠。
 */
public class yAxis {

    /**
     * 是否显示 y 轴。
     */
    private Boolean show = true;

    /**
     * 坐标轴类型。
     * 可选：
     * 'value' 数值轴，适用于连续数据。
     * 'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
     * 'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
     * 'log' 对数轴。适用于对数数据。
     */
    private String type = "value";

    /**
     * 坐标轴刻度标签的相关设置。
     */
    private yAxisLabel axisLabel = new yAxisLabel();

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public yAxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(yAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}