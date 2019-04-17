package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.CollegeDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSpecialityRegDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.center.student.dao.StudentDao;
import com.hxy.nzxy.stexam.center.student.dao.StudentSpecialityDao;
import com.hxy.nzxy.stexam.center.sys.dao.SystemStudentDao;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.region.dao.RegionRegionDao;
import com.hxy.nzxy.stexam.school.student.dao.StudentSpecialityStudentDao;
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

import com.hxy.nzxy.stexam.school.student.dao.StudentStudentDao;
import com.hxy.nzxy.stexam.school.student.service.StudentStudentService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentStudentServiceImpl implements StudentStudentService {
	@Autowired
	private StudentStudentDao studentStudentDao;
	@Autowired
	private CollegeDao collegeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentSpecialityDao studentSpecialityDao;
    @Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private StudentSpecialityStudentDao studentSpecialityStudentDao;
	@Autowired
	private SystemStudentDao systemStudentDao;
	@Autowired
	private SchoolSpecialityRegDao schoolSpecialityRegDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Override
	public StudentDO get(String id){
		return studentStudentDao.get(id);
	}

	@Override
	public int updateNMD(StudentDO studentStudent){
		return studentStudentDao.updateNMD(studentStudent);
	}

	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentStudentDao.count(map);
	}
	
	@Override
	public int save(StudentDO studentStudent,StudentSpecialityDO studentSpeciality){

		//報名類型
		studentStudent.setClassify("b");
		//获取组织代码
		CollegeDO collegeDO = collegeDao.get(studentStudent.getCollegeid() + "");
		studentStudent.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
		//获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(studentStudent.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentStudent.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentStudent.setRegionid(regionDO.getParentid());
		//在籍
		studentStudent.setStatus("a");
		//待审核
		studentStudent.setAuditStatus("a");
		//确认状态待确认
		studentStudent.setConfirmStatus("a");
        studentStudent.setType("b");
        studentStudent.setKjh(studentStudent.getStudentid().substring(4,7));
        studentStudent.setId(studentStudent.getStudentid());
		//学生基本信息
		studentStudentDao.save(studentStudent);
		//报考信息
        studentSpeciality.setRegionid(studentStudent.getRegionid());
        studentSpeciality.setChildRegionid(studentStudent.getChildRegionid());
        studentSpeciality.setSchoolid(studentStudent.getSchoolid());
        studentSpeciality.setStatus("a");
        studentSpeciality.setAuditStatus("a");
        studentSpeciality.setGradAuditStatus("a");
		//获取招生年份和季节; school_speciality_regid
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpeciality.getSchoolSpecialityRegid());
        //专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpeciality.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpeciality.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		if(schoolSpecialityRegDO!=null){
			studentSpeciality.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpeciality.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}

		studentSpecialityStudentDao.save(studentSpeciality);
		return 1;
	}



	@Override
        public int update(StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
            //获取组织代码
            CollegeDO collegeDO = collegeDao.get(studentStudent.getCollegeid() + "");
            studentStudent.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
            //获取地区代码
            TeachSiteDO teachSiteDO = teachSiteDao.get(studentStudent.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentStudent.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentStudent.setRegionid(regionDO.getParentid());
		studentStudent.setId(studentSpeciality.getStudentid());
		//学生基本信息
 		studentStudentDao.update(studentStudent);
		//报考信息
		studentSpeciality.setStudentid(studentStudent.getId());
		studentSpeciality.setRegionid(studentStudent.getRegionid());
		studentSpeciality.setChildRegionid(studentStudent.getChildRegionid());
		studentSpeciality.setSchoolid(studentStudent.getSchoolid());
		//获取招生年份和季节; school_speciality_regid
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpeciality.getSchoolSpecialityRegid());
		//专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpeciality.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpeciality.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		if(schoolSpecialityRegDO!=null){
			studentSpeciality.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpeciality.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}
		studentSpecialityStudentDao.update(studentSpeciality);


		return 1;
	}
	
	@Override
	public int remove(String id){
		return studentStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentStudentDao.batchRemove(ids);
	}

    @Override
    public String batchImport(String fileName, MultipartFile file,String teachid,String collegeid) {

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
            return readExcelValue(wb,tempFile,teachid,collegeid);
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

    @Override
    public void saveBatch(List<StudentDO> userKnowledgeBaseList) {
        studentDao.saveBatch(userKnowledgeBaseList);
    }


    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private String readExcelValue(Workbook wb,File tempFile,String teachid,String collegeid) throws ParseException {

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
        List<StudentSpecialityDO> userKnowledgeBaseList1 = new ArrayList<>();
        StudentSpecialityDO tempUserKB1;
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
            tempUserKB1 = new StudentSpecialityDO();
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
            String studentid = "";
            String specialityid="";
            String specialityName="";
            String education="";
            String grad_school="";
            String grad_certificate="";
            String grad_specialityid="";

            String schoolid = "";
            String teach_siteid = "";
            String reg_year = "";
            String child_regionid = "";
            String reg_season = "";
            String audit_status = "";
            String speciality_recordid = "";
            //java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                cell.setCellType(CellType.STRING);
                if (null != cell) {
                    if (c == 0) {
                        name = cell.getStringCellValue();
                        if (StringUtils.isEmpty(name)) {
                            rowMessage += "姓名不能为空；";
                        } else if (name.length() > 50) {
                            rowMessage += "姓名的长度不能超过50；";
                        }
                        tempUserKB.setName(name);
                    } else if (c == 1) {
                        pinyin = cell.getStringCellValue();
                        if (StringUtils.isEmpty(pinyin)) {
                            rowMessage += "拼音不能为空；";
                        } else if (pinyin.length() > 50) {
                            rowMessage += "拼音的长度不能超过50；";
                        }
                        tempUserKB.setPinyin(pinyin);
                    }  else if (c == 2) {
                        homeType = cell.getStringCellValue();
                        homeType = FieldDictUtil.get(Constant.APPID, "stu_student", "home_type", homeType);

                        if (StringUtils.isEmpty(homeType)) {
                            //rowMessage += "户籍类别不能为空,或未找到对应的层次；";
                        } else if (homeType.length() > 10) {
                            rowMessage += "户籍的长度不能超过10；";
                        }
                        tempUserKB.setHomeType(homeType);
                    } else if (c == 3) {
                        politics = cell.getStringCellValue();
                        politics = FieldDictUtil.get(Constant.APPID, "stu_student", "politics", politics);
                        if (StringUtils.isEmpty(politics)) {
                            //	rowMessage += "政治面貌不能为空,或为找到对应的层次类型；";
                        } else if (politics.length() > 10) {
                            rowMessage += "政治面貌的长度不能超过10；";
                        }
                        tempUserKB.setPolitics(politics);
                    } else if (c == 4) {
                        nation = cell.getStringCellValue();
                        nation = FieldDictUtil.get(Constant.APPID, "stu_student", "nation", nation);
                        if (StringUtils.isEmpty(nation)) {
                            rowMessage += "民族不能为空,或未找到对应的委托类型；";
                        } else if (nation.length() > 10) {
                            rowMessage += "民族的长度不能超过10；";
                        }
                        tempUserKB.setNation(nation);
                    } else if (c == 5) {
                        profession = cell.getStringCellValue();
                        profession = FieldDictUtil.get(Constant.APPID, "stu_student", "profession", profession);

                        if (profession.length() > 10) {
                            rowMessage += "职业的长度过长；";
                        }
                        tempUserKB.setProfession(profession);
                    }else if (c == 6) {
                        nativePlace = cell.getStringCellValue();
                        if (StringUtils.isEmpty(nativePlace)) {
                            //rowMessage += "籍贯；";
                        } else if (nation.length() > 20) {
                            rowMessage += "籍贯的长度不能超过20；";
                        }
                        tempUserKB.setNativePlace(nativePlace);
                    } else if (c == 7) {
                        certificateType = cell.getStringCellValue();
                        certificateType = FieldDictUtil.get(Constant.APPID, "stu_student", "certificate_type", certificateType);
                        if (StringUtils.isEmpty(certificateType)) {
                        } else if (certificateType.length() > 20) {
                            rowMessage += "证件类别有误；";
                        }
                        tempUserKB.setCertificateType(certificateType);
                    } else if (c == 8) {
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

                    } else if (c == 9) {
                        phone = cell.getStringCellValue();
                        if (StringUtils.isEmpty(phone)) {
                            //固定电话
                        } else if (phone.length() > 50) {
                            rowMessage += "固定电话长度不能超过20；";
                        }
                        ;
                        tempUserKB.setPhone(phone);
                    } else if (c == 10) {
                        mphone = cell.getStringCellValue();
                        if (StringUtils.isEmpty(mphone)) {
                            //移动电话
                        } else if (mphone.length() > 50) {
                            rowMessage += "移动电话长度不能超过20；";
                        }
                        ;
                        tempUserKB.setMphone(mphone);
                    } else if (c == 11) {
                        address = cell.getStringCellValue();
                        if (StringUtils.isEmpty(address)) {
                            //通讯地址
                        } else if (address.length() > 300) {
                            rowMessage += "通讯地址长度不能超过300；";
                        }
                        ;
                        tempUserKB.setAddress(address);
                    } else if (c == 12) {
                        postCode = cell.getStringCellValue();
                        if (StringUtils.isEmpty(postCode)) {
                            //邮编
                        } else if (postCode.length() > 10) {
                            rowMessage += "邮编长度不能超过10；";
                        }
                        ;
                        tempUserKB.setPostCode(postCode);
                    } else if (c == 13) {
                        email = cell.getStringCellValue();
                        if (StringUtils.isEmpty(email)) {
                            //电子邮箱
                        } else if (email.length() > 200) {
                            rowMessage += "电子邮箱长度不能超过10；";
                        }
                        ;
                        tempUserKB.setEmail(email);
                    }else if (c == 14) {
                        pic = cell.getStringCellValue();
                        if (StringUtils.isEmpty(pic)) {
                            //照片
                        }else if (pic.length() > 100) {
                            rowMessage += "照片地址长度不能超过100；";
                        };
                        tempUserKB.setPic(pic);
                    }else if (c == 15) {
                        idcardPic = cell.getStringCellValue();
                        if (StringUtils.isEmpty(idcardPic)) {
                            //照片
                        }else if (idcardPic.length() > 100) {
                            rowMessage += "身份证照片地址长度不能超过100；";
                        };
                        tempUserKB.setIdcardPic(idcardPic);
                    }
                  else if (c == 16) {
                        specialityid = cell.getStringCellValue();
                        if (StringUtils.isEmpty(specialityid)) {
                            rowMessage += "专业不能为空；";
                        } else if (specialityid.length() > 20) {
                            rowMessage += "专业的长度不能超过20；";
                        }
                        tempUserKB1.setSpecialityid(specialityid);
                    } else if (c == 17) {
                        specialityName = cell.getStringCellValue();
                        if (StringUtils.isEmpty(specialityName)) {
                            rowMessage += "专业名称不能为空；";
                        } else if (specialityName.length() > 50) {
                            rowMessage += "专业名称长度不能超过50；";
                        }

                    } else if (c == 18) {
                        education = cell.getStringCellValue();
                        education = FieldDictUtil.get(Constant.APPID, "stu_student_speciality", "education", education);
                        if (StringUtils.isEmpty(education)) {
                            //
                            rowMessage += "原学历不能为空；";
                        }
                        if (education.length() > 1) {
                            rowMessage += "原学历的长度过长；";
                        }
                        tempUserKB1.setEducation(education);
                    } else if (c == 19) {
                        grad_school = cell.getStringCellValue();
                        if (StringUtils.isEmpty(grad_school)) {
                            //
                        } else if (grad_school.length() > 20) {
                            rowMessage += "毕业学校的长度不能超过20；";
                        }

                        tempUserKB1.setGradSchool(grad_school);
                    } else if (c == 20) {
                        grad_certificate = cell.getStringCellValue();
                        if (StringUtils.isEmpty(grad_certificate)) {

                        } else if (nation.length() > 20) {
                            rowMessage += "毕业证书号不能超过20；";
                        }
                        tempUserKB1.setGradCertificate(grad_certificate);
                    } else if (c == 21) {
                        grad_specialityid = cell.getStringCellValue();
                        grad_specialityid = FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", grad_specialityid);
                        if (StringUtils.isEmpty(grad_specialityid)) {
                        } else if (grad_specialityid.length() > 20) {
                            rowMessage += "原学专业；";
                        }
                        tempUserKB1.setGradSpecialityid(grad_specialityid);
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
                tempUserKB1.setOperator(ShiroUtils.getUserId().toString());
                Calendar now = Calendar.getInstance();
                //获取地区编码
                //查询出考试考区编号 考试区县编号 获取地区代码
                TeachSiteDO teachSiteDO = teachSiteDao.get(Long.valueOf(teachid));
                SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
                RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());

                String regionCode=regionDO.getCode();
                String  userName = ShiroUtils.getUser().getName();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH) + 1 ;
                int year1=year-2000;
                String month1="";
                if(month<4){
                    month1="1";
                }else if(month>=4&&month<10){
                    month1="2";
                } else{
                    month1="1";
                    year1= year1+1;
                }
                //年度码
              String  yearNumber=year1+month1;
                //序列号码  改成地区码啦
                SystemStudentDO byUserName = systemStudentDao.getByUserName(regionCode);
                if(byUserName==null){
                    byUserName=new SystemStudentDO();
                    byUserName.setUserName(regionCode);
                    byUserName.setOperator(ShiroUtils.getUserId().toString());
                    byUserName.setUpdateDate(new Date().toString());
                    byUserName.setValue(1);
                    systemStudentDao.save(byUserName);
                }
                String orderid=String.valueOf(byUserName.getValue()+100000).substring(1);
                //准考证号码
                studentid =regionCode+yearNumber+orderid;
                //序列号自增
                byUserName.setValue(1+byUserName.getValue());
                systemStudentDao.update(byUserName);
                    tempUserKB.setId(studentid);
                    tempUserKB.setType("b");
                    tempUserKB.setClassify("b");
                    tempUserKB.setStatus("a");
                tempUserKB.setCollegeid(Long.valueOf(collegeid));
                CollegeDO collegeDO = collegeDao.get(tempUserKB.getCollegeid() + "");
                tempUserKB.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
                String specialityName1= FieldDictUtil.get(Constant.APPID, "pla_speciality_nzxy", "id", specialityid);
                String direction=specialityName.substring(specialityName1.length(),specialityName.length());
                speciality_recordid = FieldDictUtil.get(Constant.APPID, "pla_speciality_record_nzxy", "specialityid_direction", specialityid+direction);
                tempUserKB1.setSpecialityRecordid(Long.valueOf(speciality_recordid));
                tempUserKB1.setSchoolid(Long.valueOf(collegeDO.getSchoolid()));
                //获取地区代码
                tempUserKB.setChildRegionid(schoolSiteDO.getRegionid());
                tempUserKB.setRegionid(regionDO.getParentid());
                tempUserKB.setTeachSiteid(Long.valueOf(teachid));

                tempUserKB.setKjh(yearNumber);
                tempUserKB1.setChildRegionid(schoolSiteDO.getRegionid());
                tempUserKB1.setRegionid(regionDO.getParentid());
                tempUserKB1.setStatus("a");
                tempUserKB1.setCollegeid(Long.valueOf(collegeid));
                tempUserKB1.setTeachSiteid(Long.valueOf(teachid));

                //待审核
                tempUserKB.setAuditStatus("a");
                //确认状态待确认
                tempUserKB.setConfirmStatus("a");


                //报考信息
                tempUserKB1.setStudentid(tempUserKB.getId());

                tempUserKB1.setAuditStatus("a");
                tempUserKB1.setGradAuditStatus("a");
                userKnowledgeBaseList.add(tempUserKB);
                userKnowledgeBaseList1.add(tempUserKB1);
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
            studentSpecialityDao.saveBatch(userKnowledgeBaseList1);
            errorMsg = "导入成功，共" + userKnowledgeBaseList.size() + "条数据！";
        }
        return errorMsg;
    }

}
