package com.hxy.nzxy.stexam.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {
    public static XSSFWorkbook generate(Map<Integer, String> titleMap, List<Map> list, String fileName) {
        XSSFWorkbook medicineWb = new XSSFWorkbook();
        XSSFSheet medicineSheet = medicineWb.createSheet("Sheet1");
        //第一行
        XSSFRow row = medicineSheet.createRow(0);
        XSSFCell cell;
        //设置表头
        for (int i = 0; i < titleMap.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titleMap.get(i));
        }
        Map map;
        for (int i = 0; i < list.size(); i++) {
            row = medicineSheet.createRow(i + 1);
            map = list.get(i);
            for (int j = 0; j < map.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue((String) map.get(j));
            }
        }
        medicineWb.setSheetName(0, fileName + ".xlsx");
        return medicineWb;
    }

    public static HSSFWorkbook generate(List<ArrayList<String>> list) {
        HSSFWorkbook medicineWb = new HSSFWorkbook();
        HSSFSheet medicineSheet = medicineWb.createSheet("Sheet1");
        //第一行
        HSSFRow row;
        HSSFCell cell;
        Map map;
        for (int i = 0; i < list.size(); i++) {
            ArrayList arr = list.get(i);
            row = medicineSheet.createRow(i);
            for (int j = 0; j < arr.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue((String) arr.get(j));
            }
        }
        return medicineWb;
    }
}
