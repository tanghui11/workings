package com.hxy.nzxy.stexam.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserUtil
 * @Description:机构工具类
 * @author: dragon
 * @date: 2017-12-01 10:49:03
 */
public class OrgUtil {
    /*
     * 机构字典 格式为：编号=>机构名称
     */
    @SuppressWarnings("rawtypes")
    public static Map mapName = new HashMap();
    /*
     * 机构字典 格式为：编号=>机构代码
     */
    public static Map mapCode = new HashMap();

    /*
     * 添加名字
     */
    @SuppressWarnings("unchecked")
    public static void setName(String userid, String name) {
        mapName.put(userid, name.replace(" ",""));
    }

    /*
     * 添加代码
     */
    @SuppressWarnings("unchecked")
    public static void setCode(String userid, String code) {
        mapCode.put(userid, code.replace(" ",""));
    }

    /*
     * 获取机构名称
     */
    @SuppressWarnings("unchecked")
    public static String getName(String orgid) {
        return mapName.getOrDefault(orgid, "").toString();
    }

    /*
     * 获取机构名称
     */
    @SuppressWarnings("unchecked")
    public static String getNames(String orgids) {
        String strNames = "";
        for (String orgid : orgids.split(",")) {
            strNames += mapName.getOrDefault(orgid, "").toString() + ",";
        }
        return strNames;
    }

    /*
     * 获取机构代码
     */
    @SuppressWarnings("unchecked")
    public static String getCode(String orgid) {
        return mapCode.getOrDefault(orgid, "").toString();
    }

    /*
     * 移除用户
     */
    public static void remove(String orgid) {
        mapName.remove(orgid);
        mapCode.remove(orgid);
    }
}
