package com.hxy.nzxy.stexam.school.student.service.impl;

import com.google.common.base.Supplier;
import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hxy.nzxy.stexam.school.student.dao.StudentCourseStudentDao;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseStudentService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Service
public class StudentCourseStudentServiceImpl implements StudentCourseStudentService {
	@Autowired
	private StudentCourseStudentDao studentCourseStudentDao;
	@Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private ExamCourseDao examCourseDao;

	@Override
	public StudentCourseDO get(Long id){
		return studentCourseStudentDao.get(id);
	}
	
	@Override
	public List<StudentCourseDO> list(Map<String, Object> map){
		return studentCourseStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCourseDO studentCourseStudent){
		return studentCourseStudentDao.save(studentCourseStudent);
	}
	
	@Override
	public int update(StudentCourseDO studentCourseStudent){
		return studentCourseStudentDao.update(studentCourseStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseStudentDao.batchRemove(ids);
	}

	@Override
	public List<TaskDO> listTask(Map<String, Object> params) {
		return studentCourseStudentDao.listTask(params);
	}

	@Override
	public List<ExamCourseDO> listCourse(Query query) {
		return studentCourseStudentDao.listCourse(query);
	}

	@Override
	public int listCourseCount(Query query) {
		return studentCourseStudentDao.listCourseCount(query);
	}



	@Override
	public List<StudentCourseDO> saveCourse(String stuList, String courseList, String teachid, HttpServletRequest request) {
		//学生信息
		List<StudentSpecialityDO> studentSpeciality = JSONUtils.readListValue(stuList, StudentSpecialityDO.class);
		//考试课程
		List<ExamCourseDO> examCourse = JSONUtils.readListValue(courseList, ExamCourseDO.class);
		//学生信息
		List<String> studentidList = studentSpeciality.stream().map(StudentSpecialityDO::getStudentid).collect(Collectors.toList());
		//开考课程编号
		List<Long>  examCourseList = examCourse.stream().map(ExamCourseDO::getId).collect(Collectors.toList());
		//课程编号
		List<String>  courseList1 = examCourse.stream().map(ExamCourseDO::getCourseid).collect(Collectors.toList());


		//查询已经报考的学生准考证号和开考课程编号
		Map map=new HashMap();
		map.put("studentidList",studentidList);
		map.put("examCourseList",examCourseList);
		//查询同一时间有考试的学生
		List<StudentCourseDO> studentCourseSameTimeList =studentCourseStudentDao.selectstudentSameTime(map);

		//取出考生已经报考的学生+原因 已经报考
		List<StudentCourseDO> studentCourseList =studentCourseStudentDao.selectstudentCourse(map);
		//根据开考课程编号取出对应的课程编号
		//List<ExamCourseDO> examCourseDOS = examCourseDao.listByids(examCourseList);
		//List<Long>  courseList1 = examCourseDOS.stream().map(ExamCourseDO::getId).collect(Collectors.toList());
		//查询已经有成绩的学生根据准考证号和课程编号
		Map map1=new HashMap();
		map1.put("studentidList",studentidList);
		map1.put("courseList1",courseList1);
		//取出已经有成绩的学生+原因 已考试
		List<StudentCourseDO> studentCjCourseList =studentCourseStudentDao.selectstudentCjCourse(map1);

		//本次没有报考的学生
		List<StudentCourseDO> ListNoExam=new ArrayList<>();
		ListNoExam.addAll(studentCourseList);
		ListNoExam.addAll(studentCjCourseList);
		ListNoExam.addAll(studentCourseSameTimeList);
		//考生数据报考,去掉已经报考+已经有成绩的学生
		//查询出考试考区编号 考试区县编号 获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(Long.valueOf(teachid));
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		//真正需要提交报考(排除掉已经报考的,和已经有成绩的，同一时间有报考课程的学生,报考课程)
		List<StudentCourseDO> list =new ArrayList<>();
		//所有学生要报考信息
		List<StudentCourseDO> list1 =new ArrayList<>();
		studentidList.forEach(student -> {
			examCourse.forEach(examCou -> {
				StudentCourseDO  studentCourse =new StudentCourseDO();
				studentCourse.setChildRegionid(schoolSiteDO.getRegionid());
				studentCourse.setRegionid(regionDO.getParentid());
				studentCourse.setType("b");
				studentCourse.setStatus("a");
				studentCourse.setArrangeStatus("a");
				studentCourse.setOperator(ShiroUtils.getUserId().toString());
				studentCourse.setStudentid(student);
				studentCourse.setExamCourseid(examCou.getId());
				studentCourse.setCourseid(examCou.getCourseid());
				list.add(studentCourse);
				list1.add(studentCourse);
					});
		});

		list1.stream().forEach(i->{
			//去掉已经报考的学生数据
			studentCourseList.stream().forEach(y->{
				if(i.getStudentid().equals(y.getStudentid())&&i.getExamCourseid().equals(y.getExamCourseid())){
					list.remove(i);
				}
			}
			);
			//去掉已有成绩学生数据
			studentCjCourseList.stream().forEach(z->{
						if(i.getStudentid().equals(z.getStudentid())&&i.getCourseid().equals(z.getCourseid())){
							list.remove(i);
						}
					}
			);
			//去掉同一时间有报考记录学生数据
			studentCourseSameTimeList.stream().forEach(z->{
						if(i.getStudentid().equals(z.getStudentid())&&i.getExamCourseid().equals(z.getExamCourseid())){
							list.remove(i);
						}
					}
			);
		});
		if(list.size()>0){
			studentCourseStudentDao.batchSave(list);
			request.setAttribute("StudentBk","success");
		}else{
			request.setAttribute("StudentBk","unSuccess");
		}
		return ListNoExam;
	}

	@Override
	public String batchImport(String fileName, MultipartFile file,HttpServletRequest request) {

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
			return readExcelValue(wb,tempFile,request);
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
		studentCourseStudentDao.saveBatch(userKnowledgeBaseList);
	}


	/**
	 * 解析Excel里面的数据
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile, HttpServletRequest request) throws ParseException {

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
			String courseid="";
			String exam_taskid="";
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
						tempUserKB.setCourseid(courseid);
					}  else if (c == 4) {
						exam_taskid= cell.getStringCellValue();
						if (StringUtils.isEmpty(exam_taskid)) {
							rowMessage += "考试任务不能为空；";
						} else if (exam_taskid.length() > 20) {
							rowMessage += "考试任务长度不能超过20；";
						}

					}  else if (c == 5) {
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
					} else if (c == 6) {
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
				if(exam_taskid.equals("")){
					errorMsg+= br + "第" + (r + 1) + "行，" +"未找到对应的考试任务";
				}
				 String exam_courseid=  FieldDictUtil.get(Constant.APPID, "exa_exam_course_nzxy", "exam_taskid_courseid", exam_taskid+courseid);
				if(exam_courseid.equals("")){
					errorMsg+= br + "第" + (r + 1) + "行，" +"未找到对应开考课程编号,请检查考试任务和课程编码";
				}else{
					tempUserKB.setType("b");
					tempUserKB.setExamCourseid(Long.valueOf(exam_courseid));
				}
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}
		//验证上传数据的数据库是否已存在
		if (StringUtils.isEmpty(errorMsg)) {
			//List<StudentCourseDO> list = studentCourseStudentDao.listCZ(userKnowledgeBaseList);
			//学生信息
			List<String> studentidList = userKnowledgeBaseList.stream().map(StudentCourseDO::getStudentid).collect(Collectors.toList());
			//开考课程编号
			List<Long>  examCourseList = userKnowledgeBaseList.stream().map(StudentCourseDO::getExamCourseid).collect(Collectors.toList());
			//课程编号
			List<String>  courseList1 = userKnowledgeBaseList.stream().map(StudentCourseDO::getCourseid).collect(Collectors.toList());
			Map map=new HashMap();
			map.put("studentidList",studentidList);
			map.put("examCourseList",examCourseList);
			//查询同一时间有考试的学生
			List<StudentCourseDO> studentCourseSameTimeList =studentCourseStudentDao.selectstudentSameTime(map);
			//取出考生已经报考的学生+原因 已经报考
			List<StudentCourseDO> studentCourseList =studentCourseStudentDao.selectstudentCourse(map);
			//根据开考课程编号取出对应的课程编号

			//查询已经有成绩的学生根据准考证号和课程编号
			Map map1=new HashMap();
			map1.put("studentidList",studentidList);
			map1.put("courseList1",courseList1);
			//取出已经有成绩的学生+原因 已考试
			List<StudentCourseDO> studentCjCourseList =studentCourseStudentDao.selectstudentCjCourse(map1);

			//本次没有报考的学生
			List<StudentCourseDO> ListNoExam=new ArrayList<>();
			ListNoExam.addAll(studentCourseList);
			ListNoExam.addAll(studentCjCourseList);
			request.getSession().setAttribute("studentNoExamList",ListNoExam);
			//考生数据报考,去掉已经报考+已经有成绩的学生
			//真正需要提交报考(排除掉已经报考的,和已经有成绩的学生,报考课程)
			List<StudentCourseDO> list =new ArrayList<>();
			//所有学生要报考信息
			List<StudentCourseDO> list1 =new ArrayList<>();
			studentidList.forEach(student -> {
				userKnowledgeBaseList.forEach(examCou -> {
					StudentCourseDO  studentCourse =new StudentCourseDO();
					studentCourse.setChildRegionid(examCou.getChildRegionid());
					studentCourse.setRegionid(examCou.getRegionid());
					studentCourse.setType("b");
					studentCourse.setStatus("a");
					studentCourse.setArrangeStatus("a");
					studentCourse.setOperator(ShiroUtils.getUserId().toString());
					studentCourse.setStudentid(student);
					studentCourse.setExamCourseid(examCou.getExamCourseid());
					studentCourse.setCourseid(examCou.getCourseid());
					list.add(studentCourse);
					list1.add(studentCourse);
				});
			});
			list1.stream().forEach(i->{
				//去掉已经报考的学生数据
				studentCourseList.stream().forEach(y->{
							if(i.getStudentid().equals(y.getStudentid())&&i.getExamCourseid().equals(y.getExamCourseid())){
								list.remove(i);
							}
						}
				);
				//去掉已有成绩学生数据
				studentCjCourseList.stream().forEach(z->{
							if(i.getStudentid().equals(z.getStudentid())&&i.getCourseid().equals(z.getCourseid())){
								list.remove(i);
							}
						}
				);
				//去掉同一时间有报考记录学生数据
				studentCourseSameTimeList.stream().forEach(z->{
							if(i.getStudentid().equals(z.getStudentid())&&i.getExamCourseid().equals(z.getExamCourseid())){
								list.remove(i);
							}
						}
				);
			});
			if(list.size()>0){
				studentCourseStudentDao.batchSave(list);
				errorMsg = "导入成功，共" + list.size() + "条数据！";
				request.setAttribute("StudentBk","success");
			}else{
				errorMsg = "没有符合要求的导入数据！";
				request.setAttribute("StudentBk","unSuccess");
			}
		}
		//全部验证通过才导入到数据库

		return errorMsg;
	}

}
