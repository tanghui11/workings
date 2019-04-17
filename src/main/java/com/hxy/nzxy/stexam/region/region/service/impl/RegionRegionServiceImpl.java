package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.center.region.service.impl.RegionServiceImpl;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import com.hxy.nzxy.stexam.region.region.dao.RegionRegionDao;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.region.region.service.RegionRegionService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class RegionRegionServiceImpl implements RegionRegionService {
	@Autowired
	private RegionRegionDao regionRegionDao;
	
	@Override
	public RegionDO get(Long id){
		return regionRegionDao.get(id);
	}
	
	@Override
	public List<RegionDO> list(Map<String, Object> map){
		return regionRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return regionRegionDao.count(map);
	}
	
	@Override
	public int save(RegionDO regionRegion){
		return regionRegionDao.save(regionRegion);
	}
	
	@Override
	public int update(RegionDO regionRegion){
		return regionRegionDao.update(regionRegion);
	}
	
	@Override
	public int remove(Long id){
		return regionRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return regionRegionDao.batchRemove(ids);
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

	@Autowired
	private RegionServiceImpl regionServiceImpl;
	/**
	 * 解析Excel里面的数据
	 * @param wb Workbook的方式
	 * @param tempFile 临时文件
	 * @return
	 */
	private String readExcelValue(Workbook wb, File tempFile) throws ParseException {

		String errorMsg = "";
		Sheet sheet=wb.getSheetAt(0);
		int totalRows=sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<RegionDO> userKnowledgeBaseList = new ArrayList<>();
		RegionDO tempUserKB ;
		String br = "<br/>";
		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new RegionDO();//定义了一个空的courseFreeCenterDO

			String parentid = "";
			String code ="";
			String type ="";
			String name ="";
			String parent_name ="";
			String pinyin ="";
			String linkman ="";
			String address ="";
			String post_code ="";
			String phone ="";
			String mphone ="";
			String fax ="";
			String email ="";
			String photo_flag ="";
			String exam_transfer ="";
			int room_size = 0;
			String exam_msg ="";
			String seq ="00";

			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						parentid = cell.getStringCellValue();
						if (parentid == null || "".equals(parentid) ) {
							rowMessage += "父考区不能为空；";
						}
						tempUserKB.setParentid(Long.parseLong(parentid));
					} else if (c == 1) {
						code = cell.getStringCellValue();
						if (StringUtils.isEmpty(code)) {
							rowMessage += "考区代码不能为空；";
						} else if (code.length() > 20) {
							rowMessage += "考区代码的长度不能超过20；";
						}
						tempUserKB.setCode(code);
					} else if (c == 2) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "reg_region", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空；";
						}
						tempUserKB.setType(type);
					} else if (c == 3) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "名称不能为空";
						}else if(name.length() > 100){
							rowMessage += "名称不可以超过50个字";
						}
						tempUserKB.setName(name);
					}else if (c == 4) {
						parent_name = cell.getStringCellValue();

						tempUserKB.setParentName(parent_name);
					}else if (c == 5) {
						pinyin = cell.getStringCellValue();

						if (StringUtils.isEmpty(pinyin)) {
							tempUserKB.setPinyin(regionServiceImpl.getPingYin(name));
						}else{
							tempUserKB.setPinyin(pinyin);
						}
					}else if (c == 6) {
						linkman = cell.getStringCellValue();
						tempUserKB.setLinkman(linkman);
					}else if (c == 7) {
						address = cell.getStringCellValue();

						tempUserKB.setAddress(address);
					}else if (c == 8) {
						post_code = cell.getStringCellValue();

						tempUserKB.setPostCode(post_code);
					}else if (c == 9) {
						phone = cell.getStringCellValue();

						tempUserKB.setPhone(phone);
					}else if (c == 10) {
						mphone = cell.getStringCellValue();

						tempUserKB.setMphone(mphone);
					}else if (c == 11) {
						fax = cell.getStringCellValue();

						tempUserKB.setFax(fax);
					}else if (c == 12) {
						email = cell.getStringCellValue();

						tempUserKB.setEmail(email);
					}else if(c == 13){
						photo_flag = cell.getStringCellValue();
						photo_flag = FieldDictUtil.get(Constant.APPID, "reg_region", "photo_flag", photo_flag);
						if (StringUtils.isEmpty(photo_flag)) {
							rowMessage += "是否有摄像头不能为空";
						}else if(photo_flag.length() > 1){
							rowMessage += "是否有摄像头的长度不能超过1";
						}
						tempUserKB.setPhotoFlag(photo_flag);
					} else if (c == 14) {
						exam_transfer = cell.getStringCellValue();

						tempUserKB.setExamTransfer(exam_transfer);
					}else if (c == 15) {
						room_size = Integer.parseInt(cell.getStringCellValue());

						tempUserKB.setName(String.valueOf(room_size));
					}else if (c == 16) {
						exam_msg = cell.getStringCellValue();

						tempUserKB.setExamMsg(exam_msg);
					}else if (c == 17) {
						seq = cell.getStringCellValue();

						tempUserKB.setSeq(seq);
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
		//验证上传数据在数据库是否已存在
		List<RegionDO> list=regionRegionDao.listT( userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，名称为";
			for(RegionDO cfc : list){
				errorMsg += cfc.getId()+",";
			}
			errorMsg +="已存在,不能重复添加";
		}



		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(RegionDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
	
}
