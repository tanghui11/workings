package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.CollegeDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSpecialityRegDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.center.sys.dao.SystemStudentDao;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.student.dao.StudentFileStudentDao;
import com.hxy.nzxy.stexam.school.student.dao.StudentSpecialityStudentDao;
import com.hxy.nzxy.stexam.school.student.service.StudentFileStudentService;
import org.apache.ibatis.annotations.Param;
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
public class StudentFileStudentServiceImpl implements StudentFileStudentService {
	@Autowired
	private StudentFileStudentDao studentStudentDao;
	@Autowired
	private CollegeDao collegeDao;
	@Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private StudentSpecialityStudentDao studentSpecialityStudentDao;
	@Autowired
	private SystemStudentDao systemStudentDao;
	@Autowired
	private SchoolSpecialityRegDao schoolSpecialityRegDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;

	public int zsSave(StudentBZCDO courseFreeCenter){
		return studentSpecialityStudentDao.zsSave(courseFreeCenter);
	}
	@Override
	public StudentDO get(String id){
		return studentStudentDao.get(id);
	}
	
	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentStudentDao.list(map);
	}
	@Override
	public List<StudentDO> listQu(Map<String, Object> map){
		return studentStudentDao.listQu(map);
	}

//	@Override
//	public List<StudentRegDO> listReg(Map<String, Object> map){
//		return studentStudentDao.listReg(map);
//	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentStudentDao.count(map);
	}
	@Override
	public int countQu(Map<String, Object> map){
		return studentStudentDao.countQu(map);
	}
	
	@Override
	public int save(StudentDO studentStudent,StudentSpecialityDO studentSpeciality){

		studentStudent.setClassify("b");
		//获取组织代码
		CollegeDO collegeDO = collegeDao.get(studentStudent.getCollegeid() + "");
		studentStudent.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
		//获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(studentStudent.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentStudent.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentStudent.setRegionid(regionDO.getParentid());
		//在籍
		studentStudent.setStatus("a");
		//待审核
		studentStudent.setAuditStatus("a");
		//确认状态待确认
		studentStudent.setConfirmStatus("a");
        studentStudent.setType("b");
		studentStudent.setKjh(studentStudent.getId().substring(4,7));
		//学生基本信息
		studentStudentDao.save(studentStudent);
		//报考信息
		studentSpeciality.setStudentid(studentStudent.getId());
        studentSpeciality.setRegionid(studentStudent.getRegionid());
        studentSpeciality.setChildRegionid(studentStudent.getChildRegionid());
        studentSpeciality.setSchoolid(studentStudent.getSchoolid());
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
		studentSpecialityStudentDao.save(studentSpeciality);
		return 1;
	}



	@Override
	public int update(StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
		//获取组织代码
		CollegeDO collegeDO = collegeDao.get(studentStudent.getCollegeid() + "");
		studentStudent.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
		//获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(studentStudent.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentStudent.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentStudent.setRegionid(regionDO.getParentid());
		//学生基本信息
		studentStudentDao.update(studentStudent);
		//报考信息
		studentSpeciality.setStudentid(studentStudent.getId());
		studentSpeciality.setRegionid(studentStudent.getRegionid());
		studentSpeciality.setChildRegionid(studentStudent.getChildRegionid());
		studentSpeciality.setSchoolid(studentStudent.getSchoolid());
		//获取招生年份和季节;
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
		studentSpecialityStudentDao.update(studentSpeciality);


		return 1;
	}
	
	@Override
	public int remove(String id){
		return studentStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentStudentDao.batchRemove(ids);
	}

	@Override
	public int updateTx(String[] ids) {

		return studentStudentDao.updateTx1(ids);
	}

	/**
	 * 考生补注册批量导入
	 * @param fileName
	 * @param file
	 * @return
	 */
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
		List<StudentBZCDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		StudentBZCDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new StudentBZCDO();

			String studentid = "";
			String specialityid = "";
			String regBeginDate = "";
			String regEndDate = "";
			String status = "";
			String createDate = "";
			String studentSpecialityid = "";
			Long StudentSpecialityid = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
						specialityid = cell.getStringCellValue();
						//specialityid = FieldDictUtil.get(Constant.APPID, "pla_old_course", "type", courseClass);
						if (StringUtils.isEmpty(specialityid)) {
							rowMessage += "专业编号不能为空；";
						}else if (specialityid.length() > 20){
							rowMessage += "专业编号的长度不能超过1";
						}
						tempUserKB.setSpecialityid(specialityid);
					}else if (c == 2) {
						regBeginDate = cell.getStringCellValue();
						//specialityid = FieldDictUtil.get(Constant.APPID, "pla_old_course", "type", courseClass);
						if (StringUtils.isEmpty(regBeginDate)) {
							rowMessage += "开始时间不能为空；";
						}
						tempUserKB.setRegBeginDate(sdf.parse(regBeginDate));
					}else if (c == 3) {
						regEndDate = cell.getStringCellValue();
						//specialityid = FieldDictUtil.get(Constant.APPID, "pla_old_course", "type", courseClass);
						if (StringUtils.isEmpty(regEndDate)) {
							rowMessage += "结束时间不能为空；";
						}
						tempUserKB.setRegEndDate(sdf.parse(regEndDate));
					}else if (c == 4) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "stu_student_reg", "status", status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						}else if (status.length() > 1){
							rowMessage += "状态的长度不能超过1";
						}
						tempUserKB.setStatus(status);
					}else if (c == 5) {
						createDate = cell.getStringCellValue();
						//specialityid = FieldDictUtil.get(Constant.APPID, "pla_old_course", "type", courseClass);
						if (StringUtils.isEmpty(createDate)) {
							rowMessage += "创建时间不能为空；";
						}
						tempUserKB.setCreateDate(sdf.parse(createDate));
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
				StudentSpecialityid = studentSpecialityStudentDao.getStudentSpecialityid(studentid, specialityid);

				tempUserKB.setStudentSpecialityid(StudentSpecialityid);
				userKnowledgeBaseList.add(tempUserKB);
			}
		}
		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}

		//验证上传数据在数据库是否已存在
		List<StudentBZCDO> list=studentSpecialityStudentDao.listZS(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，编号为";
			for(StudentBZCDO spe : list){
				errorMsg += spe.getId()+",";
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(StudentBZCDO courseClassify : userKnowledgeBaseList){
				this.zsSave(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}
