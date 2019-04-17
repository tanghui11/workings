package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption;

import com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption.markLine;
import com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption.markPoint;
import com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption.markPoint;

/**
 * bar
 */
public class bar {

    private String type = "bar";

    /**
     * 系列名称，
     * 用于tooltip的显示，legend 的图例筛选，
     * 在 setOption 更新数据和配置项时用于指定对应的系列。
     */
    private String name;

    /**
     * 该系列使用的坐标系，可选：
     * 'cartesian2d' : 使用二维的直角坐标系（也称笛卡尔坐标系），通过 xAxisIndex, yAxisIndex指定相应的坐标轴组件。
     * 'polar' : 使用极坐标系，通过 polarIndex 指定相应的极坐标组件
     */
    private String coordinateSystem = "cartesian2d";

    /**
     * 使用的 x 轴的 index，在单个图表实例中存在多个 x 轴的时候有用
     */
    private Integer xAxisIndex = 0;

    /**
     * 使用的 y 轴的 index，在单个图表实例中存在多个 y 轴的时候有用
     */
    private Integer yAxisIndex = 0;

    /**
     * 系列中的数据内容数组。数组项通常为具体的数据项。
     */
    private Object data;

    /**
     * 图表标注。
     */
    private com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption.markPoint markPoint = new markPoint();

    /**
     * 图表标线。
     */
    private com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption.markLine markLine = new markLine();

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinateSystem() {
        return coordinateSystem;
    }

    public void setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public Integer getxAxisIndex() {
        return xAxisIndex;
    }

    public void setxAxisIndex(Integer xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
    }

    public Integer getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public markPoint getMarkPoint() {
        return markPoint;
    }

    public void setMarkPoint(markPoint markPoint) {
        this.markPoint = markPoint;
    }

    public markLine getMarkLine() {
        return markLine;
    }

    public void setMarkLine(markLine markLine) {
        this.markLine = markLine;
    }
}