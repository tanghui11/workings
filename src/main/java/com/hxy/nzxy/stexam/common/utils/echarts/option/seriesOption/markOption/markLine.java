package com.hxy.nzxy.stexam.common.utils.echarts.option.seriesOption.markOption;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表标线。
 */
public class markLine {

    private List<markPointData> data = null;

    public List<markPointData> getData() {
        if (data == null) {
            List<markPointData> markPointDataList = new ArrayList<>();
            markPointData markPointData = new markPointData();
            markPointData.setName("平均值");
            markPointData.setType("average");
            markPointDataList.add(markPointData);
            data = markPointDataList;
        }
        return data;
    }

    public void setData(List<markPointData> data) {
        this.data = data;
    }
}
