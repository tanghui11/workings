package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseDao;
import com.hxy.nzxy.stexam.center.exam.dao.TaskExamDao;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.ListPlaceDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
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
public class ExamCourseServiceImpl implements ExamCourseService {
	@Autowired
	private ExamCourseDao examCourseDao;
	@Autowired
	private TaskExamDao taskDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public ExamCourseDO get(Long id){
		return examCourseDao.get(id);
	}

	@Override
	public List<ExamCourseDO> list(Map<String, Object> map){
		return examCourseDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return examCourseDao.count(map);
	}

	@Override
	public int save(ExamCourseDO examCourse){
		return examCourseDao.save(examCourse);
	}

	@Override
	public int update(ExamCourseDO examCourse){
		return examCourseDao.update(examCourse);
	}

	@Override
	public int remove(Long id){
		return examCourseDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return examCourseDao.batchRemove(ids);
	}

	@Override
	public List<ExamCourseDO> listProposition(Query query) {
		return examCourseDao.listProposition(query);
	}

	@Override
	public int countProposition(Query query) {
		return examCourseDao.countProposition(query);
	}

	@Override
	public void updateAllDefault(ExamCourseDO examCourse) {
		examCourseDao.updateAllDefault(examCourse);
	}

	@Override
	public void updateAudit(ExamCourseDO examCourse) {
		examCourseDao.updateAudit(examCourse);
	}

	@Override
	public void updateSequence(ExamCourseDO examCourse) {
		List<ExamCourseDO>	list =examCourseDao.listSequence(examCourse);
		examCourseDao.updateSequence(list);
	}

	@Override
	public void updateTask(TaskDO task) {
		taskDao.updateStatus(task);
	}

	@Override
	public List<ListPlaceDO> listPlace(String courseid){ return examCourseDao.listPlace(courseid);}

	@Override
	public Integer addPlaceCourse(Long courseid, Long id, String operator){ return examCourseDao.addPlaceCourse(courseid, id,operator);}

	@Override
	public int ifPlaceCourse(Long courseid, Long id, String operator){ return examCourseDao.ifPlaceCourse(courseid, id,operator);}

	@Override
	public Integer deletePlaceCourse(Long courseid, Long id, String operator){ return examCourseDao.deletePlaceCourse(courseid, id,operator);}

	@Override
	public List<ExamCourseDO> listPl(String courseid) {
		return examCourseDao.listPl(courseid);
	}

	@Override
	public ExamCourseDO listPP(Long specialityRecordid){ return examCourseDao.listPP(specialityRecordid);}

	//批量导入
	@Override
	public String batchImport(String fileName, MultipartFile file, String examTaskid, String examTimeid) {

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
			return readExcelValue(wb, tempFile,examTaskid,examTimeid);
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
	public void saveBatch(List<ExamCourseDO> userKnowledgeBaseList) {
		examCourseDao.saveBatch(userKnowledgeBaseList);
	}

	/**
	 * 解析Excel里面的数据
	 *
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile,String examTaskid,String examTimeid ) throws ParseException {

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
		List<ExamCourseDO> userKnowledgeBaseList = new ArrayList<>();
		ExamCourseDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new ExamCourseDO();

			String courseid = "";
			String courseName = "";
			String bookName = "";
			String type = "";
			String classify = "";
			String cardType = "";


			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (StringUtils.isEmpty(examTaskid)){
					rowMessage += "考试任务不能为空，请重新选择";
				}
				if (StringUtils.isEmpty(examTimeid)){
					rowMessage += "开考时间不能为空，请重新选择";
				}
				try {
					tempUserKB.setExamTaskid(Long.valueOf(examTaskid));
					tempUserKB.setExamTimeid(Long.valueOf(examTimeid));
				}catch (Exception e){
					rowMessage += "请选择考试任务和开考时间";
				}

				if (null != cell) {
					if (c == 0) {
						courseName = cell.getStringCellValue();
						courseid = FieldDictUtil.get(Constant.APPID,"pla_course_nzxy","id",courseName);
						if (StringUtils.isEmpty(courseid)) {
							rowMessage += "找不到对应课程名称或课程名称不能为空；";
						} else if (courseid.length() > 20) {
							rowMessage += "课程名称的长度不能超过20；";
						}
						tempUserKB.setCourseid(courseid);
					}else if (c == 1) {
						bookName = cell.getStringCellValue();
						String bookid = FieldDictUtil.get(Constant.APPID,"pla_book_nzxy","id",bookName);
						if (StringUtils.isEmpty(bookid)) {
							rowMessage += "教材名称不能为空；";
						} else if (bookid.length() > 20) {
							rowMessage += "教材名称的长度不能超过20；";
						}
						tempUserKB.setBookid(Long.valueOf(bookid));
					}else if (c == 2) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID,"exa_exam_course","type",type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空；";
						}
						tempUserKB.setType(type);
					}else if (c == 3) {
						classify = cell.getStringCellValue();
						classify = FieldDictUtil.get(Constant.APPID,"exa_exam_course","classify",classify);
						if (StringUtils.isEmpty(classify)) {
							rowMessage += "命题类别不能为空；";
						}
						tempUserKB.setClassify(classify);
					}else if (c ==4){
						cardType = cell.getStringCellValue();
						cardType = FieldDictUtil.get(Constant.APPID,"exa_exam_course","card_type",cardType);
						if (StringUtils.isEmpty(cardType)){
							rowMessage += "题卡类别不能为空";
						}
						tempUserKB.setCardType(cardType);
					}
					else {
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
//		if (StringUtils.isEmpty(errorMsg)) {
//			//验证上传数据在数据库是否已存在
//			List<ExamCourseDO> list= examCourseDao.listCZ(userKnowledgeBaseList);
//			if(list.size()>0){
//				errorMsg ="数据重复，重复的编号为";
//				for(ExamCourseDO spe : list){
//					errorMsg += spe.getCourseid()+",";
//				}
//				errorMsg +="数据库已存在,不能重复添加";
//			}
//		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){

			this.saveBatch(userKnowledgeBaseList);
			for (ExamCourseDO task : userKnowledgeBaseList) {
				FieldDictDO fieldDict = new FieldDictDO();
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("exa_exam_course_nzxy");
				fieldDict.setFieldName("exam_taskid_courseid");
				fieldDict.setFieldValue(task.getExamTaskid()+task.getCourseid());
				fieldDict.setFieldMean(task.getId()+"");
				fieldDictService.saveCache(fieldDict);


			}
			errorMsg = "导入成功，共" + userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}