package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption;

/**
 * 雷达图
 * 雷达图主要用于表现多变量的数据，例如球员的各个属性分析。
 */
public class radar {

    private String type = "radar";

    /**
     * 系列名称，
     * 用于tooltip的显示，legend 的图例筛选，
     * 在 setOption 更新数据和配置项时用于指定对应的系列。
     */
    private String name;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
