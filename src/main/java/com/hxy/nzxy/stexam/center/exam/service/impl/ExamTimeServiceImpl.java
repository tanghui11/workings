package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.ExamTimeDao;
import com.hxy.nzxy.stexam.center.exam.service.ExamTimeService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamTimeDO;
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
public class ExamTimeServiceImpl implements ExamTimeService {
	@Autowired
	private ExamTimeDao examTimeDao;
	
	@Override
	public ExamTimeDO get(Long id){
		return examTimeDao.get(id);
	}
	
	@Override
	public List<ExamTimeDO> list(Map<String, Object> map){
		return examTimeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examTimeDao.count(map);
	}
	
	@Override
	public int save(ExamTimeDO examTime){
		return examTimeDao.save(examTime);
	}
	
	@Override
	public int update(ExamTimeDO examTime){
		return examTimeDao.update(examTime);
	}
	
	@Override
	public int remove(Long id){
		return examTimeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examTimeDao.batchRemove(ids);
	}

	@Override
	public List<ExamTimeDO> seachExamTimeAllList(Map<String, Object> params) {
		return examTimeDao.seachExamTimeAllList( params) ;
	}



	//批量导入
	@Override
	public String batchImport(String fileName, MultipartFile file,String examTaskid) {

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
			return readExcelValue(wb, tempFile ,examTaskid);
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
	public void saveBatch(List<ExamTimeDO> userKnowledgeBaseList) {
		examTimeDao.saveBatch(userKnowledgeBaseList);
	}

	/**
	 * 解析Excel里面的数据
	 *
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile ,String examTaskid) throws ParseException {

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
		List<ExamTimeDO> userKnowledgeBaseList = new ArrayList<>();
		ExamTimeDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new ExamTimeDO();

			String examDate = "";
			String segment = "";
			String beginTime = "";
			String endTime = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				if (StringUtils.isEmpty(examTaskid)) {
					rowMessage += "任务编号不能为空；";
				} else if (examTaskid.length() > 20) {
					rowMessage += "任务编号的长度不能超过20；";
				}
				tempUserKB.setExamTaskid(Long.valueOf(examTaskid));

				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
                        examDate = cell.getStringCellValue();
						if (StringUtils.isEmpty(examDate)) {
							rowMessage += "开考日期不能为空；";
						}
						tempUserKB.setExamDate(CommonUtil.stringToDate(examDate,"yyyy-MM-dd"));
					} else if (c == 1) {
                        segment = cell.getStringCellValue();
                        segment = FieldDictUtil.get(Constant.APPID,"exa_exam_time","segment",segment);
						if (StringUtils.isEmpty(segment)) {
							rowMessage += "时段不能为空；";
						}
						tempUserKB.setSegment(segment);
					} else if (c == 2) {
						beginTime = cell.getStringCellValue();
						if (StringUtils.isEmpty(beginTime)) {
							rowMessage += "开考时间不能为空；";
						} else if (beginTime.length() > 20) {
							rowMessage += "开考时间的长度不能超过20；";
						}
						tempUserKB.setBeginTime(beginTime);
					} else if (c == 3) {
						endTime = cell.getStringCellValue();
						if (StringUtils.isEmpty(endTime)) {
							rowMessage += "结束时间不能为空；";
						} else if (endTime.length() > 20) {
							rowMessage += "结束时间的长度不能超过20；";
						}
						tempUserKB.setEndTime(endTime);
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
			List<ExamTimeDO> list= examTimeDao.listCZ(userKnowledgeBaseList);
			if(list.size()>0){
				errorMsg ="数据重复，重复的时间为";
				for(ExamTimeDO spe : list){
					errorMsg += examTaskid + " "+ CommonUtil.dateToString(spe.getExamDate(),null) +"\n";
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