package com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption;

/**
 * 数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
 */
public class dataView {
    /**
     * 是否显示该工具。
     */
    private Boolean show = true;

    /**
     * 是否不可编辑（只读）。
     */
    private Boolean readOnly = true;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }
}
