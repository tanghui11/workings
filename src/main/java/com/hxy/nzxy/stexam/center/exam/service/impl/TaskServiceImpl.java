package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.TaskExamDao;
import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
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
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskExamDao taskDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public TaskDO get(Long id){
		return taskDao.get(id);
	}
	
	@Override
	public List<TaskDO> list(Map<String, Object> map){
		return taskDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskDao.count(map);
	}
	
	@Override
	public int save(TaskDO task){
		return taskDao.save(task);
	}
	
	@Override
	public int update(TaskDO task){
		return taskDao.update(task);
	}
	
	@Override
	public int remove(Long id){
		return taskDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return taskDao.batchRemove(ids);
	}

	@Override
	public List<TaskDO> serchTaskAll(Map<String, Object> map) {
		return taskDao.serchTaskAll(map);
	}


	@Override
	public List<TaskDO> taskList(Map<String, Object> map) {
		return taskDao.taskList(map);
	}
	@Override
	public int updateStatus(TaskDO task) {
		return taskDao.updateStatus(task);
	}
	@Override
	public List<TaskDO> seachBkTimeSetlist(Map<String, Object> map) {
		return taskDao.seachBkTimeSetlist(map);
	}

	@Override
	public int bkcount(Long[] ids) {
		return taskDao.bkcount(ids);
	}


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

		String errorMsg = "";
		Sheet sheet=wb.getSheetAt(0);
		int totalRows=sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<TaskDO> userKnowledgeBaseList =             new ArrayList<>();
		TaskDO tempUserKB ;
		String br = "<br/>";
		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new TaskDO();//定义了一个空的courseFreeCenterDO

			String  exam_year          = "";
			String  exam_month         = "";
			String type = "";
			String  remark             =  "";
			String  status             =  "";
			String  confirm_status     =  "";
			String 	audit_status      =  "";

			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						exam_year = cell.getStringCellValue();
						if (StringUtils.isEmpty(exam_year)) {
							rowMessage += "年份不能为空；";
						}
						tempUserKB.setExamYear(exam_year);
					} else if (c == 1) {
						exam_month = cell.getStringCellValue();
						if (!("01月".equals(exam_month) || "04月".equals(exam_month)|| "07月".equals(exam_month)|| "10月".equals(exam_month)|| "08月".equals(exam_month))) {
							rowMessage += "月份无效，请填有效月份；（如：1月请填01月）";
						}
						exam_month = FieldDictUtil.get(Constant.APPID, "exam_task", "exam_month", exam_month);
						tempUserKB.setExamMonth(exam_month);
					}else if (c == 2) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "exam_task", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "考试类型不能为空；";
						} else if (type.length() > 1) {
							rowMessage += "考试类型不可以超过1；";
						}
						tempUserKB.setType(type);
					}
					else if (c == 3) {
						//备注
						remark = cell.getStringCellValue();
						tempUserKB.setRemark(remark);
					} else if (c == 4) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "exam_task", "status", status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						}if (status.length() > 1){
							rowMessage += "状态长度不能大于1；";
						}
						tempUserKB.setStatus(status);
					} else if (c == 5) {
						confirm_status = cell.getStringCellValue();
						confirm_status = FieldDictUtil.get(Constant.APPID, "exam_task", "confirm_status", confirm_status);
						if (StringUtils.isEmpty(confirm_status)) {
							rowMessage += "确认状态不能为空；";
						} else if (confirm_status.length() > 1) {
							rowMessage += "确认状态不可以超过1；";
						}
						tempUserKB.setConfirmStatus(confirm_status);
					} else if (c == 6) {
						audit_status = cell.getStringCellValue();
						audit_status = FieldDictUtil.get(Constant.APPID, "exam_task", "audit_status", audit_status);
						if (StringUtils.isEmpty(audit_status)) {
							rowMessage += "审核状态不能为空；";
						} else if (audit_status.length() > 1) {
							rowMessage += "审核状态不可以超过1；";
						}
						tempUserKB.setAuditStatus(audit_status);
					}
				}else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}
			}
			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());//添加操作人
				tempUserKB.setEnabledFlag(1);
				userKnowledgeBaseList.add(tempUserKB);//添加信息到userKnowledgeBaseList
			}
		}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
            for (TaskDO task : userKnowledgeBaseList) {
            	try {
					this.save(task);
					FieldDictDO fieldDict = new FieldDictDO();
					fieldDict.setAppid(Constant.APPID);
					fieldDict.setTableName("exam_task_nzxy");
					fieldDict.setFieldName("exam_year_exam_month");
					fieldDict.setFieldValue(task.getExamYear()+task.getExamMonth());
					fieldDict.setFieldMean(task.getId()+"");
					fieldDictService.saveCache(fieldDict);
				}catch (Exception e){
            		errorMsg = "可能日期有重复,请仔细检查";
				}

            }
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

	private TaskDO saveBatch(List<TaskDO> userKnowledgeBaseList) {
		return taskDao.saveBatch(userKnowledgeBaseList);
	}


}