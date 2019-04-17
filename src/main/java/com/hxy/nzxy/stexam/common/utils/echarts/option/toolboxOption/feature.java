package com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption;

import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.dataView;
import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.magicType;
import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.restore;
import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.saveAsImage;
import com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.restore;

public class feature {
    /**
     * 保存为图片。
     */
    private saveAsImage saveAsImage = new saveAsImage();

    /**
     * 配置项还原。
     */
    private com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.restore restore = new restore();

    /**
     * 数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
     */
    private dataView dataView = new dataView();

    /**
     * 数据区域缩放。目前只支持直角坐标系的缩放。
     */
//    private dataZoom dataZoom = new dataZoom();

    /**
     * 动态类型切换, 包括
     * 'line'（切换为折线图）,
     * 'bar'（切换为柱状图）,
     * 'stack'（切换为堆叠模式）,
     * 'tiled'（切换为平铺模式）。
     */
    private magicType magicType = new magicType();

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.saveAsImage getSaveAsImage() {
        return saveAsImage;
    }

    public void setSaveAsImage(com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.saveAsImage saveAsImage) {
        this.saveAsImage = saveAsImage;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.restore getRestore() {
        return restore;
    }

    public void setRestore(com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.restore restore) {
        this.restore = restore;
    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.dataView getDataView() {
        return dataView;
    }

    public void setDataView(com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.dataView dataView) {
        this.dataView = dataView;
    }

//    public dataZoom getDataZoom() {
//        return dataZoom;
//    }
//
//    public void setDataZoom(dataZoom dataZoom) {
//        this.dataZoom = dataZoom;
//    }

    public com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.magicType getMagicType() {
        return magicType;
    }

    public void setMagicType(com.hxy.nzxy.stexam.common.utils.echarts.option.toolboxOption.featureOption.magicType magicType) {
        this.magicType = magicType;
    }
}
