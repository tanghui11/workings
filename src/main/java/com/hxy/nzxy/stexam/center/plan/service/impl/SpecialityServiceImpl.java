package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityDao;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseClassifyDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
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
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private SpecialityDao specialityDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public SpecialityDO get(String id){
		return specialityDao.get(id);
	}
	
	@Override
	public List<SpecialityDO> list(Map<String, Object> map){
		return specialityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialityDao.count(map);
	}
	
	@Override
	public int save(SpecialityDO speciality){
		return specialityDao.save(speciality);
	}
	
	@Override
	public int update(SpecialityDO speciality){
		return specialityDao.update(speciality);
	}
	
	@Override
	public int remove(String id){
		return specialityDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return specialityDao.batchRemove(ids);
	}

	@Override
	public List<SpecialityDO> listSpeciality(List<String> list) {
		 return specialityDao.listSpeciality(list);
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
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile) throws ParseException {

		//错误信息接收器
		String errorMsg = "";
		//得到第一个shell
		Sheet sheet=wb.getSheetAt(0);
		//得到Excel的行数
		int totalRows=sheet.getPhysicalNumberOfRows();
		//总列数
		int totalCells = 0;
		//得到Excel的列数(前提是有行数)，从第二行算起
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<SpecialityDO> userKnowledgeBaseList=new ArrayList<>();
		SpecialityDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new SpecialityDO();

			String id = "";
			String name = "";
			String pinyin = "";
			String type = "";
			String classify = "";
			String flag = "";
			String grantType = "";
			String score = "0";
			String auditStatus = "";
			String auditDate = "";
			String zkSpecialityid = "";
			String operator = "";
			String updateDate = "";
			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						id = cell.getStringCellValue();
						if (StringUtils.isEmpty(id)) {
							rowMessage += "编号不能为空；";
						} else if (id.length() > 20) {
							rowMessage += "编号的长度不能超过20；";
						}
						tempUserKB.setId(id);
					} else if (c == 1) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "名称不能为空；";
						} else if (name.length() > 100) {
							rowMessage += "编号的长度不能超过100；";
						}
						tempUserKB.setName(name);
					} else if (c == 2) {
						pinyin = cell.getStringCellValue();
						if (StringUtils.isEmpty(pinyin)) {
							rowMessage += "拼音不能为空；";
						} else if (pinyin.length() > 100) {
							rowMessage += "拼音的长度不能超过100；";
						}
						tempUserKB.setPinyin(pinyin);
					} else if (c == 3) {
						type = cell.getStringCellValue();

						type = FieldDictUtil.get(Constant.APPID, "pla_speciality", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空,或未找到对应的类别；";
						}
						tempUserKB.setType(type);
					} else if (c == 4) {
						classify = cell.getStringCellValue();
						classify = FieldDictUtil.get(Constant.APPID, "pla_speciality", "classify", classify);

						if (StringUtils.isEmpty(classify)) {
							rowMessage += "专业层次不能为空,或未找到对应的层次；";
						}
						tempUserKB.setClassify(classify);
					} else if (c == 5) {
						flag = cell.getStringCellValue();
						flag = FieldDictUtil.get(Constant.APPID, "pla_speciality", "flag", flag);
						if (StringUtils.isEmpty(flag)) {
							rowMessage += "层次类型不能为空,或为扎到对应的层次类型；";
						}
						tempUserKB.setFlag(flag);
					} else if (c == 6) {
						grantType = cell.getStringCellValue();
						grantType = FieldDictUtil.get(Constant.APPID, "pla_speciality", "grant_type", grantType);
						if (StringUtils.isEmpty(grantType)) {
							rowMessage += "委托类型不能为空,或未找到对应的委托类型；";
						}
						tempUserKB.setGrantType(grantType);
					} else if (c == 7) {
						score = cell.getStringCellValue();
						if (StringUtils.isEmpty(score)) {
							rowMessage += "学分不能为空；";
						} else if (score.length() > 10) {
							rowMessage += "学分的长度过长；";
						}
						tempUserKB.setScore(Integer.valueOf(score));
					} else if (c == 8) {
						auditStatus = cell.getStringCellValue();
						if (StringUtils.isEmpty(auditStatus)) {
							//rowMessage += "审批状态不能为空；";
						}
						auditStatus = FieldDictUtil.get(Constant.APPID, "pla_speciality", "audit_status", auditStatus);

						tempUserKB.setAuditStatus(auditStatus);
					} else if (c == 9) {
						auditDate = cell.getStringCellValue();
						if (StringUtils.isEmpty(auditDate)) {
							//rowMessage += "审批时间不能为空；";
						}
						tempUserKB.setAuditDate(sdf.parse(auditDate));
					} else if (c == 10) {
						zkSpecialityid = cell.getStringCellValue();
						if (StringUtils.isEmpty(zkSpecialityid)) {
						} else if (zkSpecialityid.length() > 20) {
							rowMessage += "专科id的长度过长；";
						}
						tempUserKB.setZkSpecialityid(zkSpecialityid);
					}
					else if (c == 11) {
							/*operator = cell.getStringCellValue();
							if (StringUtils.isEmpty(operator)) {
							}*/
							tempUserKB.setOperator(ShiroUtils.getUserId().toString());
					} else if (c == 12) {
							updateDate = cell.getStringCellValue();
							if (StringUtils.isEmpty(updateDate)) {
							};
							tempUserKB.setUpdateDate(updateDate);
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
					userKnowledgeBaseList.add(tempUserKB);
				}
			}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		//验证上传数据在数据库是否已存在
		List<SpecialityDO> list=specialityDao.listCZ(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，编号为";
			for(SpecialityDO spe : list){
				errorMsg += spe.getId()+",";
			}
			errorMsg +="的专业数据库已存在,不能重复添加";
		}



		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			this.saveBatch(userKnowledgeBaseList);
			//加入缓存
			for (SpecialityDO speciality:userKnowledgeBaseList) {
				FieldDictDO fieldDict = new FieldDictDO();
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("pla_speciality_nzxy");
				fieldDict.setFieldName("id");
				fieldDict.setFieldValue(speciality.getId());
				fieldDict.setFieldMean(speciality.getName());
				fieldDictService.saveCache(fieldDict);
			};
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

	private void saveBatch(List<SpecialityDO> userKnowledgeBaseList) {
		specialityDao.saveBatch(userKnowledgeBaseList);
	}

}
