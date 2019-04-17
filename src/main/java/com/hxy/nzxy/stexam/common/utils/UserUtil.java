package com.hxy.nzxy.stexam.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserUtil
 * @Description:用户工具类
 * @author: dragon
 * @date: 2017-12-01 10:49:03
 */
public class UserUtil {
    /*
     * 用户字典 格式为：编号=>用户名称
     */
    @SuppressWarnings("rawtypes")
    public static Map mapName = new HashMap();
    /*
     * 用户字典 格式为：编号=>用户名
     */
    @SuppressWarnings("rawtypes")
    public static Map mapUserName = new HashMap();

    /*
     * 添加用户
     */
    @SuppressWarnings("unchecked")
    public static void set(String userid, String name, String userName) {
        mapName.put(userid, name);
        mapUserName.put(userid, userName);
    }

    /*
     * 获取用户名称
     */
    @SuppressWarnings("unchecked")
    public static String getName(String userid) {
        return mapName.getOrDefault(userid, "").toString();
    }

    /*
     * 获取用户名称
     */
    @SuppressWarnings("unchecked")
    public static String getNames(String userids) {
        String strNames = "";
        for (String userid : userids.split(",")) {
            strNames += mapName.getOrDefault(userid, "").toString() + ",";
        }
        return strNames;
    }

    /*
     * 获取用户名称
     */
    @SuppressWarnings("unchecked")
    public static String getUserName(String userid) {
        return mapUserName.getOrDefault(userid, "").toString();
    }

    /*
     * 移除用户
     */
    public static void remove(String userid) {
        mapName.remove(userid);
        mapUserName.remove(userid);
    }
}
