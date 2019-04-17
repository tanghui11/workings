package com.hxy.nzxy.stexam.common.utils.echarts.option;

import com.hxy.nzxy.stexam.common.utils.echarts.option.radarOption.radarIndicator;
import com.hxy.nzxy.stexam.common.utils.echarts.option.radarOption.radarName;

import java.util.List;

/**
 * 雷达图坐标系组件，只适用于雷达图。
 */
public class radar {
    /**
     * 雷达图每个指示器名称的配置项。
     */
    private radarName name = new radarName();

    /**
     * 雷达图绘制类型，支持 'polygon' 和 'circle'。
     */
    private String shape = "polygon";

    /**
     * 中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
     * 支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度。
     */
    private String[] center = new String[]{"50%", "60%"};

    /**
     * 雷达图的指示器，用来指定雷达图中的多个变量（维度），如下示例。
     * indicator: [
     * { name: '销售（sales）', max: 6500},
     * { name: '管理（Administration）', max: 16000, color: 'red'}, // 标签设置为红色
     * { name: '信息技术（Information Techology）', max: 30000},
     * { name: '客服（Customer Support）', max: 38000},
     * { name: '研发（Development）', max: 52000},
     * { name: '市场（Marketing）', max: 25000}
     * ]
     */
    private List<radarIndicator> indicator;

    public radarName getName() {
        return name;
    }

    public void setName(radarName name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String[] getCenter() {
        return center;
    }

    public void setCenter(String[] center) {
        this.center = center;
    }

    public List<radarIndicator> getIndicator() {
        return indicator;
    }

    public void setIndicator(List<radarIndicator> indicator) {
        this.indicator = indicator;
    }
}
