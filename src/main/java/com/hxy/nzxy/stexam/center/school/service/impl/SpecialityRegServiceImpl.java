package com.hxy.nzxy.stexam.center.school.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.RegionDO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.hxy.nzxy.stexam.center.school.dao.SpecialityRegDao;
import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import com.hxy.nzxy.stexam.center.school.service.SpecialityRegService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class SpecialityRegServiceImpl implements SpecialityRegService {
	@Autowired
	private SpecialityRegDao specialityRegDao;
	
	@Override
	public SpecialityRegDO get(Long id){
		return specialityRegDao.get(id);
	}
	
	@Override
	public List<SpecialityRegDO> list(Map<String, Object> map){
		return specialityRegDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialityRegDao.count(map);
	}
	
	@Override
	public int save(SpecialityRegDO specialityReg){
		return specialityRegDao.save(specialityReg);
	}
	
	@Override
	public int update(SpecialityRegDO specialityReg){
		return specialityRegDao.update(specialityReg);
	}
	
	@Override
	public int remove(Long id){
		return specialityRegDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return specialityRegDao.batchRemove(ids);
	}


    @Override
    public int updateAudit(Map<String, Object> params) {
        return specialityRegDao.updateAudit(params);
    }

    @Override
    public int batchUpdateAudit(Long[] ids, String auditStatus) {
        Map  map =new HashMap();
        map.put("array",ids);
        map.put("auditStatus",auditStatus);
        return specialityRegDao.batchUpdateAudit(map);
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
	private String readExcelValue(Workbook wb,File tempFile) {

		String errorMsg = "";
		Sheet sheet=wb.getSheetAt(0);
		int totalRows=sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<SpecialityRegDO> userKnowledgeBaseList=new ArrayList<SpecialityRegDO>();
		SpecialityRegDO tempUserKB ;
		String br = "<br/>";
		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new SpecialityRegDO();//定义了一个空的courseFreeCenterDO

			String specialityid = ""; //专业id
			String specialityName = ""; //专业mingzi
			String schoolid = "";//助学组织编号_名称
			String speciality_recordid ="";//专业开设编号
			String code ="";//自定义代码
			String auditStatus ="";//审核状态
			String status ="";//使用状态
			String direction = "";//专业方向


			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						specialityid = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityid)) {
							rowMessage += "专业代码不能为空；";
						}
						tempUserKB.setSubjectCode(specialityid);
					} else if (c == 1) {
						specialityName = cell.getStringCellValue();
						if (StringUtils.isEmpty(specialityName)) {
							rowMessage += "专业名称不能为空；";
						} else {
							String Name = FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", specialityid);
							direction = specialityName.substring(Name.length(), specialityName.length());

							tempUserKB.setDirection(direction);//专业方向
						}
					} else if (c == 2) {
						schoolid = cell.getStringCellValue();
						schoolid = FieldDictUtil.get(Constant.APPID, "sch_school_nzxy", "code", schoolid.substring(0,4));
						if (StringUtils.isEmpty(schoolid)) {
							rowMessage += "助学组织不能为空；";
						}
						tempUserKB.setSchoolid(Long.parseLong(schoolid));
					}  else if (c == 3) {
						code = cell.getStringCellValue();
						if (StringUtils.isEmpty(code)) {
							rowMessage += "自定义代码不能为空；";
						} else if (code.length() > 2) {
							rowMessage += "自定义代码的长度不能超过20；";
						}
						tempUserKB.setCode(code);
					} else if (c == 4) {
						auditStatus = cell.getStringCellValue();
						auditStatus = FieldDictUtil.get(Constant.APPID, "sch_speciality_reg", "audit_status", auditStatus);
						if (StringUtils.isEmpty(auditStatus)) {
							rowMessage += "审核状态不能为空";
						} else if (auditStatus.length() > 1) {
							rowMessage += "审核状态不可以超过1";
						}
						tempUserKB.setAuditStatus(auditStatus);
					} else if (c == 5) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "sch_speciality_reg", "status", status);
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
					speciality_recordid = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy", "specialityid_direction", specialityid+direction);
					if(speciality_recordid == null || "".equals(speciality_recordid)){
						rowMessage += "专业代码或名称不正确";
					}
					tempUserKB.setSpecialityRecordid(Long.parseLong(speciality_recordid));
					tempUserKB.setOperator(ShiroUtils.getUserId().toString());//添加操作人
					tempUserKB.setEnabledFlag(1);
					userKnowledgeBaseList.add(tempUserKB);//添加信息到userKnowledgeBaseList
				}
			}

		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		//验证上传数据在数据库是否已存在
		List<SpecialityRegDO> list=specialityRegDao.listK(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，助学组织编号和代码为";
			for(SpecialityRegDO cfc : list){
				errorMsg += "助学组织编号："+cfc.getSchoolid()+",代码："+cfc.getCode();
			}
			errorMsg +="已存在,不能重复添加";
		}



		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(SpecialityRegDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}
