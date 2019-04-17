package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表标注。
 */
public class markPoint {

    /**
     * 标记的图形。
     * 标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
     */
    private String symbol = "pin";

    private List<markPointData> data = null;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<markPointData> getData() {
        if (data == null) {
            List<markPointData> markPointDataList = new ArrayList<>();
            markPointData markPointData = new markPointData();
            markPointData.setName("最大值");
            markPointData.setType("max");
            markPointDataList.add(markPointData);
            markPointData markPointData2 = new markPointData();
            markPointData2.setName("最小值");
            markPointData2.setType("min");
            markPointDataList.add(markPointData2);
            data = markPointDataList;
        }
        return data;
    }

    public void setData(List<markPointData> data) {
        this.data = data;
    }
}