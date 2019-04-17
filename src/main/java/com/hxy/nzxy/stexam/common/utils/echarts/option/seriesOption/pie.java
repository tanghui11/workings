package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption;

/**
 * 饼图
 * 饼图主要用于表现不同类目的数据在总和中的占比。每个的弧度表示数据数量的比例。
 */
public class pie {

    private String type = "pie";

    /**
     * 系列名称，
     * 用于tooltip的显示，legend 的图例筛选，
     * 在 setOption 更新数据和配置项时用于指定对应的系列。
     */
    private String name;

    /**
     * 饼图的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
     * 支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度。
     */
    private String[] center = new String[]{"50%", "60%"};

    /**
     * 饼图的半径，数组的第一项是内半径，第二项是外半径。
     * 支持设置成百分比，相对于容器高宽中较小的一项的一半。
     * 可以将内半径设大显示成圆环图（Donut chart）。
     */
    private String radius = "60%";

    /**
     * 系列中的数据内容数组。数组项通常为具体的数据项。
     */
    private Object data;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCenter() {
        return center;
    }

    public void setCenter(String[] center) {
        this.center = center;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
