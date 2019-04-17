package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseClassifyDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseClassifyService;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.CourseClassifyDO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class CourseClassifyServiceImpl implements CourseClassifyService {
	@Autowired
	private CourseClassifyDao courseClassifyDao;
	
	@Override
	public CourseClassifyDO get(String type, String courseid){
		Map map=new HashMap();
		map.put("type",type);
		map.put("courseid",courseid);
		return courseClassifyDao.get(map);
	}

	@Override
	public List<CourseClassifyDO> list(Map<String, Object> map){
		return courseClassifyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseClassifyDao.count(map);
	}
	
	@Override
	public int save(CourseClassifyDO courseClassify){
		return courseClassifyDao.save(courseClassify);
	}
	
	@Override
	public int update(CourseClassifyDO courseClassify){
		return courseClassifyDao.update(courseClassify);
	}
	
	@Override
	public int remove(String type,String courseid){
		Map map=new HashMap();
		map.put("type",type);
		map.put("courseid",courseid);
		return courseClassifyDao.remove(map);

	}
	
	@Override
	public int batchRemove(String[] types){
		return courseClassifyDao.batchRemove(types);
	}


	@Override
	public String batchImport(String fileName, MultipartFile file){
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
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile){

		//错误信息接收器
		String errorMsg = "";
		//得到第一个shell
		Sheet sheet=wb.getSheetAt(0);
		//得到Excel的行数
		int totalRows=sheet.getPhysicalNumberOfRows();
		//总列数
		int totalCells = 0;
		//得到Excel的列数(前提是有行数)，从第二行算起
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<CourseClassifyDO> userKnowledgeBaseList=new ArrayList<>();
		CourseClassifyDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++){
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null){
				errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new CourseClassifyDO();

			String type = "";
			String courseid = "";

			//循环Excel的列
			for(int c = 0; c <totalCells; c++){
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell){
					if(c==0){
						type = cell.getStringCellValue();
						if(StringUtils.isEmpty(type)){
							rowMessage += "类别不能为空；";
						}
						tempUserKB.setType(type);
					}else if(c==1){
						courseid = cell.getStringCellValue();
						if(StringUtils.isEmpty(courseid)){
							rowMessage += "课程代码不能为空；";
						}
						tempUserKB.setCourseid(courseid);
					} else if (c == 2) {

					}
				}else{
					rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
				}
			}
			//拼接每行的错误提示
			if(!StringUtils.isEmpty(rowMessage)){
				errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
			}else{
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		if (StringUtils.isEmpty(errorMsg)) {
			//验证上传数据在数据库是否已存在
			List<CourseClassifyDO> list= courseClassifyDao.listCZ(userKnowledgeBaseList);
			if(list.size()>0){
				errorMsg ="数据重复，重复的编号为";
				for(CourseClassifyDO spe : list){
					errorMsg += spe.getCourseid()+",";
				}
				errorMsg +="数据库已存在,不能重复添加";
			}
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(CourseClassifyDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}
