package com.hxy.nzxy.stexam.center.school.service.impl;

import com.hxy.nzxy.stexam.center.school.dao.SchoolDao;
import com.hxy.nzxy.stexam.center.school.service.SchoolService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SchoolDO;
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
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolDao schoolDao;
    @Autowired
    private FieldDictService fieldDictService;
	@Override
	public SchoolDO get(Long id) {
		return schoolDao.get(id);
	}

	@Override
	public List<SchoolDO> list(Map<String, Object> map) {
		return schoolDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return schoolDao.count(map);
	}

	@Override
	public int save(SchoolDO school) {
		return schoolDao.save(school);
	}

	@Override
	public int update(SchoolDO school) {
		return schoolDao.update(school);
	}

	@Override
	public int remove(Long id) {
		return schoolDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids) {
		return schoolDao.batchRemove(ids);
	}

	@Override
	public List<SchoolDO> serchSchoolAll() {
		return schoolDao.serchSchoolAll();
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
			return readExcelValue(wb, tempFile , regionid);
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
	private String readExcelValue(Workbook wb, File tempFile , String regionid) throws ParseException {

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
		List<SchoolDO> userKnowledgeBaseList = new ArrayList<>();
		SchoolDO tempUserKB;

		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);




            if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new SchoolDO();

			String code = "";
            String  name = "";
			String bcode = "";
            String bSendUnit = "";
            String bSendDate = "";
            String zcode = "";
            String zSendUnit = "";
            String zSendDate = "";
            String type = "";
            String legalPerson = "";
            String legalPersionDuty = "";
            String jTeacherNum = "";
            String zTeacherNum = "";
            String managerNum = "";
            String allNum = "";
            String charger = "";
            String address = "";
            String postCode = "";
            String phone = "";
            String mphone = "";
            String fax = "";
            String email = "";
            String status = "";

			//java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);

                if (!StringUtils.isEmpty(regionid)){
                    tempUserKB.setRegionid(CommonUtil.StringToLong(regionid,0));
                }else{
                    rowMessage += "所选考区不能为空；";
                }

				if (null != cell) {
                    if (c == 0) {
                        code = cell.getStringCellValue();
                        if (StringUtils.isEmpty(code)) {
                            rowMessage += "课程编码不能为空；";
                        } else if (code.length() > 20) {
                            rowMessage += "课程编码的长度不能超过20；";
                        }
                        tempUserKB.setCode(code);
                    }else if (c == 1){
                            name = cell.getStringCellValue();
                            if (StringUtils.isEmpty(name)){
                                rowMessage += "助学组织名称不能为空";
                            }else if (name.length() > 50){
                                rowMessage += "助学组织名称长度不能超过50";
                            }
                            tempUserKB.setName(name);
                     }
                              else if (c == 2){
                            bcode = cell.getStringCellValue();
                            if (StringUtils.isEmpty(bcode)){
                                rowMessage += "办学许可证编号不能为空";
                            }else if (bcode.length() > 30){
                                rowMessage += "办学许可证编号长度不能超过30";
                            }
                            tempUserKB.setBCode(bcode);
                        }else if (c == 3) {
                            bSendUnit = cell.getStringCellValue();
                            if (StringUtils.isEmpty(bSendUnit)) {
                                rowMessage += "办学许可证发放单位不能为空";
                            } else if (bSendUnit.length() > 50) {
                                rowMessage += "办学许可证发放单位长度不能超过50";
                            }
                                tempUserKB.setBSendUnit(bSendUnit);
                        }else if (c == 4) {
						bSendDate = cell.getStringCellValue();
						if (StringUtils.isEmpty(bSendDate)) {
							rowMessage += "办学许可证发放日期不能为空";
						}
						tempUserKB.setBSendDate(CommonUtil.stringToDate(bSendDate,null));
					}else if (c == 5){
                                zcode = cell.getStringCellValue();
                                if (zcode.length() > 30){
                                    rowMessage += "助学许可证编号长度不能超过30";
                                }
                                tempUserKB.setZCode(zcode);
                        }else if (c == 6){
                                zSendUnit  = cell.getStringCellValue();
                                if (zSendUnit.length() > 50){
                                    rowMessage += "助学许可证发放单位长度不能超过50";
                                }
                                tempUserKB.setZSendUnit(zSendUnit);
                        }else if (c == 7){
                                zSendDate = cell.getStringCellValue();
                                tempUserKB.setZSendDate(CommonUtil.stringToDate(zSendDate,null));
                                }
                         else if (c == 8){
                                type = cell.getStringCellValue();
                                if (StringUtils.isEmpty(type)){
                                    rowMessage += "助学主体类型不能为空";
                                }
                        type = FieldDictUtil.get(Constant.APPID,"sch_school","type",type);
                                tempUserKB.setType(type);
                    }else if (c == 9){
                        legalPerson = cell.getStringCellValue();
                        if (StringUtils.isEmpty(legalPerson)){
                            rowMessage += "法人名称不能为空";
                        }else if (legalPerson.length() > 20){
                            rowMessage += "法人名称长度不能超过20";
                        }tempUserKB.setLegalPerson(legalPerson);
                    }
                              else if (c == 10){
                        legalPersionDuty = cell.getStringCellValue();
                        if (legalPersionDuty.length() > 30){
                            rowMessage += "法人职务长度不能超过30";
                        }tempUserKB.setLegalPersonDuty(legalPersionDuty);
                    }else if (c == 11){
                        jTeacherNum = cell.getStringCellValue();
                        if (jTeacherNum.length() > 12){
                            rowMessage += "兼职教学人员长度不能超过11";
                        }tempUserKB.setJTeacherNum(Integer.valueOf(jTeacherNum));
                    }else if (c == 12){
                        zTeacherNum = cell.getStringCellValue();
                        if (zTeacherNum.length() > 11){
                            rowMessage += "专职教学人员长度不能超过11";
                        }tempUserKB.setZTeacherNum(Integer.valueOf(zTeacherNum));
                    }else if (c == 13) {
                        managerNum = cell.getStringCellValue();
                        if (StringUtils.isEmpty(managerNum)) {
                            rowMessage += "管理人员总数不能为空";
                        } else if (managerNum.length() > 17) {
                            rowMessage += "管理人员总数长度不能超过11";
                        }
                        tempUserKB.setManagerNum(Integer.valueOf(managerNum));
                    }else if (c == 14) {
                        allNum = cell.getStringCellValue();
                        if (StringUtils.isEmpty(allNum)) {
                            rowMessage += "教学和管理人员数不能为空";
                        } else if (allNum.length() > 11) {
                            rowMessage += "教学和管理人员数长度不能超过11";
                        }
                        tempUserKB.setAllNum(Integer.valueOf(allNum));
                    }else if (c == 15){
                        charger = cell.getStringCellValue();
                        if (StringUtils.isEmpty(charger)){
                            rowMessage += "负责人不能为空";
                        }else if (charger.length() > 30){
                            rowMessage += "负责人长度不能超过30";
                        }tempUserKB.setCharger(charger);
                    }else if (c == 16){
                        address = cell.getStringCellValue();
                        if (address.length() > 100){
                            rowMessage += "联系地址长度不能超过100";
                        }tempUserKB.setAddress(address);
                    }else if (c == 17){
                        postCode = cell.getStringCellValue();
                        if (postCode.length() > 6){
                            rowMessage += "邮政编码长度不能超过6";
                        }tempUserKB.setPostCode(postCode);
                    }else if (c == 18){
                        phone = cell.getStringCellValue();
                        if (phone.length() > 20){
                            rowMessage +="固定电话长度不能超过20";
                        }tempUserKB.setPhone(phone);
                    }
                    else if (c == 19){
                        mphone = cell.getStringCellValue();
                        if (mphone.length() > 20){
                            rowMessage += "移动电话长度不能超过20";
                        }tempUserKB.setMphone(mphone);
                    }
                    else if (c == 20){
                        fax = cell.getStringCellValue();
                        if (fax.length() > 50){
                            rowMessage += "传真长度不能超过50";
                        }tempUserKB.setFax(fax);
                    }else if (c == 21){
                        email = cell.getStringCellValue();
                        if (email.length() > 100){
                            rowMessage += "邮件长度不能超过100";
                        }tempUserKB.setEmail(email);
                    }
                    else if (c == 22){
                        status = cell.getStringCellValue();
                        status = FieldDictUtil.get(Constant.APPID,"sch_school","status",status);
                        if (StringUtils.isEmpty(status)){
                            rowMessage += "状态不能为空";
                        }
                        tempUserKB.setStatus(status);
                    }else {
                        rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
                    }
				}
			}

			/**/
				//拼接每行的错误提示
				if (!StringUtils.isEmpty(rowMessage)) {
					errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
				} else {
					tempUserKB.setOperator(ShiroUtils.getUserId().toString());
					userKnowledgeBaseList.add(tempUserKB);
				}
			}

			//删除上传的临时文件
			if (tempFile.exists()) {
				tempFile.delete();
			}
			if (StringUtils.isEmpty(errorMsg)) {
                //验证上传数据在数据库是否已存在
                List<SchoolDO> list = schoolDao.listCZ(userKnowledgeBaseList);

                if (list.size() > 0) {
                    errorMsg = "数据重复，重复的编号为";
                    for (SchoolDO spe : list) {
                        errorMsg += spe.getId() + ",";
                    }
                    errorMsg += "数据库已存在,不能重复添加";
                }
            }

				//全部验证通过才导入到数据库
				if (StringUtils.isEmpty(errorMsg)) {
					for (SchoolDO schoolDO : userKnowledgeBaseList) {
						this.save(schoolDO);
                        FieldDictDO fieldDict = new FieldDictDO();
                        fieldDict.setAppid(Constant.APPID);
                        fieldDict.setTableName("sch_school_nzxy");
                        fieldDict.setFieldName("id");
                        fieldDict.setFieldValue(schoolDO.getId()+"");
                        fieldDict.setFieldMean(schoolDO.getName());
                        fieldDictService.saveCache(fieldDict);
                        fieldDict.setFieldName("code");
                        fieldDict.setFieldValue(schoolDO.getId()+"");
                        fieldDict.setFieldMean(schoolDO.getCode());
                        fieldDictService.saveCache(fieldDict);
					}
					errorMsg = "导入成功，共" + userKnowledgeBaseList.size() + "条数据！";
				}
				return errorMsg;
			}
		}