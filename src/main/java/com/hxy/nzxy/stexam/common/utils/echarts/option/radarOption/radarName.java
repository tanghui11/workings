package com.hxy.nzxy.stexam.common.utils.echarts.option.radarOption;

public class radarName {

    /**
     * 是否显示指示器名称。
     */
    private Boolean show = true;

    /**
     * 雷达图每个指示器名称的配置项。
     */
    private String formatter;

    /**
     * 文字的颜色。
     */
    private String color = "#000";

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
