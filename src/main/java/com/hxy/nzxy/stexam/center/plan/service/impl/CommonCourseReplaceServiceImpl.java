package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CommonCourseReplaceDao;
import com.hxy.nzxy.stexam.center.plan.service.CommonCourseReplaceService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import com.hxy.nzxy.stexam.domain.CourseFreeCenterDO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CommonCourseReplaceServiceImpl implements CommonCourseReplaceService {
	@Autowired
	private CommonCourseReplaceDao commonCourseReplaceDao;
	
	@Override
	public CommonCourseReplaceDO get(Long id){
		return commonCourseReplaceDao.get(id);
	}
	
	@Override
	public List<CommonCourseReplaceDO> list(Map<String, Object> map){
		return commonCourseReplaceDao.list(map);
	}

	@Override
	public List<CommonCourseReplaceDO> listOther(Map<String, Object> map) {
		return commonCourseReplaceDao.listOther(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return commonCourseReplaceDao.count(map);
	}
	
	@Override
	public int save(CommonCourseReplaceDO commonCourseReplace){
		return commonCourseReplaceDao.save(commonCourseReplace);
	}

	public int tySave(CommonCourseReplaceDO commonCourseReplace){
		return commonCourseReplaceDao.tySave(commonCourseReplace);
	}
	@Override
	public int update(CommonCourseReplaceDO commonCourseReplace){
		return commonCourseReplaceDao.update(commonCourseReplace);
	}
	
	@Override
	public int remove(Long id){
		return commonCourseReplaceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return commonCourseReplaceDao.batchRemove(ids);
	}

	@Override
	public String getFatherCourse(String courseReplaceId){
		Long id = Long.parseLong(courseReplaceId);
		return commonCourseReplaceDao.getCourseid(id);
	}
	//免考课程规则批量导入
	@Override
	public String TYbatchImport(String fileName, MultipartFile file){


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
		List<CommonCourseReplaceDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		CommonCourseReplaceDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new CommonCourseReplaceDO();

			String courseid = "";
			String flag ="";
			String course_class ="";
			String type ="";
			String course_num ="";
			String least_num ="";
			String least_score ="";
			String append_courseid1 ="";
			String append_courseid2 ="";
			String append_courseid3 ="";
			String append_courseid4 ="";
			String speciality_class ="";

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						courseid = cell.getStringCellValue();
						String names = courseid.substring(0,5);
						if (StringUtils.isEmpty(names)) {
							rowMessage += "课程名称不能为空；";
						} else if (courseid.length() > 200) {
							rowMessage += "课程名称的长度不能超过200；";
						}
						tempUserKB.setCourseid(names);
					} else if (c == 1) {
						flag = cell.getStringCellValue();
						flag = FieldDictUtil.get(Constant.APPID, "pla_course_replace", "flag", flag);
						if (StringUtils.isEmpty(flag)) {
							rowMessage += "顶替类别不能为空；";
						} else if (flag.length() > 2) {
							rowMessage += "顶替类别的长度不能超过1；";
						}
						tempUserKB.setFlag(flag);

					} else if (c == 2) {
						course_class = cell.getStringCellValue();
						course_class = FieldDictUtil.get(Constant.APPID, "pla_course_replace", "course_class", course_class);
						if (StringUtils.isEmpty(course_class)) {
							rowMessage += "课程层次不能为空；";
						}else if (course_class.length() > 2){
							rowMessage += "课程层次的长度不能超过1";
						}
						tempUserKB.setCourseClass(course_class);

					} else if (c == 3) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "pla_course_replace", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空,或未找到对应的类别；";
						}
						tempUserKB.setType(type);
					}else if (c == 4) {
						course_num = cell.getStringCellValue();

						tempUserKB.setCourseNum(Integer.parseInt(course_num));
					}else if (c == 5) {
						least_num = cell.getStringCellValue();

						tempUserKB.setLeastNum(Integer.parseInt(least_num));
					}else if (c == 6) {
						least_score = cell.getStringCellValue();

						tempUserKB.setLeastScore(Integer.parseInt(least_score));
					}else if (c == 7) {
						append_courseid1 = cell.getStringCellValue();
						if(append_courseid1 != "" || "".equals(append_courseid1)){
							append_courseid1 = FieldDictUtil.get(Constant.APPID, "pla_course_nzxy", "id", append_courseid1);
							if (StringUtils.isEmpty(append_courseid1)) {
								tempUserKB.setAppendCourseid1(append_courseid1);
							}
						}
					}else if (c == 8) {
						append_courseid2 = cell.getStringCellValue();
						if(append_courseid1 != "" || "".equals(append_courseid1)){
							append_courseid2 = FieldDictUtil.get(Constant.APPID, "pla_course_nzxy", "id", append_courseid2);
							if (StringUtils.isEmpty(append_courseid2)) {
								tempUserKB.setAppendCourseid1(append_courseid2);
							}
						}

					}else if (c == 9) {
						append_courseid3 = cell.getStringCellValue();
						if(append_courseid1 != "" || "".equals(append_courseid1)){
							append_courseid3 = FieldDictUtil.get(Constant.APPID, "pla_course_nzxy", "id", append_courseid3);
							if (StringUtils.isEmpty(append_courseid3)) {
								tempUserKB.setAppendCourseid1(append_courseid3);
							}
						}

					}else if (c == 10) {
						append_courseid4 = cell.getStringCellValue();
						if(append_courseid1 != "" || "".equals(append_courseid1)){
							append_courseid4 = FieldDictUtil.get(Constant.APPID, "pla_course_nzxy", "id", append_courseid4);
							if (StringUtils.isEmpty(append_courseid4)) {
								tempUserKB.setAppendCourseid1(append_courseid4);
							}
						}

					}else if (c == 11) {
						speciality_class = cell.getStringCellValue();

						speciality_class = FieldDictUtil.get(Constant.APPID, "pla_speciality_record", "type", speciality_class);
						if (StringUtils.isEmpty(speciality_class)) {
							rowMessage += "专业类别不能为空,或未找到对应的类别；";
						}
						tempUserKB.setSpecialityClass(speciality_class);
					}
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
		if(tempFile.exists()){
			tempFile.delete();
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(CommonCourseReplaceDO courseClassify : userKnowledgeBaseList){
				this.tySave(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}


}
