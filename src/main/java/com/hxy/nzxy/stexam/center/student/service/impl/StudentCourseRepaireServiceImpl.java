package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
import com.hxy.nzxy.stexam.center.student.dao.StudentCourseRepaireDao;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseRepaireService;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
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
public class StudentCourseRepaireServiceImpl implements StudentCourseRepaireService {
	@Autowired
	private StudentCourseRepaireDao studentCourseDao;
	
	@Override
	public StudentCourseDO get(Long id){
		return studentCourseDao.get(id);
	}
	
	@Override
	public List<StudentCourseDO> list(Map<String, Object> map){
		return studentCourseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseDao.count(map);
	}
	
	@Override
	public int save(StudentCourseDO studentCourse){
		return studentCourseDao.save(studentCourse);
	}
	
	@Override
	public int update(StudentCourseDO studentCourse){
		return studentCourseDao.update(studentCourse);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseDao.batchRemove(ids);
	}

	@Override
	public List<StudentCourseDO> listBkCourse(Query query) {
		return studentCourseDao.listBkCourse(query);
	}

	@Override
	public int countBkCourse(Query query) {
		return studentCourseDao.countBkCourse(query);
	}

	@Override
	public List<StudentCourseDO> listBkStudent(Query query) {
		return studentCourseDao.listBkStudent(query);
	}

	@Override
	public int countBkStudent(Query query) {
		return studentCourseDao.countBkStudent(query);
	}
	@Override
	public String batchImport(String fileName, MultipartFile file) {

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

	@Override
	public void saveBatch(List<StudentCourseDO> userKnowledgeBaseList) {
		studentCourseDao.saveBatch(userKnowledgeBaseList);
	}


	/**
	 * 解析Excel里面的数据
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile) throws ParseException {

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
		List<StudentCourseDO> userKnowledgeBaseList = new ArrayList<>();

		StudentCourseDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new StudentCourseDO();
			String studentid = "";
			String exam_taskid="";
			String courseid="";
			String exam_date="";
			String segment="";
			String type="";
			String arrange_status="";
			String nation = "";
			String status="";
			String regionid = "";
			String child_regionid = "";
			String speciality_recordid = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
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
					} else if (c == 2) {
						courseid = cell.getStringCellValue();
						if (StringUtils.isEmpty(courseid)) {
							rowMessage += "课程代码不能为空；";
						} else if (courseid.length() > 20) {
							rowMessage += "课程代码长度不能超过20；";
						}
					}  else if (c == 4) {
						exam_date= cell.getStringCellValue();
						if (StringUtils.isEmpty(exam_date)) {
							rowMessage += "考试日期不能为空；";
						} else if (exam_date.length() > 20) {
							rowMessage += "时段考试日期长度不能超过20；";
						}

					} else if (c == 5) {
						segment= cell.getStringCellValue();
						if (StringUtils.isEmpty(segment)) {
							rowMessage += "考试日期不能为空；";
						} else if (segment.length() > 20) {
							rowMessage += "时段考试日期长度不能超过20；";
						}

					} else if (c == 6) {
						type= cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "stu_student_course", "type", type);

						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空；";
						} else if (type.length() > 1) {
							rowMessage += "类别有误；";
						}
						tempUserKB.setType(type);

					} else if (c == 7) {
						regionid = cell.getStringCellValue();
						regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", regionid.substring(0,4));
						if (StringUtils.isEmpty(regionid)) {
							//
							rowMessage += "考试地州不能为空；";
						}
						if (regionid.length() > 20) {
							rowMessage += "考试地州长度过长；";
						}
						tempUserKB.setRegionid(Long.valueOf(regionid));
					} else if (c == 8) {
						child_regionid = cell.getStringCellValue();
						child_regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", child_regionid.substring(0,4));
						if (StringUtils.isEmpty(child_regionid)) {
							rowMessage += "考试县区不能为空；";
						} else if (child_regionid.length() > 20) {
							rowMessage += "考试县区长度过长；";
						}

						tempUserKB.setChildRegionid(Long.valueOf(child_regionid));
					} else if (c == 9) {
						arrange_status = cell.getStringCellValue();
						arrange_status = FieldDictUtil.get(Constant.APPID, "stu_student_course", "arrange_status", arrange_status);

						if (StringUtils.isEmpty(arrange_status)) {
							rowMessage += "编排状态不能为空；";
						} else if (nation.length() > 20) {
							rowMessage += "编排状态不能超过20；";
						}
						tempUserKB.setArrangeStatus(arrange_status);
					} else if (c == 10) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "stu_student_course", "status", status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						} else if (status.length() > 1) {
							rowMessage += "状态长度过长；";
						}
						tempUserKB.setStatus(status);
					}else if (c == 11) {
						exam_taskid= cell.getStringCellValue();
						if (StringUtils.isEmpty(exam_taskid)) {
							rowMessage += "考试任务不能为空；";
						} else if (exam_taskid.length() > 20) {
							rowMessage += "考试任务长度不能超过20；";
						}
					}
				}else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}
			}
			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				String year =exam_taskid.substring(0,exam_taskid.indexOf(" "));
				String month =exam_taskid.substring(exam_taskid.indexOf(" ")+1,exam_taskid.length());
				month=  FieldDictUtil.get(Constant.APPID, "exam_task", "exam_month", month);
				exam_taskid=  FieldDictUtil.get(Constant.APPID, "exam_task_nzxy", "exam_year_exam_month", year+month);
				String exam_courseid=  FieldDictUtil.get(Constant.APPID, "exa_exam_course_nzxy", "exam_taskid_courseid", exam_taskid+courseid);
				if(exam_courseid.equals("")){
					errorMsg+= br + "第" + (r + 1) + "行，" +"未找到对应的开考课程编号,请检查考试任务和课程编码";
				}else{

					tempUserKB.setExamCourseid(Long.valueOf(exam_courseid));

				}
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}
		//验证上传数据的身份证在数据库是否已存在
		if (StringUtils.isEmpty(errorMsg)) {
			List<StudentCourseDO> list = studentCourseDao.listCZ(userKnowledgeBaseList);
			if (list.size() > 0) {
				errorMsg = "导入失败，以下准考证号的课程信息已报考";
				for (StudentCourseDO stu : list) {
					errorMsg += "," + stu.getStudentid()+":"+stu;
				}
			}
		}
		//全部验证通过才导入到数据库
		if (StringUtils.isEmpty(errorMsg)) {
			this.saveBatch(userKnowledgeBaseList);

			errorMsg = "导入成功，共" + userKnowledgeBaseList.size() + "条数据！";
		}
		return errorMsg;
	}

}
