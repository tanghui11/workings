package com.hxy.nzxy.stexam.common.utils;

/**
 * @author Administrator
 * @Title: StudentidCheckCode.java
 * @Package com.hxy.nzxy.stexam.common.utils
 * @Description: 毕业证书编号校验码，准考证号编码规则：自考分类代码+专业层次代码（5：本科，6：专科）+省码+准考证号+校验码
 *                                                6           5                            62  12位    此方法获取
 * @date 2018-12-10 14:39
 */
public class StudentidCheckCode {

    private static int[] wi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static String[] jym = {"1", "0", "2", "9", "8", "7", "6", "5", "4", "3", ""};

    public static String GetCheckCode(String strStudentid) {
        int total = 0;
        String ret = "";
        try {
            for (int i = 0; i < strStudentid.length(); i++) {
                total += Integer.valueOf(strStudentid.substring(i, i + 1)) * wi[i];
            }
            ret = jym[(total % 10)];
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ret = "";
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("65624905121620071" + ":  " + StudentidCheckCode.GetCheckCode("6562490512162007"));
        System.out.println("65624901121623297" + ":  " + StudentidCheckCode.GetCheckCode("6562490112162329"));
        System.out.println("65620103031007635" + ":  " + StudentidCheckCode.GetCheckCode("6562010303100763"));
    }
}
