package com.hxy.nzxy.stexam.common.utils;

import org.omg.CORBA.SystemException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    //   将长整型数组转成字符串
    public static String[] longToString(Long[] array){
        if(array == null || array.length == 0) return null;
        String[] strArr = new String[array.length];
        for(int i=0;i<strArr.length;i++){
            strArr[i] = String.valueOf(array[i]);
        }
        return strArr;
    }

 //   将字符串转成长整型数组

    public static Long[] StringtoLong(String str, String regex) {
        String strs[] = str.split(regex);
        Long array[] = new Long[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Long.parseLong(strs[i]);
        }
        return array;
    }
    public static long StringToLong(String string) {
        return StringToLong(string, 0);
    }

    public static long StringToLong(String string, long defaultValue) {
        if ((string == null) || (string.equalsIgnoreCase(""))) {
            return defaultValue;
        }
        long ret;
        try {
            ret = Long.parseLong(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }

        return ret;
    }

    // 字符串转日期
    public static Date stringToDate(String sDate, String pattern) throws SystemException
    {
        if (null == pattern || "".equals(pattern))
        {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try
        {

            formatter.parse(sDate);

            return formatter.parse(sDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    // 日期转字符串
    public static String dateToString(Date date, String pattern)
    {
        if (null == pattern || "".equals(pattern))
        {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

}
