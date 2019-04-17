package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
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

import com.hxy.nzxy.stexam.center.student.dao.PracticeSchoolDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.center.student.service.PracticeSchoolService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PracticeSchoolServiceImpl implements PracticeSchoolService {
	@Autowired
	private PracticeSchoolDao practiceSchoolDao;
	
	@Override
	public PracticeSchoolDO get(Long id){
		return practiceSchoolDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolDO> list(Map<String, Object> map){
		return practiceSchoolDao.list(map);
	}
//	@Override
//	public List<PracticeSchoolDO> listT(Map<String, Object> map){
//		return practiceSchoolDao.listT(map);
//	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolDao.count(map);
	}

//	@Override
//	public int countT(Map<String, Object> map){
//		return practiceSchoolDao.countT(map);
//	}
	
	@Override
	public int save(PracticeSchoolDO practiceSchool){
		return practiceSchoolDao.save(practiceSchool);
	}

	@Override
	public int whetherinfo(String studentid){
		return practiceSchoolDao.whetherinfo(studentid);
	}
	
	@Override
	public int update(PracticeSchoolDO practiceSchool){
		return practiceSchoolDao.update(practiceSchool);
	}

	@Override
	public int RK(PracticeSchoolDO practiceSchool){
		return practiceSchoolDao.RK(practiceSchool);
	}

	@Override
	public int qxrk(List<PracticeSchoolDO> practiceSchool){
		return practiceSchoolDao.qxrk(practiceSchool);
	}
	@Override
	public int qxrkZS(List<PracticeSchoolDO> practiceSchool){
		return practiceSchoolDao.qxrkZS(practiceSchool);
	}

	@Override
	public int enabled(PracticeSchoolDO practiceSchool){
		return practiceSchoolDao.enabled(practiceSchool);
	}

	@Override
	public int remove(Long id){
		return practiceSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolDao.batchRemove(ids);
	}

	@Override
	public PracticeSchoolDO cannotRK(String studentid, String type){
		return practiceSchoolDao.cannotRK(studentid, type);
	}

	@Override
	public  int readyToRK(String studentid, String type, float grade){
		return practiceSchoolDao.readyToRK(studentid, type, grade);
	}

	@Override
	public  int deleteGrade(String studentid){
		return practiceSchoolDao.deleteGrade(studentid);
	}

	//实践成绩临时表批量导入
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
	 * @param wb Workbook的方式
	 * @param tempFile 临时文件
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile) throws ParseException {

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
		List<PracticeSchoolDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		PracticeSchoolDO tempUserKB;
		String br = "<br/>";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new PracticeSchoolDO();

			String   studentid               = "";
			String   speciality_recordid     ="";
			String   courseid	        ="";
			String   grade		   ="";
			String   in_operator	     ="";
			String   in_date		 ="";
			String   status		  ="";
			String   confirm_status	  ="";
			String   confirm_operator        ="";
			String   confirm_date	    ="";
			String   audit_status	    ="";
			String   audit_operator	  ="";
			String   audit_date	      ="";

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						studentid = cell.getStringCellValue();
						if (StringUtils.isEmpty(studentid)) {
							rowMessage += "准考证号不能为空；";
						} else if (studentid.length() > 20) {
							rowMessage += "准考证号的长度不能超过20；";
						}
						tempUserKB.setStudentid(studentid);
					}else if (c == 1) {
						speciality_recordid = cell.getStringCellValue();
						if (StringUtils.isEmpty(speciality_recordid)) {
							rowMessage += "专业开设编号不能为空；";
						}
						tempUserKB.setSpecialityRecordid(Long.valueOf(speciality_recordid));

					}else if (c == 2) {
						courseid = cell.getStringCellValue();
						if (StringUtils.isEmpty(courseid)) {
							rowMessage += "课程编号不能为空；";
						}else if (courseid.length() > 20){
							rowMessage += "课程编号的长度不能超过1";
						}
						tempUserKB.setCourseid(courseid);

					}else if (c == 3) {
						grade = cell.getStringCellValue();
						if (StringUtils.isEmpty(grade)) {
							rowMessage += "成绩不能为空；";
						}
						tempUserKB.setGrade(Float.parseFloat(grade));

					}else if (c == 4) {
						in_operator = cell.getStringCellValue();
						tempUserKB.setInOperator(in_operator);

					}else if (c == 5) {
						in_date = cell.getStringCellValue();
						in_date = DateUtils.format(in_date, DateUtils.DATE_PATTERN);

						tempUserKB.setInDate(sdf.parse(in_date));

					}else if (c == 6) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "stu_practice_school", "status", status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "入库状态不能为空；";
						}else if (status.length() > 1){
							rowMessage += "入库状态的长度不能超过1";
						}
						tempUserKB.setStatus(status);

					}else if (c == 7) {
						confirm_status = cell.getStringCellValue();
						confirm_status = FieldDictUtil.get(Constant.APPID, "stu_practice_school", "confirm_status", confirm_status);
						if (StringUtils.isEmpty(confirm_status)) {
							rowMessage += "确认状态不能为空；";
						}else if (confirm_status.length() > 1){
							rowMessage += "确认状态的长度不能超过1";
						}
						tempUserKB.setConfirmStatus(confirm_status);

					}else if (c == 8) {
						confirm_operator = cell.getStringCellValue();
						tempUserKB.setConfirmOperator(Long.parseLong(confirm_operator));
					}else if (c == 9) {
						confirm_date = cell.getStringCellValue();
						confirm_date = DateUtils.format(confirm_date, DateUtils.DATE_PATTERN);
						tempUserKB.setConfirmDate(sdf.parse(confirm_date));

					}else if (c == 10) {
						audit_status = cell.getStringCellValue();
						audit_status = FieldDictUtil.get(Constant.APPID, "stu_practice_school", "audit_status", audit_status);
						if (StringUtils.isEmpty(audit_status)) {
							rowMessage += "审核状态不能为空；";
						}else if (audit_status.length() > 1){
							rowMessage += "审核状态的长度不能超过1";
						}
						tempUserKB.setAuditStatus(audit_status);

					}else if (c == 11) {
						audit_operator = cell.getStringCellValue();

						tempUserKB.setAuditOperator(Long.parseLong(audit_operator));

					}else if (c == 12) {
						audit_date = cell.getStringCellValue();
						audit_date = DateUtils.format(audit_date, DateUtils.DATE_PATTERN);

						tempUserKB.setAuditDate(sdf.parse(audit_date));

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

		//验证上传数据在数据库是否已存在
		List<PracticeSchoolDO> list=practiceSchoolDao.listL(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，准考证号：";

			for(PracticeSchoolDO spe : list){
				errorMsg += spe.getStudentid()+",";
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(PracticeSchoolDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

	@Override
	public int isBK(String studentid, String courseid){
		return practiceSchoolDao.isBK(studentid,courseid);
	}
}
