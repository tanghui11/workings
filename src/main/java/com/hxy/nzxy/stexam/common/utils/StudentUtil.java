package com.hxy.nzxy.stexam.common.utils;

/**
 * @ClassName: UserUtil
 * @Description:学生工具类
 * @author: dragon
 * @date: 2017-12-01 10:49:03
 */
public class StudentUtil {

    /*
     * 获取学生照片相对路径
     */
    public static String getPicPath(String certificateNo) {
        if (certificateNo.length() == 18) {
            return certificateNo.substring(0, 4) + "/" + certificateNo.substring(4, 6) + "/" + certificateNo.substring(6, 10) + "/";
        } else {
            return certificateNo;
        }
    }
}
