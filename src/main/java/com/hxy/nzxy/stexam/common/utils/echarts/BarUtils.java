package com.hxy.nzxy.stexam.common.utils.echarts;

import com.hxy.nzxy.stexam.common.utils.echarts.option.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 */
public class BarUtils {
    private title title = new title();

    private legend legend = new legend();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.tooltip tooltip = new tooltip();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.toolbox toolbox = new toolbox();

    private List<com.hxy.nzxy.stexam.common.utils.echarts.option.dataZoom> dataZoom = null;

    private com.hxy.nzxy.stexam.common.utils.echarts.option.grid grid = new grid();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.xAxis xAxis = new xAxis();

    private yAxis yAxis = new yAxis();

    private Object series = new ArrayList<>();

    public com.hxy.nzxy.stexam.common.utils.echarts.option.title getTitle() {
        return title;
    }

    public void setTitle(com.hxy.nzxy.stexam.common.utils.echarts.option.title title) {
        this.title = title;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.legend getLegend() {
        return legend;
    }

    public void setLegend(com.hxy.nzxy.stexam.common.utils.echarts.option.legend legend) {
        this.legend = legend;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(com.hxy.nzxy.stexam.common.utils.echarts.option.tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolbox getToolbox() {
        return toolbox;
    }

    public void setToolbox(com.hxy.nzxy.stexam.common.utils.echarts.option.toolbox toolbox) {
        this.toolbox = toolbox;
    }

    public List<com.hxy.nzxy.stexam.common.utils.echarts.option.dataZoom> getDataZoom() {
        if (dataZoom == null) {
            List<dataZoom> dataZoomList = new ArrayList<>();
            dataZoom inside = new dataZoom();
            inside.setType("inside");
            inside.setyAxisIndex(null);
            dataZoomList.add(inside);
            dataZoom slider = new dataZoom();
            slider.setType("slider");
            slider.setyAxisIndex(null);
            dataZoomList.add(slider);
            dataZoom = dataZoomList;
        }
        return dataZoom;
    }

    public void setDataZoom(List<com.hxy.nzxy.stexam.common.utils.echarts.option.dataZoom> dataZoom) {
        this.dataZoom = dataZoom;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.grid getGrid() {
        return grid;
    }

    public void setGrid(com.hxy.nzxy.stexam.common.utils.echarts.option.grid grid) {
        this.grid = grid;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.xAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(com.hxy.nzxy.stexam.common.utils.echarts.option.xAxis xAxis) {
        this.xAxis = xAxis;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.yAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(com.hxy.nzxy.stexam.common.utils.echarts.option.yAxis yAxis) {
        this.yAxis = yAxis;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }
}
