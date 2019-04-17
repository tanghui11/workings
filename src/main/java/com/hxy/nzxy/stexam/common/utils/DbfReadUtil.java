package com.hxy.nzxy.stexam.common.utils;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbfReadUtil {

    public List<Map<String,String>>  readDbf(MultipartFile file) {
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> rowList = null;
        InputStream fis = null;
        try {
            fis = file.getInputStream();
            DBFReader reader = new DBFReader(fis);
            reader.setCharactersetName("UTF-8");
            Map<Integer,String> firstRow = new HashMap<>();
            //获取文件中字段的个数
             int fieldCount = reader.getFieldCount();
            for (int i = 0; i < fieldCount; i++) {
                DBFField field = reader.getField(i);
                firstRow.put(i,field.getName());
            }
            Object[] rowValues;
            //读取记录
            while ((rowValues = reader.nextRecord()) != null) {
                rowList = new HashMap<>();
                for (int i = 0; i < rowValues.length; i++) {
                    Object rowValue = rowValues[i];
                    if(rowValue==null){
                        rowList.put(firstRow.get(i),null);
                    }else{
                        rowList.put(firstRow.get(i),rowValue.toString());
                    }



                }
                list.add(rowList);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int  readDbf1(MultipartFile file) throws IOException {
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> rowList = null;
        InputStream fis = file.getInputStream();
        DBFReader reader = new DBFReader(fis);
        Map<Integer,String> firstRow = new HashMap<>();
        //获取文件中字段的个数
        int fieldCount = reader.getFieldCount();
        for (int i = 0; i < fieldCount; i++) {
            DBFField field = reader.getField(i);
            firstRow.put(i,field.getName());
        }
        return fieldCount;
    }
}
