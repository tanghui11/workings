package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.CourseFreeCenterDO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.dao.CourseFreeCenterDao;
import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCenterService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CourseFreeCenterServiceImpl implements CourseFreeCenterService {

	@Autowired
	private CourseFreeCenterDao courseFreeCenterDao;
	
	@Override
	public CourseFreeDO get(String id){
		return courseFreeCenterDao.get(id);
	}
	
	@Override
	public List<CourseFreeDO> list(Map<String, Object> map){ return courseFreeCenterDao.list(map); }
	
	@Override
	public int count(Map<String, Object> map){
		return courseFreeCenterDao.count(map);
	}
	
	@Override
	public int save(CourseFreeDO courseFreeCenter){
		return courseFreeCenterDao.save(courseFreeCenter);
	}

	@Override
	public int mkSave(CourseFreeCenterDO courseFreeCenter){
		return courseFreeCenterDao.mkSave(courseFreeCenter);
	}
	
	@Override
	public int update(CourseFreeDO courseFreeCenter){
		return courseFreeCenterDao.update(courseFreeCenter);
	}
	
	@Override
	public int remove(String id){
		return courseFreeCenterDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return courseFreeCenterDao.batchRemove(ids);
	}

	//免考课程规则批量导入
	@Override
	public String MKbatchImport(String fileName, MultipartFile file){


		File uploadDir = new  File("E:\\fileupload");
		//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!uploadDir.exists()) uploadDir.mkdirs();
		//新建一个文件
		File tempFile = new File("E:\\fileupload\\" + new Date().getTime() + ".xlsx");
		//初始化输入流
		InputStream is = null;
		try{
			//将上传的文件写入新建的文件中
			file.transferTo(tempFile);

			//根据新建的文件实例化输入流
			is = new FileInputStream(tempFile);
			//根据版本选择创建Workbook的方式
			Workbook wb = null;
			//根据文件名判断文件是2003版本还是2007版本
			if(ExcelImportUtils.isExcel2007(fileName)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
			//根据excel里面的内容读取知识库信息
			return readExcelValue(wb,tempFile);
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			if(is !=null)
			{
				try{
					is.close();
				}catch(IOException e){
					is = null;
					e.printStackTrace();
				}
			}
		}
		return "导入出错！请检查数据格式！";



	}

	/**
	 * 解析Excel里面的数据
	 * @param wb Workbook的方式
	 * @param tempFile 临时文件
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile) {

		//错误信息接收器
		String errorMsg = "";
		//得到第一个shell,一般是字段的名称
		Sheet sheet=wb.getSheetAt(0);
		//得到Excel的行数
		int totalRows=sheet.getPhysicalNumberOfRows();
		//总列数
		int totalCells = 0;
		//得到Excel的列数(前提是有行数)，从第二行算起
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<CourseFreeCenterDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		CourseFreeCenterDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new CourseFreeCenterDO();//定义了一个空的courseFreeCenterDO

			String name = "";
			String remark = "";
			String status = "";
			String type = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "姓名不能为空；";
						} else if (name.length() > 200) {
							rowMessage += "姓名的长度不能超过200；";
						}
						tempUserKB.setName(name);
					} else if (c == 1) {
						remark = cell.getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							rowMessage += "备注不能为空；";
						} else if (remark.length() > 500) {
							rowMessage += "编号的长度不能超过500；";
						}
						tempUserKB.setRemark(remark);
					} else if (c == 2) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "pla_course_free", "status", status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						}
						tempUserKB.setStatus(status);
					} else if (c == 3) {
						type = cell.getStringCellValue();

						type = FieldDictUtil.get(Constant.APPID, "pla_course_free", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "免考类别不能为空,或未找到对应的类别；";
						}
						tempUserKB.setType(type);
					}
				} else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}
			}
			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());//添加操作人
				tempUserKB.setEnabledFlag(1);
				userKnowledgeBaseList.add(tempUserKB);//添加信息到userKnowledgeBaseList
			}
		}
		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		//验证上传数据在数据库是否已存在
//		List<CourseFreeCenterDO> list=courseFreeCenterDao.listMK(userKnowledgeBaseList);
//		if(list.size()>0){
//			errorMsg ="导入失败，名称为";
//			for(CourseFreeCenterDO cfc : list){
//				errorMsg += cfc.getName()+",";
//			}
//			errorMsg +="的免考规则已存在,不能重复添加";
//		}



		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(CourseFreeCenterDO courseClassify : userKnowledgeBaseList){
				this.mkSave(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
	
}
