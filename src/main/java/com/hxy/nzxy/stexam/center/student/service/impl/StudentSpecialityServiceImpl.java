package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSpecialityRegDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.center.student.dao.ScoreDao;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
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

import com.hxy.nzxy.stexam.center.student.dao.StudentSpecialityDao;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentSpecialityServiceImpl implements StudentSpecialityService {
	@Autowired
	private StudentSpecialityDao studentSpecialityDao;
	@Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private SchoolSpecialityRegDao schoolSpecialityRegDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Autowired
	private ScoreDao scoreDao;
	@Override
	public StudentSpecialityDO get(Long id){
		return studentSpecialityDao.get(id);
	}
	@Override
	public StudentSpecialityDO selectStudentInfo(Long id){
		return studentSpecialityDao.selectStudentInfo(id);
	}
	@Override
	public StudentSpecialityDO selectStudentSpeciality(String studentid){
		return studentSpecialityDao.selectStudentSpeciality(studentid);
	}
	
	@Override
	public List<StudentSpecialityDO> list(Map<String, Object> map){
		return studentSpecialityDao.list(map);
	}

	@Override
	public List<StudentSpecialityDO> listStudent(Map<String, Object> map){
		return studentSpecialityDao.listStudent(map);
	}

	@Override
	public List<StudentSpecialityDO> selectAuditStudent(Long[] ids){
		return studentSpecialityDao.selectAuditStudent(ids);
	}

	@Override
	public float getSourceCourse(String courseid,String specialityRecordid,String studentid){
		return studentSpecialityDao.getSourceCourse( courseid, specialityRecordid, studentid);
	}

	@Override
	public List<StudentSpecialityDO> listQu(Map<String, Object> map){
		return studentSpecialityDao.listQu(map);
	}

	@Override
	public List<StudentSpecialityDO> listCourseScore(Map<String, Object> map){
		return studentSpecialityDao.listCourseScore(map);
	}

	@Override
	public List<CourseReplaceDO> listCourseReplace(String specialityRecordid){
		return studentSpecialityDao.listCourseReplace(specialityRecordid);
	}



	@Override
	public List<CourseAppendDO> listCourseAppend(String specialityRecordid, List<StudentSpecialityDO> studentStudentList){
		return studentSpecialityDao.listCourseAppend(specialityRecordid,studentStudentList);
	}

	@Override
	public List<CourseAppendItemDO> listCourseAppendItem(List<CourseAppendDO> courseReplaceList){
		return studentSpecialityDao.listCourseAppendItem(courseReplaceList);
	}

	@Override
	public List<CourseCheckDO> listCourseCheck(String specialityRecordid, List<StudentSpecialityDO> studentStudentList){
		return studentSpecialityDao.listCourseCheck(specialityRecordid,studentStudentList);
	}

	@Override
	public List<CourseCheckDO> listCourseCheckItem(Map<String, Object> map){
		return studentSpecialityDao.listCourseCheckItem(map);
	}

	@Override
	public List<ScoreDO> listScore(Map<String, Object> map){
		return studentSpecialityDao.listScore(map);
	}
	@Override
	public List<ScoreDO> listScoreReplace(Map<String, Object> map){
		return studentSpecialityDao.listScoreReplace(map);
	}
	@Override
	public List<ScoreDO> applyExcelScore(String studentid,String specialityRecordid){
		return studentSpecialityDao.applyExcelScore(studentid,specialityRecordid);
	}

	@Override
	public List<StudentSpecialityDO> seachStuSubjectlist(Map<String, Object> map) {
		return studentSpecialityDao.seachStuSubjectlist(map);
	}
	@Override
	public List<CommonCourseReplaceDO> getCommonCourseReplace(Map<String, Object> map) {
		return studentSpecialityDao.getCommonCourseReplace(map);
	}
	@Override
	public List<CommonCourseReplaceItemDO> getItemList(List<CommonCourseReplaceDO> studentStudentList) {
		return studentSpecialityDao.getItemList(studentStudentList);
	}

	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityDao.count(map);
	}

	@Override
	public int countStudent(Map<String, Object> map){
		return studentSpecialityDao.countStudent(map);
	}

	@Override
	public int updateAuditStudent(Long ids, String gradAuditStatus, String gradAuditOperator){
		return studentSpecialityDao.updateAuditStudent(ids,gradAuditStatus,gradAuditOperator);
	}

	@Override
	public int updateGradute(Long ids, String gradAuditOperator){
		return studentSpecialityDao.updateGradute(ids, gradAuditOperator);
	}

	@Override
	public int countQu(Map<String, Object> map){
		return studentSpecialityDao.countQu(map);
	}

	@Override
	public int selectCommonReolace(String courseid1,String courseid2){
		return studentSpecialityDao.selectCommonReolace(courseid1,courseid2);
	}

	@Override
	public int selectCourseReplace(String courseid1,String courseid2, String specialityRecordid){
		return studentSpecialityDao.selectCourseReplace(courseid1,courseid2,specialityRecordid);
	}

	@Override
	public int selectCourseAppend(String courseid1,String courseid2,String specialityRecordid){
		return studentSpecialityDao.selectCourseAppend(courseid1,courseid2,specialityRecordid);
	}

	@Override
	public int selectCourseCheck(String courseid1,String courseid2){
		return studentSpecialityDao.selectCourseCheck(courseid1,courseid2);
	}

	@Override
	public int getInfomation(String studentid,String specialityRecordid,String graduate){
		return studentSpecialityDao.getInfomation(studentid,specialityRecordid,graduate);
	}

	@Override
	public StudentSpecialityDO selectInfomation(String studentid,String specialityRecordid){
		return studentSpecialityDao.selectInfomation(studentid,specialityRecordid);
	}

	@Override
	public List<StudentSpecialityDO> listStudentSpeciality(String studentid){
		return studentSpecialityDao.listStudentSpeciality(studentid);
	}

	@Override
	public int save(StudentSpecialityDO studentSpeciality){
		//获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(studentSpeciality.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentSpeciality.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentSpeciality.setRegionid(regionDO.getParentid());
		studentSpeciality.setStatus("a");
		studentSpeciality.setAuditStatus("a");
		studentSpeciality.setGradAuditStatus("a");
		//获取招生年份和季节; school_speciality_regid
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpeciality.getSchoolSpecialityRegid());
		//专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpeciality.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpeciality.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		if(schoolSpecialityRegDO!=null){
			studentSpeciality.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpeciality.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}

		return studentSpecialityDao.save(studentSpeciality);
	}
	
	@Override
	public int update(StudentSpecialityDO studentSpeciality){
		return studentSpecialityDao.update(studentSpeciality);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityDao.remove(id);
	}

	@Override
	public int editStudentInfo(Map<String, Object> map){
		return studentSpecialityDao.editStudentInfo(map);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityDao.batchRemove(ids);
	}

	@Override
	public int batchUpdateAudit(Long[] ids, String auditStatus, String operator) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("auditStatus",auditStatus);
		map.put("operator",operator);
		return studentSpecialityDao.batchUpdateAudit(map);
	}

	@Override
	public int updateAudit(Map<String, Object> params) {
		return studentSpecialityDao.updateAudit(params);
	}
	@Override
	public int updateScore(Map<String, Object> params) {
		return studentSpecialityDao.updateScore(params);
	}
	@Override
	public int updateScore1(Map<String, Object> params) {
		int i = studentSpecialityDao.updateScore1(params);
		if(i==0){
		ScoreDO score=	new ScoreDO();
			score.setStudentid(params.get("studentid").toString());
			score.setSpecialityRecordid(Long.valueOf(params.get("specialityRecordid").toString()));
			score.setCourseid(params.get("courseid2").toString());
			score.setSourceCourseid(Long.valueOf(params.get("courseid1").toString()));
			score.setType("g");
			score.setExamFlag("0");
			score.setStatus("0");
			score.setUseStatus("b");
			score.setFlag("a");
			score.setGrade(Float.valueOf(params.get("score1").toString()));
			score.setOldGrade(Float.valueOf(params.get("score2").toString()));
			scoreDao.save(score);
		}
		return 1;
	}
	@Override
	public int updateScoreBack(Map<String, Object> params) {
		return studentSpecialityDao.updateScoreBack(params);
	}
	@Override
	public int updateScoreBack1(Map<String, Object> params) {
		return studentSpecialityDao.updateScoreBack1(params);
	}
	@Override
	public int editDoubleRight(String courseid,String specialityRecordid,String studentid,String type) {
		if(type.equals("a")){
			specialityRecordid=null;
		}
		return studentSpecialityDao.editDoubleRight(courseid, specialityRecordid, studentid,type);
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
	public void saveBatch(List<StudentSpecialityDO> userKnowledgeBaseList) {
		studentSpecialityDao.saveBatch(userKnowledgeBaseList);
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
		List<StudentSpecialityDO> userKnowledgeBaseList = new ArrayList<>();

		StudentSpecialityDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new StudentSpecialityDO();

			String studentid = "";
			String specialityid="";
			String specialityName="";
			String education="";
			String grad_school="";
			String grad_certificate="";
			String grad_specialityid="";
			String schoolid = "";
			String collegeid = "";
			String teach_siteid = "";
			String nation = "";
			String reg_year = "";
			String status="";
			String regionid = "";
			String child_regionid = "";
			String reg_season = "";
			String audit_status = "";
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
					} else if (c == 1) {
						specialityid = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityid)) {
							rowMessage += "专业不能为空；";
						} else if (specialityid.length() > 20) {
							rowMessage += "专业的长度不能超过20；";
						}
						tempUserKB.setSpecialityid(specialityid);
					} else if (c == 2) {
						specialityName = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityName)) {
							rowMessage += "专业名称不能为空；";
						} else if (specialityName.length() > 50) {
							rowMessage += "专业名称长度不能超过50；";
						}
					} else if (c == 3) {

					} else if (c == 4) {

					} else if (c == 5) {

					} else if (c == 6) {

					} else if (c == 7) {
						education = cell.getStringCellValue();
						education = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "education", education);
						if (StringUtils.isEmpty(education)) {
							//
							rowMessage += "原学历不能为空；";
						}
						if (education.length() > 1) {
							rowMessage += "原学历的长度过长；";
						}
						tempUserKB.setEducation(education);
					} else if (c == 8) {
						grad_school = cell.getStringCellValue();
						if (StringUtils.isEmpty(grad_school)) {
							//
						} else if (grad_school.length() > 20) {
							rowMessage += "毕业学校的长度不能超过20；";
						}

						tempUserKB.setGradSchool(grad_school);
					} else if (c == 9) {
						grad_certificate = cell.getStringCellValue();
						if (StringUtils.isEmpty(grad_certificate)) {

						} else if (nation.length() > 20) {
							rowMessage += "毕业证书号不能超过20；";
						}
						tempUserKB.setGradCertificate(grad_certificate);
					} else if (c == 10) {
						grad_specialityid = cell.getStringCellValue();
						grad_specialityid = FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", grad_specialityid);
						if (StringUtils.isEmpty(grad_specialityid)) {
						} else if (grad_specialityid.length() > 20) {
							rowMessage += "原学专业；";
						}
						tempUserKB.setGradSpecialityid(grad_specialityid);
					} else if (c == 11) {
						regionid = cell.getStringCellValue();
						if (StringUtils.isEmpty(regionid)) {
							rowMessage += "报考地市不能为空；";
						}
						 else if (regionid.length() > 10) {
							rowMessage += "报考地市长度不能超过10；";
						} else {
							regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", regionid.substring(0,4));
							tempUserKB.setRegionid(Long.valueOf(regionid));
						}
					} else if (c == 12) {
						child_regionid = cell.getStringCellValue();
						if (StringUtils.isEmpty(child_regionid)) {
							rowMessage += "报考县区不能为空；";
						} else if (child_regionid.length() > 10) {
							rowMessage += "报考县区长度不能超过10；";
						};
						child_regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", child_regionid.substring(0,4));
						tempUserKB.setChildRegionid(Long.valueOf(child_regionid));
					} else if (c == 13) {
						schoolid = cell.getStringCellValue();
						if (StringUtils.isEmpty(schoolid)) {
							rowMessage += "组织不能为空；";
						} ;
						schoolid = FieldDictUtil.get(Constant.APPID, "sch_school_nzxy", "code", schoolid.substring(0,4));
						tempUserKB.setSchoolid(Long.valueOf(schoolid));
					}else if (c == 14) {
						collegeid = cell.getStringCellValue();
						if (StringUtils.isEmpty(collegeid)) {
							//学院
							rowMessage += "学院不能为空；";
						};
						collegeid = FieldDictUtil.get(Constant.APPID, "sch_college_nzxy", "code_name", schoolid+collegeid);
						tempUserKB.setCollegeid(Long.valueOf(collegeid));
					}else if (c == 15) {
						teach_siteid = cell.getStringCellValue();
						if (StringUtils.isEmpty(teach_siteid)) {
							//教学点
							rowMessage += "教学点不能为空；";
						};
						teach_siteid = FieldDictUtil.get(Constant.APPID, "sch_teach_site_nzxy", "code_name", collegeid+teach_siteid);
						tempUserKB.setTeachSiteid(Long.valueOf(teach_siteid));
					}else if (c == 16) {
						reg_year = cell.getStringCellValue();
						if (StringUtils.isEmpty(reg_year)) {
							//教学点
							rowMessage += "招生年份不能为空；";
						};
						tempUserKB.setRegYear(reg_year);
					}else if (c == 17) {
						reg_season = cell.getStringCellValue();
						if (StringUtils.isEmpty(teach_siteid)) {
							//教学点
							rowMessage += "招生季节不能为空；";
						};
						reg_season = FieldDictUtil.get(Constant.APPID, "sch_school_speciality_reg", "reg_season", reg_season);
						tempUserKB.setRegSeason(reg_season);
					}else if (c == 18) {
						audit_status = cell.getStringCellValue();
						if (StringUtils.isEmpty(audit_status)) {
							//教学点
							rowMessage += "审核状态不能为空；";
						};
						audit_status = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "audit_status", audit_status);
						tempUserKB.setAuditStatus(audit_status);
					}else if (c == 19) {
						status = cell.getStringCellValue();
						if (StringUtils.isEmpty(status)) {
							//教学点
							rowMessage += "考生状态不能为空；";
						};
						status = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "status", status);
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
				String specialityName1= FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", specialityid);
				String direction=specialityName.substring(specialityName1.length(),specialityName.length());
				speciality_recordid = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy", "specialityid_direction", specialityid+direction);
				if(StringUtils.isEmpty(speciality_recordid)){
					errorMsg+= br + "第" + (r + 1) + "行，" +"未找到对应专业开设编号";
				}else{
					tempUserKB.setSpecialityRecordid(Long.valueOf(speciality_recordid));
				}
				tempUserKB.setGradAuditStatus("a");
				userKnowledgeBaseList.add(tempUserKB);
			}
		}
		//删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}
		//验证上传数据的身份证在数据库是否已存在
		if (StringUtils.isEmpty(errorMsg)) {
			List<StudentSpecialityDO> list = studentSpecialityDao.listCZ(userKnowledgeBaseList);
			if (list.size() > 0) {
				errorMsg = "导入失败，以下准考证号的信息已存在相同专业的信息,不能重复添加";
				for (StudentSpecialityDO stu : list) {
					errorMsg += "," + stu.getStudentid();
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



	/**
	 * 解析Excel里面的数据
	 * @param wb
	 * @return
	 */
	private String readExcelValueKaoWu(Workbook wb,File tempFile) throws ParseException {

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
		List<StudentSpecialityDO> userKnowledgeBaseList = new ArrayList<>();

		StudentSpecialityDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new StudentSpecialityDO();

			String studentid = "";
			String specialityid="";
			String specialityName="";
			String education="";
			String grad_school="";
			String grad_certificate="";
			String grad_specialityid="";
			String nation = "";
			String status="";
			String regionid = "";
			String child_regionid = "";
			String audit_status = "";
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
					} else if (c == 5) {
						specialityid = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityid)) {
							rowMessage += "专业不能为空；";
						} else if (specialityid.length() > 20) {
							rowMessage += "专业的长度不能超过20；";
						}
						tempUserKB.setSpecialityid(specialityid);
					} else if (c == 6
							) {
						specialityName = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityName)) {
							rowMessage += "专业名称不能为空；";
						} else if (specialityName.length() > 50) {
							rowMessage += "专业名称长度不能超过50；";
						}
					}  else if (c == 4) {
						education = cell.getStringCellValue();
						education = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "education", education);
						if (StringUtils.isEmpty(education)) {
							//
							rowMessage += "原学历不能为空；";
						}
						if (education.length() > 1) {
							rowMessage += "原学历的长度过长；";
						}
						tempUserKB.setEducation(education);
					} else if (c == 1) {
						grad_school = cell.getStringCellValue();
						if (StringUtils.isEmpty(grad_school)) {
							//
						} else if (grad_school.length() > 20) {
							rowMessage += "毕业学校的长度不能超过20；";
						}
						tempUserKB.setGradSchool(grad_school);
					} else if (c == 2) {
						grad_certificate = cell.getStringCellValue();
						if (StringUtils.isEmpty(grad_certificate)) {

						} else if (nation.length() > 20) {
							rowMessage += "毕业证书号不能超过20；";
						}
						tempUserKB.setGradCertificate(grad_certificate);
					} else if (c == 3) {
						grad_specialityid = cell.getStringCellValue();
						grad_specialityid = FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", grad_specialityid);
						if (StringUtils.isEmpty(grad_specialityid)) {
						} else if (grad_specialityid.length() > 20) {
							rowMessage += "原学专业；";
						}
						tempUserKB.setGradSpecialityid(grad_specialityid);
					} else if (c == 7) {
						regionid = cell.getStringCellValue();
						if (StringUtils.isEmpty(regionid)) {
							rowMessage += "报考地市不能为空；";
						}
						else if (regionid.length() > 10) {
							rowMessage += "报考地市长度不能超过10；";
						} else {
							regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", regionid.substring(0,4));
							tempUserKB.setRegionid(Long.valueOf(regionid));
						}
					} else if (c == 8) {
						child_regionid = cell.getStringCellValue();
						if (StringUtils.isEmpty(child_regionid)) {
							rowMessage += "报考县区不能为空；";
						} else if (child_regionid.length() > 10) {
							rowMessage += "报考县区长度不能超过10；";
						};
						child_regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", child_regionid.substring(0,4));
						tempUserKB.setChildRegionid(Long.valueOf(child_regionid));
					} else if (c == 9) {
						audit_status = cell.getStringCellValue();
						if (StringUtils.isEmpty(audit_status)) {
							//教学点
							rowMessage += "审核状态不能为空；";
						};
						audit_status = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "audit_status", audit_status);
						tempUserKB.setAuditStatus(audit_status);
					}else if (c == 10) {
						status = cell.getStringCellValue();
						if (StringUtils.isEmpty(status)) {
							//教学点
							rowMessage += "考生状态不能为空；";
						};
						status = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "status", status);
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
				String specialityName1= FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", specialityid);
				String direction=specialityName.substring(specialityName1.length(),specialityName.length());
				speciality_recordid = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy", "specialityid_direction", specialityid+direction);
				if(StringUtils.isEmpty(speciality_recordid)){
					errorMsg+= br + "第" + (r + 1) + "行，" +"未找到对应专业开设编号";
				}else{
					tempUserKB.setSpecialityRecordid(Long.valueOf(speciality_recordid));

				}
				tempUserKB.setGradAuditStatus("a");
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}
		//验证上传数据的身份证在数据库是否已存在
		if (StringUtils.isEmpty(errorMsg)) {
			List<StudentSpecialityDO> list = studentSpecialityDao.listCZ(userKnowledgeBaseList);
			if (list.size() > 0) {
				errorMsg = "导入失败，以下准考证号的信息已存在相同专业的信息,不能重复添加";
				for (StudentSpecialityDO stu : list) {
					errorMsg += "," + stu.getStudentid();
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

	@Override
	public String batchImportKaoWu(String fileName, MultipartFile file) {

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
			return readExcelValueKaoWu(wb,tempFile);
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
}

