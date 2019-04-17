package com.hxy.nzxy.stexam.center.school.service.impl;

import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.center.school.service.TeachSiteService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.TeachSiteDO;
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
import java.util.*;


@Service
public class TeachSiteServiceImpl implements TeachSiteService {
	@Autowired
	private TeachSiteDao teachSiteDao;
    @Autowired
    private FieldDictService fieldDictService;
	@Override
	public TeachSiteDO get(Long id){
		return teachSiteDao.get(id);
	}
	
	@Override
	public List<TeachSiteDO> list(Map<String, Object> map){
		return teachSiteDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teachSiteDao.count(map);
	}
	
	@Override
	public int save(TeachSiteDO teachSite){
		return teachSiteDao.save(teachSite);
	}
	
	@Override
	public int update(TeachSiteDO teachSite){
		return teachSiteDao.update(teachSite);
	}
	
	@Override
	public int remove(Long id){
		return teachSiteDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return teachSiteDao.batchRemove(ids);
	}

	@Override
	public int batchUpdateAudit(Long[] ids, String auditStatus) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("status",auditStatus);
		return teachSiteDao.batchUpdateAudit(map);
	}

	@Override
	public int updateAudit(Map<String, Object> params) {
		return teachSiteDao.updateAudit(params);
	}

    //批量导入
    @Override
    public String batchImport(String fileName, MultipartFile file,String collegeId) {

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
            return readExcelValue(wb, tempFile, collegeId);
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
    private String readExcelValue(Workbook wb, File tempFile,String collegeId) throws ParseException {

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
        List<TeachSiteDO> userKnowledgeBaseList = new ArrayList<>();
        TeachSiteDO tempUserKB;

        String br = "<br/>";

        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) {
                errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
                continue;
            }
            tempUserKB = new TeachSiteDO();

           String name = "";
           String schoolSiteName = "";
           String pinyin = "";
           String linkman = "";
           String phone = "";
           String mphone = "";
           String postCode = "";
           String address = "";
            String status = "";
            String schoolSiteid = "";

            //java.text.SimpleDateFormat   formatter   = new SimpleDateFormat( "yyyy-MM-dd ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                cell.setCellType(CellType.STRING);
                if (StringUtils.isEmpty(collegeId)){
                    rowMessage += "请选择助学组织列表";
                }
                tempUserKB.setCollegeid(Long.valueOf(collegeId));
//                if (StringUtils.isEmpty(schoolSiteId)){
//                    rowMessage += "请选择助学组织";
//                }
//                tempUserKB.setSchoolSiteid(Long.valueOf(schoolSiteId));
                if (null != cell) {
                    if (c == 0) {
                        name = cell.getStringCellValue();
                        if (StringUtils.isEmpty(name)) {
                            rowMessage += "学院名称不能为空；";
                        }else if (name.length() > 200){
                            rowMessage += "学院名称长度不能超过200";
                        }
                        tempUserKB.setName(name);
                    }else if (c == 1){
                        schoolSiteName = cell.getStringCellValue();
                        //截取 “ ” 两边的内容
                        String[] str = schoolSiteName.split(" ");
                        String code =  str[0];
                        schoolSiteid = FieldDictUtil.get(Constant.APPID,"sch_school_site_nzxy","code",code);
                        if (StringUtils.isEmpty(schoolSiteid)){
                            rowMessage += "办学地区编号未找到对应数据";
                        }
                        if (StringUtils.isEmpty(schoolSiteName)) {
                            rowMessage += "办学地区不能为空；";
                        }else if (schoolSiteName.length() > 200){
                            rowMessage += "办学地区长度不能超过200";
                        }
                        str = schoolSiteName.split("->");
                        String xianName = str[1];
                        //regionID
                        String regionid = FieldDictUtil.get(Constant.APPID,"sch_school_site_nzxy","schoolSiteid",schoolSiteid);
                        tempUserKB.setRegionid(Long.valueOf(regionid));
                        tempUserKB.setSchoolSiteid(Long.valueOf(schoolSiteid));
                    }else if (c == 2){
                        name = cell.getStringCellValue();
                        if (StringUtils.isEmpty(name)){
                            rowMessage += "教学点名称不能为空";
                        }
                        if (name.length()>200){
                            rowMessage += "教学点名称的长度不能超过200";
                        }
                        tempUserKB.setName(name);
                    }else if (c == 3){
                        pinyin = cell.getStringCellValue();
                        if (pinyin.length()>200){
                            rowMessage += "拼音的长度不能超过200";
                        }
                        tempUserKB.setPinyin(pinyin);

                    }else if (c == 4){
                        linkman = cell.getStringCellValue();
                        if (linkman.length() > 50){
                            rowMessage += "联系人的长度不能超过50";
                        }
                        tempUserKB.setLinkman(linkman);
                    }
                    else if (c == 5){
                        phone = cell.getStringCellValue();
                        if (phone.length() > 20){
                            rowMessage += "固定电话的长度不能超过20";
                        }
                        tempUserKB.setPhone(phone);
                    } else if (c == 6){
                        mphone = cell.getStringCellValue();
                        if (mphone.length() > 20){
                            rowMessage += "移动电话的长度不能超过20";
                        }
                        tempUserKB.setPhone(mphone);
                    }else if (c == 7){
                        postCode = cell.getStringCellValue();
                        if (postCode.length() > 10){
                            rowMessage += "邮编的长度不能超过10";
                        }
                        tempUserKB.setPostCode(postCode);

                    }else if (c ==8){
                        address = cell.getStringCellValue();
                        if (address.length() > 200){
                            rowMessage += "联系地址长度不能超过200";
                        }
                        tempUserKB.setAddress(address);
                    }else if (c == 9){
                        status = cell.getStringCellValue();
                        status = FieldDictUtil.get(Constant.APPID,"sch_teach_site","status",status);
                        if (StringUtils.isEmpty(status)){
                            rowMessage += "状态不能为空";
                        }
                        tempUserKB.setStatus(status);
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
//        if (StringUtils.isEmpty(errorMsg)) {
//            //验证上传数据在数据库是否已存在
//            List<TeachSiteDO> list= teachSiteDao.listCZ(userKnowledgeBaseList);
//            if(list.size()>0){
//                errorMsg ="数据重复，重复的数据为";
//                for(TeachSiteDO spe : list){
//                    errorMsg += spe.getName()+",";
//                }
//                errorMsg +="数据库已存在,不能重复添加";
//            }
//        }

        //全部验证通过才导入到数据库
        if(StringUtils.isEmpty(errorMsg)){
            this.saveBatch(userKnowledgeBaseList);
            for (TeachSiteDO teachSiteDO : userKnowledgeBaseList) {
                FieldDictDO fieldDict = new FieldDictDO();
                fieldDict.setAppid(Constant.APPID);
                fieldDict.setTableName("sch_teach_site_nzxy");
                fieldDict.setFieldName("id");
                fieldDict.setFieldValue(teachSiteDO.getId()+"");
                fieldDict.setFieldMean(teachSiteDO.getName());
                fieldDictService.saveCache(fieldDict);
                fieldDict.setTableName("sch_teach_site_nzxy");
                fieldDict.setFieldName("code_name");
                fieldDict.setFieldValue(teachSiteDO.getCollegeid()+teachSiteDO.getName());
                fieldDict.setFieldMean(teachSiteDO.getId()+"");
                fieldDictService.saveCache(fieldDict);
            }
            errorMsg = "导入成功，共" + userKnowledgeBaseList.size()+"条数据！";
        }
        return errorMsg;
    }

    public void saveBatch(List<TeachSiteDO> userKnowledgeBaseList) {
        teachSiteDao.saveBatch(userKnowledgeBaseList);
    }
}