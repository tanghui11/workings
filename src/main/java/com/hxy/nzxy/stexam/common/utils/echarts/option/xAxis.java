package com.hxy.nzxy.stexam.common.utils.echarts.option;

import com.hxy.nzxy.stexam.common.utils.echarts.option.axisOption.xAxisLabel;

/**
 * 直角坐标系 grid 中的 x 轴，
 * 一般情况下单个 grid 组件最多只能放上下两个 x 轴，
 * 多于两个 x 轴需要通过配置 offset 属性防止同个位置多个 x 轴的重叠。
 */
public class xAxis {

    /**
     * 是否显示 x 轴。
     */
    private Boolean show = true;

    /**
     * x 轴所在的 grid 的索引，默认位于第一个 grid。
     */
    private Integer gridIndex = 0;

    /**
     * 坐标轴类型。
     * 可选：
     * 'value' 数值轴，适用于连续数据。
     * 'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
     * 'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
     * 'log' 对数轴。适用于对数数据。
     */
    private String type = "category";

    /**
     * 坐标轴名称。
     */
    private String name = "";

    /**
     * 坐标轴名称显示位置。
     */
    private String nameLocation = "end";

    /**
     * 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
     * 类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间。
     * 非类目轴，包括时间，数值，对数轴，boundaryGap是一个两个值的数组，分别表示数据最小值和最大值的延伸范围，可以直接设置数值或者相对的百分比，在设置 min 和 max 后无效。
     */
    private Boolean boundaryGap = true;

    /**
     * 坐标轴刻度标签的相关设置。
     */
    private xAxisLabel axisLabel = new xAxisLabel();

    /**
     * 类目数据，在类目轴（type: 'category'）中有效。
     */
    private Object data;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getGridIndex() {
        return gridIndex;
    }

    public void setGridIndex(Integer gridIndex) {
        this.gridIndex = gridIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public xAxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(xAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
