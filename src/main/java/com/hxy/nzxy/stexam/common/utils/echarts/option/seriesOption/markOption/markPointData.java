package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption;

public class markPointData {
    /**
     * 标注名称。
     */
    private String name;
    /**
     * 特殊的标注类型，用于标注最大值最小值等。
     * 可选:
     * 'min' 最大值。
     * 'max' 最大值。
     * 'average' 平均值。
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
