package com.hxy.nzxy.stexam.common.controller;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.center.sys.dao.SystemStudentDao;
import com.hxy.nzxy.stexam.common.config.FtpConfig;
import com.hxy.nzxy.stexam.common.utils.FtpUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.domain.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * 2018-08-01
 * 创建人：lzl
 */
@Controller


public class UploadFileController extends BaseController {
    @Autowired
    FtpConfig ftpConfig;
    @Autowired
    private SystemStudentDao systemStudentDao;
    @Autowired
    private TeachSiteDao teachSiteDao;
    @Autowired
    private SchoolSiteDao schoolSiteDao;
    @Autowired
    private RegionDao regionDao;
    @RequestMapping("/common/upload")
    String showUpload()
    {
        return  "/common/file/imageUpload";
    }

    @RequestMapping("/common/uploadPhoto")
    String Upload()
    {
        return  "/upload";
    }
    @RequestMapping("/common/uploadPhotoCenter")
    String uploadCenter()
    {
        return  "/uploadCenter";
    }
    @ResponseBody
    @RequestMapping(value = "/common/upload" ,method = RequestMethod.POST)
    public Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam String studentid, String teachid,String regionid)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

        }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path[] = new String[2];
        String newVersionName = "";
        String fileMd5 = "";
        String yearNumber="";
        String userName="";
        String regionCode="";
        try {

            inputStream = multipartFile.getInputStream();
            if(studentid.equals("")||studentid==null){
                if(teachid!=null&&!("").equals(teachid)){
                    Calendar now = Calendar.getInstance();
                    //获取地区编码
                    //查询出考试考区编号 考试区县编号 获取地区代码
                    TeachSiteDO teachSiteDO = teachSiteDao.get(Long.valueOf(teachid));
                    SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
                    RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
                    regionCode=regionDO.getCode();
                    userName = ShiroUtils.getUser().getName();
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
                    yearNumber=year1+month1;
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
                }else if(regionid!=null&&!("").equals(regionid)){
                    Calendar now = Calendar.getInstance();
                    //获取地区编码
                    //查询出考试考区编号 考试区县编号 获取地区代码
                    RegionDO regionDO = regionDao.get(Long.valueOf(regionid));
                    regionCode=regionDO.getCode();
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
                    yearNumber=year1+month1;
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
                    String orderid=String.valueOf(byUserName.getValue()+190000).substring(1);
                    //准考证号码
                    studentid =regionCode+yearNumber+orderid;
                    //序列号自增
                    byUserName.setValue(1+byUserName.getValue());
                    systemStudentDao.update(byUserName);
                }
            }else{
                yearNumber=studentid.substring(4,7);
                regionCode=studentid.substring(0,4);
            }
            //序列号前两位
            String The_first_two_of_oderid=studentid.substring(7,9);
            // 上传UpYun后返回的path
             path= FtpUtil.pictureUploadByConfig(ftpConfig, studentid+".jpg",yearNumber+"/"+regionCode+"/"+The_first_two_of_oderid,inputStream);
         //   tmpFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("newVersionName", newVersionName);
        json.put("fileMd5", fileMd5);
        json.put("message", "照片上传成功");
        json.put("status", true);
        json.put("filePath", path);
        json.put("studentid", studentid);
        return json;
    }
    /**
     * 身份证上传接口
     *
     * */
    @ResponseBody
    @RequestMapping(value = "/common/idcardUpload" ,method = RequestMethod.POST)
    public Map<String, Object> idcardUpload(HttpServletRequest request, HttpServletResponse response,@RequestParam String idcard)
            throws Exception {
        request.setCharacterEncoding("UTF-8");

        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

        }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path[] = new String[2];
        String newVersionName = "";
        String fileMd5 = "";
        String yearNumber="";
        String userName="";
        try {

            inputStream = multipartFile.getInputStream();



                Calendar now = Calendar.getInstance();
                //获取用户名
                userName = ShiroUtils.getUser().getName();
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
                yearNumber=year1+month1;

            // 上传UpYun后返回的path
            path= FtpUtil.pictureUploadByConfig(ftpConfig, idcard+".jpg",yearNumber+"/"+userName,inputStream);
            //   tmpFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("newVersionName", newVersionName);
        json.put("fileMd5", fileMd5);
        json.put("message", "身份证照片上传成功");
        json.put("status", true);
        json.put("filePath", path);
        json.put("idcard", idcard);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/common/uploadCenter" ,method = RequestMethod.POST)
    public Map<String, Object> uploadCenter(HttpServletRequest request, HttpServletResponse response,@RequestParam String studentid)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

        }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path[] = new String[2];
        String newVersionName = "";
        String fileMd5 = "";

        try {
            inputStream = multipartFile.getInputStream();
            // 上传UpYun后返回的path
            path= FtpUtil.pictureUploadByConfig(ftpConfig, studentid+".jpg","center/card                        ",inputStream);
            //   tmpFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("newVersionName", newVersionName);
        json.put("fileMd5", fileMd5);
        json.put("message", "照片上传成功");
        json.put("status", true);
        json.put("filePath", path);
        json.put("studentid", studentid);
        return json;
    }
    /**
     * 身份证上传接口
     *
     * */
    @ResponseBody
    @RequestMapping(value = "/common/idcardUploadCenter" ,method = RequestMethod.POST)
    public Map<String, Object> idcardUploadCenter(HttpServletRequest request, HttpServletResponse response,@RequestParam String idcard)
            throws Exception {
        request.setCharacterEncoding("UTF-8");

        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

        }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path[] = new String[2];
        String newVersionName = "";
        String fileMd5 = "";
        String yearNumber="";
        String userName="";
        try {
            inputStream = multipartFile.getInputStream();
            Calendar now = Calendar.getInstance();

            // 上传UpYun后返回的path
            path= FtpUtil.pictureUploadByConfig(ftpConfig, idcard+".jpg","center/idcard",inputStream);
            //   tmpFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("newVersionName", newVersionName);
        json.put("fileMd5", fileMd5);
        json.put("message", "身份证照片上传成功");
        json.put("status", true);
        json.put("filePath", path);
        json.put("idcard", idcard);
        return json;
    }
}
