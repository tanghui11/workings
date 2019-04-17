package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CertificateReplaceDao;
import com.hxy.nzxy.stexam.center.plan.service.CertificateReplaceService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CertificateReplaceServiceImpl implements CertificateReplaceService {
	@Autowired
	private CertificateReplaceDao certificateReplaceDao;
	
	@Override
	public CertificateReplaceDO get(Long id){
		return certificateReplaceDao.get(id);
	}
	
	@Override
	public List<CertificateReplaceDO> list(Map<String, Object> map){
		return certificateReplaceDao.list(map);
	}

	@Override
	public String getFatherCourse(String courseReplaceId){
		Long id = Long.parseLong(courseReplaceId);
		return certificateReplaceDao.getCourseid(id);
	}

	@Override
	public int count(Map<String, Object> map){
		return certificateReplaceDao.count(map);
	}
	
	@Override
	public int save(CertificateReplaceDO certificateReplace){
		return certificateReplaceDao.save(certificateReplace);
	}

	public int zsSave(CertificateReplaceDO certificateReplace){
		return certificateReplaceDao.zsSave(certificateReplace);
	}

	@Override
	public int update(CertificateReplaceDO certificateReplace){
		return certificateReplaceDao.update(certificateReplace);
	}
	
	@Override
	public int remove(Long id){
		return certificateReplaceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return certificateReplaceDao.batchRemove(ids);
	}

	//免考课程规则批量导入
	@Override
	public String ZSbatchImport(String fileName, MultipartFile file){


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
		List<CertificateReplaceDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		CertificateReplaceDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new CertificateReplaceDO();

			String oldCourseid ="";
			String oldCourseName ="";
			String courseClass ="";

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						oldCourseid = cell.getStringCellValue();
						if (StringUtils.isEmpty(oldCourseid)) {
							rowMessage += "证书编号不能为空；";
						} else if (oldCourseid.length() > 20) {
							rowMessage += "证书编号的长度不能超过20；";
						}
						tempUserKB.setOldCourseid(oldCourseid);
					}else if (c == 2) {
						courseClass = cell.getStringCellValue();
						courseClass = FieldDictUtil.get(Constant.APPID, "pla_old_course", "type", courseClass);
						if (StringUtils.isEmpty(courseClass)) {
							rowMessage += "课程层次不能为空；";
						}else if (courseClass.length() > 1){
							rowMessage += "课程层次的长度不能超过1";
						}
						tempUserKB.setCourseClass(courseClass);

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
				userKnowledgeBaseList.add(tempUserKB);
			}
		}
		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}

		//验证上传数据在数据库是否已存在
		List<CertificateReplaceDO> list=certificateReplaceDao.listZS(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，课程编号和课程层次分别为";
			String Name = "";
			for(CertificateReplaceDO spe : list){
				Name = FieldDictUtil.get(Constant.APPID, "pla_speciality_record", "course_class", spe.getCourseClass());
				errorMsg += spe.getId()+","+Name;
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(CertificateReplaceDO courseClassify : userKnowledgeBaseList){
				this.zsSave(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
	
}
