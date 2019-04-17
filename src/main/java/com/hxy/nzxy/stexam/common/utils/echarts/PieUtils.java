package com.hxy.nzxy.stexam.common.utils.echarts;

import com.hxy.nzxy.stexam.common.utils.echarts.option.legend;
import com.hxy.nzxy.stexam.common.utils.echarts.option.title;
import com.hxy.nzxy.stexam.common.utils.echarts.option.toolbox;
import com.hxy.nzxy.stexam.common.utils.echarts.option.tooltip;

import java.util.ArrayList;

public class PieUtils {
    private com.hxy.nzxy.stexam.common.utils.echarts.option.title title = new title();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.tooltip tooltip = new tooltip();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.toolbox toolbox = new toolbox();

    private com.hxy.nzxy.stexam.common.utils.echarts.option.legend legend = new legend();

    private Object series = new ArrayList<>();

    public title getTitle() {
        return title;
    }

    public void setTitle(title title) {
        this.title = title;
    }

    public tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public toolbox getToolbox() {
        toolbox.getFeature().setMagicType(null);
        toolbox.getFeature().setRestore(null);
        return toolbox;
    }

    public void setToolbox(toolbox toolbox) {
        this.toolbox = toolbox;
    }

    public legend getLegend() {
        return legend;
    }

    public void setLegend(legend legend) {
        this.legend = legend;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }
}
