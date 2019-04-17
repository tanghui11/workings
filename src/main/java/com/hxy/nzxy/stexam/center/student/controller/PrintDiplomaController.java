package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.domain.PrintDiplomaDO;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ypp
 * @Title: printDiploma
 * @Description:
 * @date 2018/12/2813:30
 */
@Controller
@RequestMapping("/student/printDiploma")
public class PrintDiplomaController {

    @GetMapping()
    @RequiresPermissions("student:printDiploma:printDiploma")
    String printDiploma() {
        return "center/student/printDiploma";
    }

    /**
     * 批量导入
     */
    @PostMapping("/savBathData")
    @RequiresPermissions("student:studentStudent:savBathData")
    public List savBathData(@RequestParam(value = "filename") MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        File uploadDir = new File("E:\\center\\student\\printDiploma\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件
        File tempFile = new File("E:\\center\\student\\printDiploma\\fileupload\\" + new Date().getTime() + ".xlsx");
        //初始化输入流
        InputStream is = null;
        //将上传的文件写入新建的文件中
        file.transferTo(tempFile);
        //根据新建的文件实例化输入流
        is = new FileInputStream(tempFile);
        //根据版本选择创建Workbook的方式
        Workbook wb = null;
        //根据文件名判断文件是2003版本还是2007版本
        if (ExcelImportUtils.isExcel2007(fileName)) {
            wb = new XSSFWorkbook(is);
        } else {
            wb = new HSSFWorkbook(is);
        }
        is.close();
        //根据excel里面的内容读取知识库信息
        return readExcelValue(wb, tempFile);
    }

    private List readExcelValue(Workbook wb, File tempFile) {

        //错误信息接收器
        String errorMsg = "";
        //得到第一个shell,一般是字段的名称
        Sheet sheet = wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if (totalRows >= 2 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        List<PrintDiplomaDO> userKnowledgeBaseList = new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
        PrintDiplomaDO tempUserKB;
        String br = "<br/>";

        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) {
                errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
                continue;
            }
            tempUserKB = new PrintDiplomaDO();

            String name = "";
            String province = "";
            String city = "";
            String speciality = "";
            String specialityLevel = "";

            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                cell.setCellType(CellType.STRING);
                if (null != cell) {
                    if (c == 0) {
                        name = cell.getStringCellValue();
                        if (StringUtils.isEmpty(name)) {
                            rowMessage += "姓名不能为空；";
                        } else if (name.length() > 20) {
                            rowMessage += "姓名的长度不能超过20；";
                        }
                        tempUserKB.setName(name);
                    } else if (c == 2) {
                        province = cell.getStringCellValue();
                        if (StringUtils.isEmpty(province)) {
                            rowMessage += "籍贯省不能为空；";
                        } else if (province.length() > 20) {
                            rowMessage += "籍贯省的长度不能超过20；";
                        }
                        tempUserKB.setProvince(province);
                    }
                } else if (c == 3) {
                    city = cell.getStringCellValue();
                    if (StringUtils.isEmpty(city)) {
                        rowMessage += "籍贯市不能为空；";
                    } else if (city.length() > 20) {
                        rowMessage += "籍贯市的长度不能超过20；";
                    }
                    tempUserKB.setCity(city);
                } else if (c == 4) {
                    speciality = cell.getStringCellValue();
                    if (StringUtils.isEmpty(speciality)) {
                        rowMessage += "专业不能为空；";
                    } else if (speciality.length() > 20) {
                        rowMessage += "专业的长度不能超过20；";
                    }
                    tempUserKB.setSpeciality(speciality);
                } else if (c == 5) {
                    specialityLevel = cell.getStringCellValue();
                    if (StringUtils.isEmpty(specialityLevel)) {
                        rowMessage += "专业层次不能为空；";
                    } else if (specialityLevel.length() > 20) {
                        rowMessage += "专业层次的长度不能超过20；";
                    }
                    tempUserKB.setSpeciality(specialityLevel);
                } else {
                    rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
                }
            }
            //拼接每行的错误提示
            if (!StringUtils.isEmpty(rowMessage)) {
                errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
            } else {
                tempUserKB.setOperator(ShiroUtils.getUserId().toString());
                tempUserKB.setEnabledFlag(1);
                userKnowledgeBaseList.add(tempUserKB);
            }
        }
        //删除上传的临时文件
        if (tempFile.exists()) {
            tempFile.delete();
        }
        return userKnowledgeBaseList;
    }
}