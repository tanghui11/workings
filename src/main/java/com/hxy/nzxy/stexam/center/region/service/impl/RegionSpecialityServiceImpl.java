package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.RegionSpecialityDao;
import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import com.hxy.nzxy.stexam.center.region.service.RegionSpecialityService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class RegionSpecialityServiceImpl implements RegionSpecialityService {
	@Autowired
	private RegionSpecialityDao regionSpecialityDao;
	
	@Override
	public RegionSpecialityDO get(Long id){
		return regionSpecialityDao.get(id);
	}
	
	@Override
	public List<RegionSpecialityDO> list(Map<String, Object> map){
		return regionSpecialityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return regionSpecialityDao.count(map);
	}
	
	@Override
	public int save(RegionSpecialityDO regionSpeciality){
		return regionSpecialityDao.save(regionSpeciality);
	}
	
	@Override
	public int update(RegionSpecialityDO regionSpeciality){
		return regionSpecialityDao.update(regionSpeciality);
	}
	
	@Override
	public int remove(Long id){
		return regionSpecialityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return regionSpecialityDao.batchRemove(ids);
	}

	@Override
	public List<RegionSpecialityDO> seachSubjectIdlist(Map<String, Object> map) {
		return regionSpecialityDao.seachSubjectIdlist(map);
	}
	@Override
	public int batchSave(List<RegionSpecialityDO> list) {
		return regionSpecialityDao.batchSave(list);
	}

	@Override
	public int countSubject(Map<String, Object> map) {
		return regionSpecialityDao.countSubject(map);
	}



	//批量导入
	@Override
	public String batchImport(String fileName, MultipartFile file,String regionid) {

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
			return readExcelValue(wb, tempFile,regionid);
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
	public void saveBatch(List<RegionSpecialityDO> userKnowledgeBaseList) {
		regionSpecialityDao.saveBatch(userKnowledgeBaseList);
	}

	/**
	 * 解析Excel里面的数据
	 *
	 * @param wb
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile,String regionid) throws ParseException {

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
		List<RegionSpecialityDO> userKnowledgeBaseList = new ArrayList<>();
		RegionSpecialityDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new RegionSpecialityDO();
			String specialityId = "";
			String specialityName= "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (!StringUtils.isEmpty(regionid)){
					tempUserKB.setRegionid(Long.valueOf(regionid));
				}else{
					rowMessage += "所选考区为空；";
				}

				if (null != cell) {
					 if (c == 0) {

					}
					else if (c == 1) {
                        specialityId = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityId)) {
							rowMessage += "专业代码不能为空；";
						}if (specialityId.length() > 20) {
							rowMessage += "专业代码长度不能超过20；";
						}

					}else if (c == 2){
						specialityName = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityName)) {
							rowMessage += "专业名称不能为空；";
						}if (specialityName.length() > 20) {
							rowMessage += "专业名称长度不能超过20；";
						}
						String specialityRecordid = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy",
								"specialityid_specialityName",  specialityId+specialityName);
						tempUserKB.setSpecialityRecordid(Long.valueOf(specialityRecordid));
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
                tempUserKB.setRegionid(Long.valueOf(regionid));
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		if (StringUtils.isEmpty(errorMsg)) {
			//验证上传数据在数据库是否已存在
			List<RegionSpecialityDO> list= regionSpecialityDao.listCZ(userKnowledgeBaseList);
			if(list.size()>0){
				errorMsg ="数据重复，重复的编号为";
				for(RegionSpecialityDO spe : list){
                   String specialityId = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy",
						   "id",  spe.getSpecialityRecordid()+"");
					errorMsg += specialityId+",";
				}
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