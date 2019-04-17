package com.hxy.nzxy.stexam.common.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

        /**
         * 默认日期格式
         */
        public static String DEFAULT_FORMAT = "yyyy-MM-dd";

        /**
         * 测试主方法
         * @param args
         */
        public static void main(String[] args) {
            for(int i = 1951;i < 1960;i++){
                System.out.println(formatDate(getCurrYearFirst(i)));
                System.out.println(formatDate(getCurrYearLast(i)));
            }

        }

        /**
         * 格式化日期
         * @param date 日期对象
         * @return String 日期字符串
         */
        public static String formatDate(Date date){
            SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
            String sDate = f.format(date);
            return sDate;
        }

        /**
         * 获取某年第一天日期
         * @param year 年份
         * @return Date
         */
        public static Date getCurrYearFirst(int year){
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, year);
            Date currYearFirst = calendar.getTime();
            return currYearFirst;
        }

        /**
         * 获取某年最后一天日期
         * @param year 年份
         * @return Date
         */
        public static Date getCurrYearLast(int year){
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, year);
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            Date currYearLast = calendar.getTime();

            return currYearLast;
        }
//获取当前年份
    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }


    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }
}
