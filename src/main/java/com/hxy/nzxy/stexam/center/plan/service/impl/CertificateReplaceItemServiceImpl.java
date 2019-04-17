package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CertificateReplaceItemDao;
import com.hxy.nzxy.stexam.center.plan.service.CertificateReplaceItemService;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CertificateReplaceItemServiceImpl implements CertificateReplaceItemService {
	@Autowired
	private CertificateReplaceItemDao certificateReplaceItemDao;
	
	@Override
	public CertificateReplaceItemDO get(Long id){
		return certificateReplaceItemDao.get(id);
	}
	
	@Override
	public List<CertificateReplaceItemDO> list(Map<String, Object> map) {
		return certificateReplaceItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return certificateReplaceItemDao.count(map);
	}
	
	@Override
	public int save(CertificateReplaceItemDO certificateReplaceItem){
		return certificateReplaceItemDao.save(certificateReplaceItem);
	}

	public int zsiSave(CertificateReplaceItemDO certificateReplace){
		return certificateReplaceItemDao.zsiSave(certificateReplace);
	}

	@Override
	public int update(CertificateReplaceItemDO certificateReplaceItem){
		return certificateReplaceItemDao.update(certificateReplaceItem);
	}
	
	@Override
	public int remove(Long id){
		return certificateReplaceItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return certificateReplaceItemDao.batchRemove(ids);
	}

	@Override
	public List<CommonCourseReplaceItemNewDO> itemList(Map<String, Object> map){
		return certificateReplaceItemDao.itemList(map);
	}

	//证书顶替课程批量导入
	@Override
	public String ZSIbatchImport(Long fatherCourseId, String fileName, MultipartFile file){


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
			return readExcelValue(fatherCourseId,wb,tempFile);
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
	private String readExcelValue(Long fatherCourseId, Workbook wb,File tempFile) {

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
		List<CertificateReplaceItemDO> userKnowledgeBaseList = new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		CertificateReplaceItemDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new CertificateReplaceItemDO();

			String courseid = "";
			String courseName = "";
			//String remark = "";

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						courseid = cell.getStringCellValue();
						//courseid = FieldDictUtil.get(Constant.APPID, "pla_course_nzxy", "id", courseid);
						if (StringUtils.isEmpty(courseid)) {
							rowMessage += "课程代码不能为空；";
						} else if (courseid.length() > 200) {
							rowMessage += "课程代码不能为空的长度不能超过200；";
						}
						tempUserKB.setCourseid(courseid);
					}
//					else if (c == 1) {
//						flag = cell.getStringCellValue();
//						flag = FieldDictUtil.get(Constant.APPID, "pla_course_replace", "flag", flag);
//						if (StringUtils.isEmpty(flag)) {
//							rowMessage += "顶替类别不能为空；";
//						} else if (flag.length() > 2) {
//							rowMessage += "顶替类别的长度不能超过1；";
//						}
//						tempUserKB.setFlag(flag);
//
//					}
//					else if (c == 2) {
//						remark = cell.getStringCellValue();
//						if (remark.length() > 200) {
//							rowMessage += "备注的长度不能超过1";
//						}
//						tempUserKB.setRemark(remark);
//
//					}
				}
				else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}
			}

			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				tempUserKB.setEnabledFlag(1);
				tempUserKB.setCertificateReplaceid(fatherCourseId);
				userKnowledgeBaseList.add(tempUserKB);
			}
		}
		//删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}

		//验证上传数据在数据库是否已存在
		List<CertificateReplaceItemDO> list=certificateReplaceItemDao.listZSI(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，编号为";
			for(CertificateReplaceItemDO spe : list){
				errorMsg += spe.getId()+",";
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}

		//全部验证通过才导入到数据库
		if (StringUtils.isEmpty(errorMsg)) {
			for (CertificateReplaceItemDO courseClassify : userKnowledgeBaseList) {
				this.zsiSave(courseClassify);
			}
			errorMsg = "导入成功，共" + userKnowledgeBaseList.size() + "条数据！";
		}
		return errorMsg;
	}

}
