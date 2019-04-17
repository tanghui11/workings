package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.OldCourseDao;
import com.hxy.nzxy.stexam.center.plan.service.OldCourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.OldCourseDO;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
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
public class OldCourseServiceImpl implements OldCourseService {
	@Autowired
	private OldCourseDao oldCourseDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public OldCourseDO get(String id){
		return oldCourseDao.get(id);
	}

	@Override
	public List<OldCourseDO> list(Map<String, Object> map){
		return oldCourseDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return oldCourseDao.count(map);
	}

	@Override
	public int save(OldCourseDO oldCourse){
		return oldCourseDao.save(oldCourse);
	}

	@Override
	public int update(OldCourseDO oldCourse){
		return oldCourseDao.update(oldCourse);
	}

	@Override
	public int remove(String id){
		return oldCourseDao.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return oldCourseDao.batchRemove(ids);
	}

	@Override
	public List searchlist(Map<String, Object> params) {
		return oldCourseDao.searchlist(params);
	}

	//批量导入
	@Override
	public String batchImport(String fileName, MultipartFile file) {

		File uploadDir = new File("E:\\center\\pla\\coure\\fileupload");
		//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!uploadDir.exists()) uploadDir.mkdirs();
		//新建一个文件
		File tempFile = new File("E:\\center\\pla\\coure\\fileupload\\" + new Date().getTime() + ".xlsx");
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
			return readExcelValue(wb, tempFile);
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
	public void saveBatch(List<OldCourseDO> userKnowledgeBaseList) {
		oldCourseDao.saveBatch(userKnowledgeBaseList);
	}

	/**
	 * 解析Excel里面的数据
	 *
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile) throws ParseException {

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
		List<OldCourseDO> userKnowledgeBaseList = new ArrayList<>();
		OldCourseDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new OldCourseDO();

			String id = "";
			String name = "";
			String pinyin = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						id = cell.getStringCellValue();
						if (StringUtils.isEmpty(id)) {
							rowMessage += "课程编码不能为空；";
						} else if (id.length() > 20) {
							rowMessage += "课程编码的长度不能超过20；";
						}
						tempUserKB.setId(id);
					} else if (c == 1) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "课程名称不能为空；";
						} else if (name.length() > 100) {
							rowMessage += "课程名称的长度不能超过100；";
						}
						tempUserKB.setName(name);
					} else if (c == 2) {
						pinyin = cell.getStringCellValue();
						if (pinyin.length() > 100) {
							rowMessage += "拼音的长度不能超过100；";
						}
						tempUserKB.setPinyin(pinyin);
					} else {
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
			List<OldCourseDO> list= oldCourseDao.listCZ(userKnowledgeBaseList);
			if(list.size()>0){
				errorMsg ="数据重复，重复的编号为";
				for(OldCourseDO spe : list){
					errorMsg += spe.getId()+",";
				}
				errorMsg +="数据库已存在,不能重复添加";
			}
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){

				this.saveBatch(userKnowledgeBaseList);
			for(OldCourseDO courseClassify : userKnowledgeBaseList){
				FieldDictDO fieldDict = new FieldDictDO();
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("pla_old_course_nzxyss");
				fieldDict.setFieldName("id");
				fieldDict.setFieldValue(courseClassify.getId()+"");
				fieldDict.setFieldMean(courseClassify.getName());
				fieldDictService.saveCache(fieldDict);
			}
			errorMsg = "导入成功，共" + userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

}