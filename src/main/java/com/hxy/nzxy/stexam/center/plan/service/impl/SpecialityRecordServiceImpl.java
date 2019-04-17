package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
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
public class SpecialityRecordServiceImpl implements SpecialityRecordService {
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public SpecialityRecordDO get(Long id){
		return specialityRecordDao.get(id);
	}
	
	@Override
	public List<SpecialityRecordDO> list(Map<String, Object> map){
		return specialityRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialityRecordDao.count(map);
	}
	
	@Override
	public int save(SpecialityRecordDO specialityRecord){
		return specialityRecordDao.save(specialityRecord);
	}
	
	@Override
	public int update(SpecialityRecordDO specialityRecord){
		return specialityRecordDao.update(specialityRecord);
	}
	
	@Override
	public int remove(Long id){
		return specialityRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return specialityRecordDao.batchRemove(ids);
	}

	@Override
	public SpecialityRecordDO getSpecialityRecordid(SpecialityRegDO specialityRegSchool) {
		return specialityRecordDao.getSpecialityRecordid(specialityRegSchool);
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
		List<SpecialityRecordDO> userKnowledgeBaseList = new ArrayList<>();
		SpecialityRecordDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new SpecialityRecordDO();
			String specialityid = "";
			String type = "";
			String schoolid = "";
//			String collegeid = "";
			String direction = "";
			String grad_courseid = "";
			String status = "";
			String grad_status = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						specialityid = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityid)) {
							rowMessage += "专业代码不能为空；";
						} else if (specialityid.length() > 20) {
							rowMessage += "专业代码的长度不能超过20；";
						}
						tempUserKB.setSpecialityid(specialityid);
					} else if (c == 1) {
						//专业名称
					} else if (c == 2) {
						direction = cell.getStringCellValue();
						 if (direction.length() > 20) {
							rowMessage += "专业方向的长度不能超过20；";
						}
						tempUserKB.setDirection(direction);
					} else if (c == 3) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "pla_speciality_record", "type",type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空；";
						}
						tempUserKB.setType(type);
					}else if (c == 4) {
						//主考院校编号
						schoolid = cell.getStringCellValue();
						schoolid = FieldDictUtil.get(Constant.APPID,"pla_speciality_record","schoolid",schoolid);
						if (!StringUtils.isEmpty(schoolid)) {
							tempUserKB.setSchoolid(Long.parseLong(schoolid));
						}
					}
//					else if (c == 5) {
//						//主考学院编号
//						collegeid = cell.getStringCellValue();
//						if (!StringUtils.isEmpty(collegeid)) {
//							tempUserKB.setCollegeid(Long.valueOf(collegeid));
//						}
					 else if (c == 5) {
						grad_courseid = cell.getStringCellValue();
						if (grad_courseid.length() > 20) {
							rowMessage += "毕业论文课程代码长度不能超过20；";
						}
						tempUserKB.setGradCourseid(grad_courseid);
					} else if (c == 6) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "pla_speciality_record", "status",status);
						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						}
						tempUserKB.setStatus(status);
					}  else if (c == 7) {
						grad_status = cell.getStringCellValue();
						grad_status = FieldDictUtil.get(Constant.APPID, "pla_speciality_record", "grad_status",grad_status);
						if (StringUtils.isEmpty(grad_status)) {
							rowMessage += "办证状态不能为空；";
						}else if (grad_status.length()>1000) {
                            rowMessage += "办证状态长度不能大于1000；";
                        }
						tempUserKB.setGradStatus(grad_status);
					}else {
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
            List<SpecialityRecordDO> list = specialityRecordDao.listCZ(userKnowledgeBaseList);
            if (list.size() > 0) {
                errorMsg = "导入失败，编号为";
                for (SpecialityRecordDO spe : list) {
                    errorMsg += spe.getSpecialityid() + ",";
                }
                errorMsg += "的专业数据库已存在,不能重复添加";
            }
        }
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			this.saveBatch(userKnowledgeBaseList);
			for(SpecialityRecordDO courseClassify : userKnowledgeBaseList){
				FieldDictDO fieldDict = new FieldDictDO();
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("pla_speciality_record_nzxy");
				fieldDict.setFieldName("specialityid_direction");
				fieldDict.setFieldValue(courseClassify.getSpecialityid()+courseClassify.getDirection());
				fieldDict.setFieldMean(courseClassify.getId()+"");
				fieldDictService.saveCache(fieldDict);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

	public void saveBatch(List<SpecialityRecordDO> userKnowledgeBaseList) {
		specialityRecordDao.saveBatch(userKnowledgeBaseList);
	}
}