package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.ExamSiteDao;
import com.hxy.nzxy.stexam.center.region.service.ExamSiteService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.ExamSiteDO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ExamSiteServiceImpl implements ExamSiteService {
	@Autowired
	private ExamSiteDao examSiteDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public ExamSiteDO get(Long id){
		return examSiteDao.get(id);
	}
	
	@Override
	public List<ExamSiteDO> list(Map<String, Object> map){
		return examSiteDao.list(map);
	}

	@Autowired
	private RegionServiceImpl regionServiceImpl;
	@Override
	public int count(Map<String, Object> map){
		return examSiteDao.count(map);
	}
	
	@Override
	public int save(ExamSiteDO examSite){
		return examSiteDao.save(examSite);
	}
	
	@Override
	public int update(ExamSiteDO examSite){
		return examSiteDao.update(examSite);
	}
	
	@Override
	public int remove(Long id){
		return examSiteDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examSiteDao.batchRemove(ids);
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
		List<ExamSiteDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		ExamSiteDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new ExamSiteDO();

			String regionid ="";
			String code ="";
			String name ="";
			String pinyin ="";
			String num ="";
			String linkman ="";
			String phone ="";
			String fax ="";
			String address ="";
			String post_code ="";
			String remark ="";
			String school ="";
			String school_code ="";
			String teach_code ="";
			String leader ="";
			String standard ="";
			String status ="";
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						regionid = cell.getStringCellValue();
						if (StringUtils.isEmpty(regionid)) {
							rowMessage += "考区编码不能为空；";
						}
						tempUserKB.setRegionid(Long.parseLong(regionid));
					} else if (c == 1) {
						code = cell.getStringCellValue();
						if (StringUtils.isEmpty(code)) {
							rowMessage += "考点代码不能为空；";
						} else if (code.length() > 20) {
							rowMessage += "考点代码的长度不能超过1；";
						}
						tempUserKB.setCode(code);

					} else if (c == 2) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "名称不能为空；";
						}
						tempUserKB.setName(name);

					} else if (c == 3) {
						pinyin = cell.getStringCellValue();
						if (StringUtils.isEmpty(pinyin)) {
							tempUserKB.setPinyin(regionServiceImpl.getPingYin(name));
						}
					}else if (c == 4) {
						num = cell.getStringCellValue();

						tempUserKB.setNum(Integer.parseInt(num));
					}else if (c == 5) {
						linkman = cell.getStringCellValue();

						tempUserKB.setLinkman(linkman);
					}else if (c == 6) {
						phone = cell.getStringCellValue();

						tempUserKB.setPhone(phone);
					}else if (c == 7) {
						fax = cell.getStringCellValue();

						tempUserKB.setFax(fax);
					}else if (c == 8) {
						address = cell.getStringCellValue();
						tempUserKB.setAddress(address);
					}else if (c == 9) {
						post_code = cell.getStringCellValue();
						tempUserKB.setPostCode(post_code);
					}else if (c == 10) {
						remark = cell.getStringCellValue();
						tempUserKB.setRemark(remark);
					}else if (c == 11) {
						school = cell.getStringCellValue();
						school = FieldDictUtil.get(Constant.APPID, "reg_exam_site", "school", school);
						tempUserKB.setSchool(school);
					}else if (c == 12) {
						school_code = cell.getStringCellValue();

						tempUserKB.setSchoolCode(school_code);
					}else if (c == 13) {
						teach_code = cell.getStringCellValue();
						tempUserKB.setTeachCode(teach_code);
					}else if (c == 14) {
						leader = cell.getStringCellValue();
						tempUserKB.setLeader(leader);
					}else if (c == 15) {
						standard = cell.getStringCellValue();
						standard = FieldDictUtil.get(Constant.APPID, "reg_exam_site", "standard", standard);
						tempUserKB.setStandard(standard);
					}else if (c == 16) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "reg_exam_site", "status", status);
						tempUserKB.setStatus(status);
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

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(ExamSiteDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
					FieldDictDO fieldDict = new FieldDictDO();
					fieldDict.setAppid(Constant.APPID);
					fieldDict.setTableName("reg_exam_site_nzxy");
					fieldDict.setFieldName("id");
					fieldDict.setFieldValue(courseClassify.getId()+"");
					fieldDict.setFieldMean(courseClassify.getName());
					fieldDictService.saveCache(fieldDict);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
	
}
