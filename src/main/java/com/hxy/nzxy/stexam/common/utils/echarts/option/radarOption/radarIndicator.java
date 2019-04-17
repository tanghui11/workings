package com.hxy.nzxy.stexam.common.utils.echarts.option.radarOption;

public class radarIndicator {
    /**
     * 指示器名称。
     */
    private String name;

    /**
     * 指示器的最大值，可选，建议设置
     */
    private Double max;

    /**
     * 指示器的最小值，可选，默认为 0。
     */
    private Double min = 0.0;

    /**
     * 标签特定的颜色。
     */
    private String color = "#333";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
