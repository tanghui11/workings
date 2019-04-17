package com.hxy.nzxy.stexam;
import com.hxy.nzxy.stexam.common.config.FtpConfig;
import com.hxy.nzxy.stexam.common.utils.FtpUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class test {
    FtpConfig ftpConfig =new FtpConfig();
    public  static  void  main(String[] args){
        //  ();
    }
        @Test
        public void test015 () {
            ftpConfig.setAddress("218.240.154.15");
            ftpConfig.setFtpPort("21");
            ftpConfig.setFtpUserName("nzxy");
            ftpConfig.setFtpPassword("Nzxy@1234");
            ftpConfig.setFtpBasepath("/home/ftppic");
            ftpConfig.setFtpBaseUrl("http://ftp.mukexiaoyuan.com:8899/");
            long startTime=System.currentTimeMillis();
            try {
                ZipInputStream Zin=new ZipInputStream(new FileInputStream("F:\\MyTest.zip"));//输入源zip路径
                BufferedInputStream Bin=new BufferedInputStream(Zin);
                String Parent="F:\\ziptest\\"; //输出路径（文件夹目录）

                File Fout=null;
                ZipEntry entry;
                String yearNumber="";
                String regionCode="";
                InputStream inputStream;
                try {
                    while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                        Fout=new File(Parent,entry.getName());
                        if(!Fout.exists()){
                            (new File(Fout.getParent())).mkdirs();
                        }


                        FileOutputStream out=new FileOutputStream(Fout);
                        BufferedOutputStream Bout=new BufferedOutputStream(out);
                        int b;
                        while((b=Bin.read())!=-1){
                            Bout.write(b);
                        }
                        Bout.close();
                        out.close();
                        System.out.println(Fout+"解压成功");
                        inputStream  = new FileInputStream(Fout);
                        yearNumber="192";
                        regionCode="0101";
                        String[] strings = FtpUtil.pictureUploadByConfig(ftpConfig, entry.getName() , yearNumber + "/" + regionCode, inputStream);
               System.out.print(strings);
                    }
                    Bin.close();
                    Zin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            long endTime=System.currentTimeMillis();
            System.out.println("耗费时间： "+(endTime-startTime)+" ms");
        }

    }
