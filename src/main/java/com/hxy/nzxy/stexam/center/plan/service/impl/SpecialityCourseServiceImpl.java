package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityCourseDao;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityCourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
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
public class SpecialityCourseServiceImpl implements SpecialityCourseService {
	@Autowired
	private SpecialityCourseDao specialityCourseDao;
	
	@Override
	public SpecialityCourseDO get(Long id){
		return specialityCourseDao.get(id);
	}
	
	@Override
	public List<SpecialityCourseDO> list(Map<String, Object> map){
		return specialityCourseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialityCourseDao.count(map);
	}
	
	@Override
	public int save(SpecialityCourseDO specialityCourse){
		return specialityCourseDao.save(specialityCourse);
	}
	
	@Override
	public int update(SpecialityCourseDO specialityCourse){
		return specialityCourseDao.update(specialityCourse);
	}
	
	@Override
	public int remove(Long id){
		return specialityCourseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return specialityCourseDao.batchRemove(ids);
	}

	@Override
	public int batchSave(List<SpecialityCourseDO> list) {
		return specialityCourseDao.batchSave(list);
	}

	@Override
	public List<SpecialityCourseDO> serachSpecialityIdList(Map<String, Object> map) {
		return specialityCourseDao.searchSpecialityIdList(map);
	}



	//批量导入
	@Override
	public String batchImport(String fileName, MultipartFile file,String specialityRecordid) {

		File uploadDir = new File("E:\\fileupload");
		//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!uploadDir.exists()) uploadDir.mkdirs();
		//新建一个文件
		File tempFile = new File("E:\\fileupload\\" + new Date().getTime() + ".xlsx");
		//初始化输入流
		InputStream is = null;
		try {
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
			//根据excel里面的内容读取知识库信息
			return readExcelValue(wb, tempFile,specialityRecordid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return "导入出错！请检查数据格式！";
	}

	@Override
	public void saveBatch(List<SpecialityCourseDO> userKnowledgeBaseList) {
		SpecialityCourseDao.saveBatch(userKnowledgeBaseList);
	}

	/**
	 * 解析Excel里面的数据
	 *
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile ,String specialityRecordid) throws ParseException {

		//错误信息接收器
		String errorMsg = "";
		//得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		//得到Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();
		//总列数
		int totalCells = 0;
		//得到Excel的列数(前提是有行数)，从第二行算起
		if (totalRows >= 2 && sheet.getRow(1) != null) {
			totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<SpecialityCourseDO> userKnowledgeBaseList = new ArrayList<>();
		SpecialityCourseDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new SpecialityCourseDO();

			String id = "";
			String courseid = "";
			String bookName = "";
			String courseName = "";
			String type = "";
			String classify = "";
			String status = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				if (StringUtils.isEmpty(specialityRecordid)){
					return rowMessage += "未获取专业，请返回重新选择设置课程！";
				}
				tempUserKB.setSpecialityRecordid(Long.valueOf(specialityRecordid));

				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					 if (c == 0) {
						courseid = cell.getStringCellValue();
						if (StringUtils.isEmpty(courseid)) {
							rowMessage += "课程代码不能为空；";
						} else if (courseid.length() > 20) {
							rowMessage += "课程代码的长度不能超过20；";
						}
						tempUserKB.setCourseid(courseid);
					}  else if (c == 1) {
						courseName = cell.getStringCellValue();
						if (StringUtils.isEmpty(courseName)) {
							rowMessage += "课程名称不能为空；";
						} else if (courseName.length() > 20) {
							rowMessage += "课程名称的长度不能超过20；";
						}
						tempUserKB.setCourseName(courseName);
					} else if (c == 2) {
						bookName = cell.getStringCellValue();
						if (StringUtils.isEmpty(bookName)) {
							rowMessage += "教材编号不能为空；";
						} else if (bookName.length() > 20) {
							rowMessage += "教材编号的长度不能超过20；";
						}
						 String bookid = FieldDictUtil.get(Constant.APPID,"pla_book_nzxy","id",bookName);
						tempUserKB.setBookid(Long.valueOf(bookid));
					}else if (c == 3) {
						classify = cell.getStringCellValue();
						classify = FieldDictUtil.get(Constant.APPID,"pla_speciality_course","classify",classify);
						if (StringUtils.isEmpty(classify)) {
							rowMessage += "分类不能为空";
						}
						tempUserKB.setClassify(classify);
					}  else if (c == 4 ){
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID,"pla_speciality_course","type",type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空";
						}
						tempUserKB.setType(type);
					}else if (c == 5 ){
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID,"pla_speciality_course","status",status);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "状态不能为空";
						}
						tempUserKB.setType(status);
					}else if (c == 6 ){
						//打印码
					}else if (c == 7 ){
						//排号
					}else if (c == 8 ){
							//标记
					}else {
						rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
					}
				}/**/
			}
			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		if (StringUtils.isEmpty(errorMsg)) {
			//验证上传数据在数据库是否已存在
			List<SpecialityCourseDO> list= SpecialityCourseDao.listCZ(userKnowledgeBaseList);
			if(list.size()>0){
				errorMsg ="数据重复，重复的编号为";
				for(SpecialityCourseDO spe : list){
					errorMsg += spe.getCourseid()+",";
				}
				errorMsg +="数据库已存在,不能重复添加";
			}
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			this.saveBatch(userKnowledgeBaseList);
			errorMsg = "导入成功，共" + userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}