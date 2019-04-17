package com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption;

/**
 * 保存为图片。
 */
public class saveAsImage {
    /**
     * 是否显示该工具。
     */
    private Boolean show = true;

    /**
     * 保存的图片格式。支持 'png' 和 'jpeg'。
     */
    private String type = "png";

    /**
     * 保存图片的分辨率比例，默认跟容器相同大小，如果需要保存更高分辨率的，可以设置为大于 1 的值，例如 2。
     */
    private Integer pixelRatio = 2;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPixelRatio() {
        return pixelRatio;
    }

    public void setPixelRatio(Integer pixelRatio) {
        this.pixelRatio = pixelRatio;
    }
}
