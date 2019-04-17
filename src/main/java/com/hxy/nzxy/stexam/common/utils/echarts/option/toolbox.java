package com.hxy.nzxy.stexam.common.utils.echarts.option;

import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.feature;

/**
 * 工具栏。内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具。
 */
public class toolbox {
    /**
     * 是否显示工具栏组件。
     */
    private Boolean show = true;

    private feature feature = new feature();

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.feature getFeature() {
        return feature;
    }

    public void setFeature(com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.feature feature) {
        this.feature = feature;
    }
}
