package com.hxy.nzxy.stexam.common.utils.echarts;

import com.hxy.nzxy.stexam.common.utils.echarts.option.*;

import java.util.ArrayList;

public class RadarUtils {
    private title title = new title();

    private tooltip tooltip = new tooltip();

    private toolbox toolbox = new toolbox();

    private legend legend = new legend();

    private radar radar = new radar();

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

    public radar getRadar() {
        return radar;
    }

    public void setRadar(radar radar) {
        this.radar = radar;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }
}
