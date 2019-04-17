package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.service.StudentTempService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.config.FtpConfig;
import com.hxy.nzxy.stexam.common.utils.*;

import com.hxy.nzxy.stexam.domain.StudentTempDO;
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
import java.util.*;

import com.hxy.nzxy.stexam.center.student.dao.StudentDao;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.center.student.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentTempServiceImpl studentTempServiceImpl;
	@Autowired
	private FtpConfig ftpConfig;
	@Autowired
	private StudentTempService studentTempService;
	@Autowired
	private ZipUtils zipUtils;
	@Override
	public StudentDO get(String id){
		return studentDao.get(id);
	}
	
	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentDao.count(map);
	}
	
	@Override
	public int save(StudentDO student){
		return studentDao.save(student);
	}
	
	@Override
	public int update(StudentDO student){
		return studentDao.update(student);
	}
	
	@Override
	public int remove(String id){
		return studentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentDao.batchRemove(ids);
	}

	@Override
	public int batchUpdateAudit(Long[] ids, String auditStatus) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("auditStatus",auditStatus);
		map.put("operator",ShiroUtils.getUserId().toString());
		return studentDao.batchUpdateAudit(map);
	}

	@Override
	public int updateAudit(Map<String, Object> params) {
		return studentDao.updateAudit(params);
	}

	@Override
	public int batchUpdateTx(Long[] ids, String status) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("status",status);
		map.put("operator",ShiroUtils.getUserId().toString());
		return studentDao.batchUpdateTx(map);
	}

	@Override
	public String batchImport(String fileName, MultipartFile file,String flag) {

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
			return readExcelValue(wb,tempFile,flag);
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
	private String readExcelValue(Workbook wb,File tempFile,String flag) throws ParseException {

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
		List<StudentDO> userKnowledgeBaseList = new ArrayList<>();

		StudentDO tempUserKB;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new StudentDO();

			String id = "";
			String name = "";
			String pinyin = "";
			String gender = "";
			String homeType = "";
			String politics = "";
			String nation = "";
			String profession = "";
			String birthday = "";
			String nativePlace = "";
			String certificateType = "";
			String certificateNo = "";
			String phone = "";
			String mphone = "";
			String address = "";
			String postCode = "";
			String pic = "";
			String idcardPic = "";
			String email = "";
			String regionid = "";
			String childRegionid = "";
			String status = "";
			String auditStatus = "";
			String confirmStatus = "";
			String backOperator = "";
			String backDate = "";
			String school = "";
			String college = "";
			String teachSite = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						id = cell.getStringCellValue();
						if (StringUtils.isEmpty(id)) {
							rowMessage += "准考证号不能为空；";
						} else if (id.length() > 20) {
							rowMessage += "准考证号的长度不能超过20；";
						}
						tempUserKB.setId(id);
						tempUserKB.setKjh(id.substring(4, 7));
					} else if (c == 1) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "名称不能为空；";
						} else if (name.length() > 50) {
							rowMessage += "名称的长度不能超过50；";
						}
						tempUserKB.setName(name);
					} else if (c == 2) {
						pinyin = cell.getStringCellValue();
						if (StringUtils.isEmpty(pinyin)) {
							rowMessage += "拼音不能为空；";
						} else if (pinyin.length() > 50) {
							rowMessage += "拼音的长度不能超过50；";
						}
						tempUserKB.setPinyin(pinyin);
					} else if (c == 3) {
						gender = cell.getStringCellValue();
						gender = FieldDictUtil.get(Constant.APPID, "stu_student", "gender", gender);

						if (gender.length() > 10) {
							rowMessage += "性别的长度不能超过10；";
						}
						tempUserKB.setGender(gender);
					} else if (c == 4) {
						homeType = cell.getStringCellValue();
						homeType = FieldDictUtil.get(Constant.APPID, "stu_student", "home_type", homeType);

						if (StringUtils.isEmpty(homeType)) {
							//rowMessage += "户籍类别不能为空,或未找到对应的层次；";
						} else if (homeType.length() > 10) {
							rowMessage += "户籍的长度不能超过10；";
						}
						tempUserKB.setHomeType(homeType);
					} else if (c == 5) {
						politics = cell.getStringCellValue();
						politics = FieldDictUtil.get(Constant.APPID, "stu_student", "politics", politics);
						if (StringUtils.isEmpty(politics)) {
							//	rowMessage += "政治面貌不能为空,或为找到对应的层次类型；";
						} else if (politics.length() > 10) {
							rowMessage += "政治面貌的长度不能超过10；";
						}
						tempUserKB.setPolitics(politics);
					} else if (c == 6) {
						nation = cell.getStringCellValue();
						nation = FieldDictUtil.get(Constant.APPID, "stu_student", "nation", nation);
						if (StringUtils.isEmpty(nation)) {
							rowMessage += "民族不能为空,或未找到对应的委托类型；";
						} else if (nation.length() > 10) {
							rowMessage += "民族的长度不能超过10；";
						}
						tempUserKB.setNation(nation);
					} else if (c == 7) {
						profession = cell.getStringCellValue();
						profession = FieldDictUtil.get(Constant.APPID, "stu_student", "profession", profession);

						if (profession.length() > 10) {
							rowMessage += "职业的长度过长；";
						}
						tempUserKB.setProfession(profession);
					} else if (c == 8) {
						birthday = cell.getStringCellValue();
						if (StringUtils.isEmpty(birthday)) {
							//
							rowMessage += "出生日期不能为空；";
						}

						tempUserKB.setBirthday(sdf2.parse(birthday));
					} else if (c == 9) {
						nativePlace = cell.getStringCellValue();
						if (StringUtils.isEmpty(nativePlace)) {
							//rowMessage += "籍贯；";
						} else if (nation.length() > 20) {
							rowMessage += "籍贯的长度不能超过20；";
						}
						tempUserKB.setNativePlace(nativePlace);
					} else if (c == 10) {
						certificateType = cell.getStringCellValue();
						certificateType = FieldDictUtil.get(Constant.APPID, "stu_student", "certificate_type", certificateType);
						if (StringUtils.isEmpty(certificateType)) {
						} else if (certificateType.length() > 20) {
							rowMessage += "证件类别；";
						}
						tempUserKB.setCertificateType(certificateType);
					} else if (c == 11) {
						certificateNo = cell.getStringCellValue();
						if (StringUtils.isEmpty(certificateNo)) {
							//证件号码
							rowMessage += "身份证号码不能为空；";
						} else if (certificateType.length() > 20) {
							rowMessage += "身份证号码长度不能超过20；";
						} else {
							tempUserKB.setCertificateNo(certificateNo);
							tempUserKB.setBirthday(sdf2.parse(certificateNo.substring(6, 10) + "-" + certificateNo.substring(10, 12) + "-" + certificateNo.substring(12, 14)));
							tempUserKB.setGender(certificateNo.substring(17, 18));
						}

					} else if (c == 12) {
						phone = cell.getStringCellValue();
						if (StringUtils.isEmpty(phone)) {
							//固定电话
						} else if (phone.length() > 50) {
							rowMessage += "固定电话长度不能超过20；";
						}
						;
						tempUserKB.setPhone(phone);
					} else if (c == 13) {
						mphone = cell.getStringCellValue();
						if (StringUtils.isEmpty(mphone)) {
							//移动电话
						} else if (mphone.length() > 50) {
							rowMessage += "移动电话长度不能超过20；";
						}
						;
						tempUserKB.setMphone(mphone);
					} else if (c == 14) {
						address = cell.getStringCellValue();
						if (StringUtils.isEmpty(address)) {
							//通讯地址
						} else if (address.length() > 300) {
							rowMessage += "通讯地址长度不能超过300；";
						}
						;
						tempUserKB.setAddress(address);
					} else if (c == 15) {
						postCode = cell.getStringCellValue();
						if (StringUtils.isEmpty(postCode)) {
							//邮编
						} else if (postCode.length() > 10) {
							rowMessage += "邮编长度不能超过10；";
						}
						;
						tempUserKB.setPostCode(postCode);
					} else if (c == 16) {
						email = cell.getStringCellValue();
						if (StringUtils.isEmpty(email)) {
							//电子邮箱
						} else if (email.length() > 200) {
							rowMessage += "电子邮箱长度不能超过10；";
						}
						;
						tempUserKB.setEmail(email);
					} else if (c == 17) {
						status = cell.getStringCellValue();
						status = FieldDictUtil.get(Constant.APPID, "stu_student", "status", status);

						if (StringUtils.isEmpty(status)) {
							rowMessage += "状态不能为空；";
						}
						tempUserKB.setStatus(status);
					} else if (c == 18) {
						regionid = cell.getStringCellValue();

						if (StringUtils.isEmpty(regionid)) {
							rowMessage += "报考地市不能为空；";//报考地市
						} else {
							//regionid = regionid.substring(0, 4);
							regionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", regionid.substring(0,4));
							if (StringUtils.isEmpty(regionid)) {
								rowMessage += "未找到对应的报考地市；";//报考地市
							}else{
								tempUserKB.setRegionid(Long.valueOf(regionid));
							}

						};

					} else if (c == 19) {
						childRegionid = cell.getStringCellValue();
						//childRegionid = childRegionid.substring(0, 4);
						if (StringUtils.isEmpty(childRegionid)) {
							rowMessage += "报考县区不能为空；";//报考县区//
						}else{
							childRegionid = FieldDictUtil.get(Constant.APPID, "reg_region_nzxy", "code", childRegionid.substring(0,4));

							if (StringUtils.isEmpty(childRegionid)) {
								rowMessage += "报考县区编号未找到；";//报考县区//
							}else{
								tempUserKB.setChildRegionid(Long.valueOf(childRegionid));

							}

						}
						;
					} else if (c == 20) {
						confirmStatus = cell.getStringCellValue();
						confirmStatus = FieldDictUtil.get(Constant.APPID, "stu_student", "confirm_status", confirmStatus);

						if (StringUtils.isEmpty(confirmStatus)) {
							rowMessage += "确认状态不能为空；";
						}
						;
						tempUserKB.setConfirmStatus(confirmStatus);
					} else if (c == 21) {
						auditStatus = cell.getStringCellValue();
						auditStatus = FieldDictUtil.get(Constant.APPID, "stu_student", "audit_status", auditStatus);

						if (StringUtils.isEmpty(auditStatus)) {
							rowMessage += "审核状态不能为空；";//审核状态
						}
						;
						tempUserKB.setAuditStatus(auditStatus);
					} else if (c == 22) {
						backOperator = cell.getStringCellValue();
						if (StringUtils.isEmpty(backOperator)) {
							//确认操作员
						}
						;
						//tempUserKB.setBackOperator(backOperator);
					} else if (c == 23) {
						backDate = cell.getStringCellValue();
						if (StringUtils.isEmpty(backDate)) {
							//确认日期
						};

					}else if (c == 24) {
						pic = cell.getStringCellValue();
						if (StringUtils.isEmpty(pic)) {
							//照片
						}else if (pic.length() > 100) {
							rowMessage += "照片地址长度不能超过100；";
						};
						tempUserKB.setPic(pic);
					}else if (c == 25) {
						idcardPic = cell.getStringCellValue();
						if (StringUtils.isEmpty(idcardPic)) {
							//照片
						}else if (idcardPic.length() > 100) {
							rowMessage += "身份证照片地址长度不能超过100；";
						};
						tempUserKB.setIdcardPic(idcardPic);
					}else if (c == 26) {
						school = cell.getStringCellValue();
						if (StringUtils.isEmpty(school)) {
							//组织
							//rowMessage += "组织不能为空；";
						}else{
							school = FieldDictUtil.get(Constant.APPID, "sch_school_nzxy", "code", school.substring(0,4));
							tempUserKB.setSchoolid(Long.valueOf(school));
						};

					}else if (c == 27) {
						college = cell.getStringCellValue();
						if (StringUtils.isEmpty(college)) {
							//学院
						//rowMessage += "学院不能为空；";
						}else{
							college = FieldDictUtil.get(Constant.APPID, "sch_college_nzxy", "code_name", school+college);
							tempUserKB.setCollegeid(Long.valueOf(college));
						};

					}else if (c == 28) {
						teachSite = cell.getStringCellValue();
						if (StringUtils.isEmpty(teachSite)) {
							//教学点
							//rowMessage += "教学点不能为空；";
						}else{
							teachSite = FieldDictUtil.get(Constant.APPID, "sch_teach_site_nzxy", "code_name", college+teachSite);
							tempUserKB.setTeachSiteid(Long.valueOf(teachSite));
						};

					}
				}else {
						rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
					}
				}
				//拼接每行的错误提示
				if (!StringUtils.isEmpty(rowMessage)) {
					errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
				} else {
					tempUserKB.setOperator(ShiroUtils.getUserId().toString());
					if(flag.equals("kaowu")){
						tempUserKB.setType("a");
						tempUserKB.setClassify("c");
					}else if(flag.equals("zhuxue")){
						tempUserKB.setType("b");
						tempUserKB.setClassify("b");
					}
					userKnowledgeBaseList.add(tempUserKB);
				}
			}

			//删除上传的临时文件
			if (tempFile.exists()) {
				tempFile.delete();
			}
			//验证上传数据的身份证在数据库是否已存在
		if (StringUtils.isEmpty(errorMsg)) {
			List<StudentDO> list = studentDao.listCZ(userKnowledgeBaseList);
			if (list.size() > 0) {
				errorMsg = "导入失败，以下身份证号的信息已存在,不能重复添加";
				for (StudentDO stu : list) {
					errorMsg += "," + stu.getCertificateNo();
				}
			}
			List<StudentDO> list1 = studentDao.listCZStudentid(userKnowledgeBaseList);
			if (list1.size() > 0) {
				errorMsg = "导入失败，以下准考证号的信息已存在,不能重复添加";
				for (StudentDO stu : list1) {
					errorMsg += "," + stu.getId();
				}
			}
		}
			//全部验证通过才导入到数据库
			if (StringUtils.isEmpty(errorMsg)) {
				this.saveBatch(userKnowledgeBaseList);

				errorMsg = "导入成功，共" + userKnowledgeBaseList.size() + "条数据！";
			}
			return errorMsg;
		}
	@Override
	public void saveBatch(List<StudentDO> userKnowledgeBaseList) {
		studentDao.saveBatch(userKnowledgeBaseList);
	}
	DbfReadUtil dbfReadUtil = new DbfReadUtil();
	@Override
	public String importInfoBl ( String fileName, MultipartFile file, String type, MultipartHttpServletRequest multipartRequest, MultipartFile multipartFiles, boolean havePhoto)throws IOException, ParseException {
		List<Map<String,String>> list = dbfReadUtil.readDbf(file);
		int num = dbfReadUtil.readDbf1(file);
		return readExcelValueBL(list,num, type,havePhoto);

	}

	@Override
	public List readExcelValueBLList(String fileName, MultipartFile file, String type, MultipartHttpServletRequest multipartRequest, MultipartFile multipartFiles, boolean havePhoto) throws IOException, ParseException {
		List<Map<String,String>> list = dbfReadUtil.readDbf(file);
		int num = dbfReadUtil.readDbf1(file);
		return readExcelValueBLList(list,num, type,havePhoto);
	}


	/**
	 * 解析Excel里面的数据  和图片 并添加
	 * @param wb Workbook的方式
	 * @param tempFile 临时文件
	 * @return
	 */
	private String readExcelValueBL(List<Map<String,String>> list, int num, String type, boolean havePhoto) throws ParseException, IOException {


		List<StudentDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		StudentDO tempUserKB;
		String br = "<br/>";
		List listSet = null;
		Set keys = null;
		String errorMsg = "";

		//循环Excel行数,从第二行开始。标题不入库
		for (int i =0; i<list.size();i++){

			tempUserKB = new StudentDO();
			String ks_zkz    ="";
			String ks_xm     ="";
			String id     ="";

			//循环Excel的列
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			keys = list.get(i).keySet();
			listSet = new ArrayList();
			String rowMessage = "";
			listSet.addAll(keys);
			System.out.println(listSet);
			for (int j =0; j<num;j++){

				if (list.get(i)!= null) {
					if ("id".equals(listSet.get(j))) {
						id = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(id)) {
							rowMessage += "准考证号不能为空；";
						}else if (ks_zkz.length() > 12){
							rowMessage += "准考证号的长度不能超过12";
						}
						tempUserKB.setId(id);

					}else if ("type".equals(listSet.get(j))) {
						type = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(type)) {
						}else if (ks_xm.length() > 64){
						}
						tempUserKB.setType(type);

					}else if ( "name".equals(listSet.get(j))) {
						tempUserKB.setName(list.get(i).get(listSet.get(j)));

					}else if ("ename".equals(listSet.get(j))) {
						tempUserKB.setEname(list.get(i).get(listSet.get(j)));

					}else if ("pinyin".equals(listSet.get(j))) {
						tempUserKB.setPinyin(list.get(i).get(listSet.get(j)));

					}else if ("gender".equals(listSet.get(j))) {
						tempUserKB.setGender(list.get(i).get(listSet.get(j)));

					}else if ("politics".equals(listSet.get(j))) {
						tempUserKB.setHomeType(list.get(i).get(listSet.get(j)));

					}else if ("nation".equals(listSet.get(j))) {
						tempUserKB.setNation(list.get(i).get(listSet.get(j)));

					}else if ("profession".equals(listSet.get(j))) {
						tempUserKB.setProfession(list.get(i).get(listSet.get(j)));

					}else if ("birthday".equals(listSet.get(j))) {
						String birthday = list.get(i).get(listSet.get(j));
						if(birthday!=null&&!birthday.equals("")){
							tempUserKB.setBirthday(new Date(birthday));

						}

					}else if ("native_pla".equals(listSet.get(j))) {
						tempUserKB.setNativePlace(list.get(i).get(listSet.get(j)));

					}else if ("certificat".equals(listSet.get(j))) {
						tempUserKB.setCertificateType(list.get(i).get(listSet.get(j)));

					}else if ("certifica1".equals(listSet.get(j))) {
						tempUserKB.setCertificateNo(list.get(i).get(listSet.get(j)));

					}else if ("phone".equals(listSet.get(j))) {
						tempUserKB.setPhone(list.get(i).get(listSet.get(j)));

					}else if ("mphone".equals(listSet.get(j))) {
						tempUserKB.setMphone(list.get(i).get(listSet.get(j)));

					}else if ("address".equals(listSet.get(j))) {
						tempUserKB.setAddress(list.get(i).get(listSet.get(j)));
					}else if ("post_code".equals(listSet.get(j))) {
						tempUserKB.setPostCode(list.get(i).get(listSet.get(j)));
					}else if ("email".equals(listSet.get(j))) {
						tempUserKB.setEmail(list.get(i).get(listSet.get(j)));

					}else if ("idcard_pic".equals(listSet.get(j))) {
						tempUserKB.setIdcardPic(list.get(i).get(listSet.get(j)));
					}else if ("pic".equals(listSet.get(j))) {
						tempUserKB.setPic(list.get(i).get(listSet.get(j)));

					}else if ("regionid".equals(listSet.get(j))) {
						tempUserKB.setRegionid(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("child_regi".equals(listSet.get(j))) {
						tempUserKB.setChildRegionid(Long.valueOf(list.get(i).get(listSet.get(j))));
					}else if ("schoolid".equals(listSet.get(j))) {
						tempUserKB.setSchoolid(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("collegeid".equals(listSet.get(j))) {
						tempUserKB.setCollegeid(Long.valueOf(list.get(i).get(listSet.get(j))));


					}else if ( "teach_siteid".equals(listSet.get(j))) {
						tempUserKB.setTeachSiteid(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("groupid".equals(listSet.get(j))) {
						tempUserKB.setGroupid(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("key_value".equals(listSet.get(j))) {
						tempUserKB.setKeyValue(list.get(i).get(listSet.get(j)));

					}else if ("password".equals(listSet.get(j))) {
						tempUserKB.setPassword(list.get(i).get(listSet.get(j)));

					}else if ("work_addre".equals(listSet.get(j))) {
						tempUserKB.setWorkAddress(list.get(i).get(listSet.get(j)));

					}else if ("question".equals(listSet.get(j))) {
						tempUserKB.setQuestion(list.get(i).get(listSet.get(j)));

					}else if ("answer".equals(listSet.get(j))) {
						tempUserKB.setAnswer(list.get(i).get(listSet.get(j)));

					}else if ("old_studen".equals(listSet.get(j))) {
						tempUserKB.setOldStudentid(list.get(i).get(listSet.get(j)));

					}else if ("classify".equals(listSet.get(j))) {
						tempUserKB.setClassify(list.get(i).get(listSet.get(j)));
					}else if ("status".equals(listSet.get(j))) {
						tempUserKB.setStatus(list.get(i).get(listSet.get(j)));
					}else if ("audit_stat".equals(listSet.get(j))) {
						tempUserKB.setAuditStatus(list.get(i).get(listSet.get(j)));
					}else if ("confirm_st".equals(listSet.get(j))) {
						tempUserKB.setConfirmStatus(list.get(i).get(listSet.get(j)));
					}else if ("back_opera".equals(listSet.get(j))) {
						tempUserKB.setBackOperator(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("back_date".equals(listSet.get(j))) {
						String  back_date=list.get(i).get(listSet.get(j));
						if(back_date!=null&&!back_date.equals("")){
							tempUserKB.setBackDate(new Date(back_date));

						}

					}else if ("confirm_op".equals(listSet.get(j))) {
						tempUserKB.setConfirmOperator(Long.valueOf(list.get(i).get(listSet.get(j))));

					}else if ("confirm_da".equals(listSet.get(j))) {
						String  confirm_da=list.get(i).get(listSet.get(j));
						if(confirm_da!=null&&!confirm_da.equals("")){
							tempUserKB.setConfirmDate(new Date(confirm_da));
						}


					}else if ("operator".equals(listSet.get(j))) {

						tempUserKB.setOperator(list.get(i).get(listSet.get(j)));

					}else if ("update_dat".equals(listSet.get(j))) {
						tempUserKB.setUpdateDate(list.get(i).get(listSet.get(j)));

					}
					else if ("create_dat".equals(listSet.get(j))) {
						String create_date = list.get(i).get(listSet.get(j));
						if(create_date!=null&&!create_date.equals("")){
							tempUserKB.setCreateDate( new Date(create_date));

						}
					}
					else if ("db_flag".equals(listSet.get(j))) {
						tempUserKB.setDbFlag(Integer.valueOf(list.get(i).get(listSet.get(j))));
					}
					else if ("kjh".equals(listSet.get(j))) {
						tempUserKB.setKjh(list.get(i).get(listSet.get(j)));
					}
					else if ("enabled_fl".equals(listSet.get(j))) {
						tempUserKB.setEnabledFlag(Integer.valueOf(list.get(i).get(listSet.get(j))));
					}
				} else {
					rowMessage += "第" + (j + 1) + "列数据有问题，请仔细检查；";
				}
			}

			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (i + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				tempUserKB.setEnabledFlag(1);
				tempUserKB.setType(type);
				userKnowledgeBaseList.add(tempUserKB);
			}
		}

		//验证上传数据在数据库是否已存在
		List<StudentDO> list1=studentDao.listCZ(userKnowledgeBaseList);
		if(list1.size()>0){
			errorMsg ="导入失败，类型、准考证和姓名分别为";
			for(StudentDO spe : list1){
				errorMsg += spe.getType()+"、"+spe.getId()+"、"+spe.getName();
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
			return errorMsg;
		}

		/*String paths = zipUtils.unzipfile(multipartFiles).get("path").toString();
		File filet = new File(paths);
		*//***************************//*
		File[] files = filet.listFiles();
		String yearNumber = "";
		String regionCode = "";
		String path[] = new String[2];
		String path2 = "";
		*//** 页面控件的文件流* *//*
		MultipartFile multipartFile = null;
		Map map = multipartRequest.getFileMap();
		for (Iterator ii = map.keySet().iterator(); ii.hasNext(); ) {
			Object obj = ii.next();
			multipartFile = (MultipartFile) map.get(obj);
		}
		*//** 获取文件的后缀* *//*
	//	String filename = multipartFile.getOriginalFilename();

		InputStream inputStream;
		inputStream = multipartFile.getInputStream();
		for (int ii = 0; ii < files.length; ii++) {
			File filek = files[ii];
			if (filek.isFile()) {
				for (StudentTempDO item : userKnowledgeBaseList) {
					if (item.getKsZkz() == filek.getName().substring(0, 12) || item.getKsZkz().equals(filek.getName().substring(0, 12))) {
						yearNumber = item.getKsZkz().substring(4, 7);
						regionCode = item.getKsZkz().substring(0, 4);
						path = FtpUtil.pictureUploadByConfig(ftpConfig, item.getKsZkz() + ".jpg", yearNumber + "/" + regionCode, inputStream);
						path2 = path[0];
						item.setPicture(String.valueOf(path2));
					}
				}
			} else {
				File filess[] = filek.listFiles();
				for (int kk = 0; kk < filess.length; kk++) {
					File filel = filess[kk];
					if (filel.isFile()) {
						for (StudentTempDO item : userKnowledgeBaseList) {
							if (item.getKsZkz() == filel.getName().substring(0, 12) || item.getKsZkz().equals(filel.getName().substring(0, 12))) {
								yearNumber = item.getKsZkz().substring(4, 7);
								regionCode = item.getKsZkz().substring(0, 4);
								path = FtpUtil.pictureUploadByConfig(ftpConfig, item.getKsZkz() + ".jpg", yearNumber + "/" + regionCode, inputStream);
								path2 = path[0];
								item.setPicture(path2);
							}
						}
					}
				}
			}
		}*/

	/*	*//*照片库中有的考生才能导入到正式表*//*
		if(havePhoto == true){
			for (StudentTempDO lists : userKnowledgeBaseList){
				if(lists.getPicture() != null && lists.getPicture() != "" && StringUtils.isEmpty(errorMsg)){
					studentTempService.ZSsave(lists);
				}
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}*/
		/*不论照片，所有考生信息都导入到正式表*/
		/*if(havePhoto == false){
			if(StringUtils.isEmpty(errorMsg)){
				for(StudentTempDO courseClassify : userKnowledgeBaseList){
					studentTempServiceImpl.ZSsave(courseClassify);
				}
				errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
			}
		}*/
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
				studentDao.saveBatch(userKnowledgeBaseList);
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

	private  List readExcelValueBLList(List<Map<String,String>> list, int num, String type, boolean havePhoto) throws ParseException, IOException {


		List<Map> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		//StudentDO tempUserKB;
		Map tempUserKB;
		String br = "<br/>";
		List listSet = null;
		Set keys = null;
		String errorMsg = "";

		//循环Excel行数,从第二行开始。标题不入库
		for (int i =0; i<list.size();i++) {

			//tempUserKB = new StudentDO();
			tempUserKB = new HashMap();


			//循环Excel的列
			String rowMessage = "";
			keys = list.get(i).keySet();
			listSet = new ArrayList();
			listSet.addAll(keys);
			for (int j = 0; j < num; j++) {

				if (list.get(i) != null) {
					Iterator<String> it = keys.iterator();
					while (it.hasNext()) {
						String str = it.next();
						if (str.equals(listSet.get(j))) {
							tempUserKB.put(str, list.get(i).get(listSet.get(j)));
						}
					}


				}
			}
			userKnowledgeBaseList.add(tempUserKB);
		}
		return userKnowledgeBaseList;
	}
}
