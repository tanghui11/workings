package com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption;

/**
 * 动态类型切换, 包括
 * 'line'（切换为折线图）,
 * 'bar'（切换为柱状图）,
 * 'stack'（切换为堆叠模式）,
 * 'tiled'（切换为平铺模式）。
 */
public class magicType {
    /**
     * 是否显示该工具。
     */
    private Boolean show = true;

    /**
     * 启用的动态类型，包括
     * 'line'（切换为折线图）,
     * 'bar'（切换为柱状图）,
     * 'stack'（切换为堆叠模式）,
     * 'tiled'（切换为平铺模式）。
     */
    private String[] type = new String[]{
            "line",
            "bar",
            "stack",
            "tiled"
    };

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }
}
