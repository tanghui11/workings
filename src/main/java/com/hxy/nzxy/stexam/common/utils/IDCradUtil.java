/*
package com.hxy.nzxy.stexam.common.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
*/
/**
 * 读出身份证工具类
 * * 2018-08-08
 * 创建人：lzl
 *//*

public class IDCradUtil {
    public interface TestDll1 extends Library {

        */
/**

         * 当前路径是在项目下，而不是bin输出目录下。

         *//*

        String path = (TestDll1.class.getResource("/").getPath()+"Sdtapi.dll").replaceAll("%20", " ").substring(1).replace("/", "\\");

        TestDll1 INSTANCE = (TestDll1)Native.loadLibrary("D:\\myWorkspace\\test\\WebRoot\\WEB-INF\\classes\\Sdtapi.dll", IDCradUtil.class);

        public  int InitComm (int iPort);//初始设备
        public int  CloseComm();
        public Boolean HIDSelect(int index);
        public int  Authenticate();
        public int ReadBaseInfos(byte name[],byte sex[],byte folk[],byte birth[],byte code[],byte add[],byte agency[],byte expirestart[],byte expireend[]);
        public int ReadBaseInfosPhoto(byte name[],byte sex[],byte folk[],byte birth[],byte code[],byte add[],byte agency[],byte expirestart[],byte expireend[],String directory);
        // public int  ReadBaseInfos(String Name, String Gender, String  Folk,   String BirthDay, String  Code,String Address,String Agency, String ExpireStart,String ExpireEnd);
    }

    */
/**
     * @param args
     *//*

    public static void main(String[] args) {
        byte sid[] = new byte[37];
        byte name[] = new byte[31];
        byte sex[] = new byte[3];
        byte folk[] = new byte[10];
        byte birth[] = new byte[9];
        byte code[] = new byte[71];
        byte add[] = new byte[71];
        byte agency[] = new byte[31];
        byte expirestart[] = new byte[9];
        byte expireend[] = new byte[9];
        String Name = null;
        String Gender="";
        String  Folk="";
        String BirthDay="";
        String  Code="";
        String Address="";
        String Agency="";
        String ExpireStart="";
        String ExpireEnd="";

        int relut=TestDll1.INSTANCE.InitComm(1001);
        //
        Boolean close=TestDll1.INSTANCE.HIDSelect(1);
        int type=TestDll1.INSTANCE.Authenticate();
        //int b=TestDll1.INSTANCE.ReadBaseInfos(Name,Gender, Folk,BirthDay, Code,Address, Agency,ExpireStart, ExpireEnd);
        //int b =TestDll1.INSTANCE.ReadBaseInfos(name,sex,folk,birth,code,add,agency,expirestart,expireend);
        int b =TestDll1.INSTANCE.ReadBaseInfosPhoto(name,sex,folk,birth,code,add,agency,expirestart,expireend,"H:/sfz");
        System.out.println(new String(sid).trim());
        System.out.println(new String(name).trim());

        System.out.println(new String(sex).trim());
        System.out.println(new String(folk).trim());
        System.out.println(new String(birth).trim());
        System.out.println(new String(add).trim());
        System.out.println(new String(code).trim());
        System.out.println(new String(agency).trim());
        System.out.println(new String(expirestart).trim());
        System.out.println(new String(expireend).trim());
        System.out.println(relut);
        System.out.println(close);
        System.out.println(type);
        System.out.println(Name);
        System.out.println(b);

    }
}
*/
