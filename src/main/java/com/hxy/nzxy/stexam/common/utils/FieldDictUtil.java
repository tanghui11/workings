package com.hxy.nzxy.stexam.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: FieldDictUtil
 * @Description:数据字典工具类
 * @author: dragon
 * @date: 2017-12-01 10:49:03
 */
public class FieldDictUtil {
    /*
     * 存储数据字典
     * 格式为：表名_字段名_字段值=>字段含义
     */
    @SuppressWarnings("rawtypes")
    public static Map map = new ConcurrentHashMap();

    /*
     * 添加字典
     */
    @SuppressWarnings("unchecked")
    public static void set(String appid, String tableName, String fieldName, String fieldValue, String fieldMean) {
        map.put(appid + "_" + tableName + "_" + fieldName + "_" + fieldValue, fieldMean);
    }

    /*
     * 获取字典
     */
    @SuppressWarnings("unchecked")
    public static String get(String appid, String tableName, String fieldName, String fieldValue) {
        return map.getOrDefault(appid + "_" + tableName + "_" + fieldName + "_" + fieldValue, "").toString();
    }

    /*
     * 获取字典
     */
    @SuppressWarnings("unchecked")
    public static String gets(String appid, String tableName, String fieldName, String fieldValues) {
        String strNames = "";
        for (String fieldValue : fieldValues.split(",")) {
            strNames += map.getOrDefault(appid + "_" + tableName + "_" + fieldName + "_" + fieldValue, "").toString() + ",";
        }
        return strNames;
    }

    /*
     * 移除数据字典
     */
    public static void remove(String appid, String tableName, String fieldName, String fieldValue) {
        map.remove(appid + "_" + tableName + "_" + fieldName + "_" + fieldValue);
    }
}
