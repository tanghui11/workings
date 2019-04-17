package com.hxy.nzxy.stexam.center.student.controller;

import com.alibaba.fastjson.JSON;
import com.hxy.nzxy.stexam.center.exam.service.ExamItemService;
import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.center.student.service.StudentService;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.region.service.GroupsRegionService;
import com.hxy.nzxy.stexam.region.region.service.RegionRegionService;
import com.hxy.nzxy.stexam.region.region.service.RegionSpecialityRegionService;
import com.hxy.nzxy.stexam.region.student.service.StudentRegionService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考务社会生基本信息管理
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */

@Controller
@RequestMapping("/student/studentRegionGl")
public class StudentRegionGlController extends SystemBaseController {
    @Autowired
    private StudentRegionService studentRegionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ExamItemService examItemService;
    @Autowired
    private RegionRegionService regionRegionService;
    @Autowired
    private GroupsRegionService groupsRegionService;
    @Autowired
    private RegionSpecialityRegionService regionSpecialityRegionService;
    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentSpecialityService studentSpecialityService;
    @GetMapping()
    @RequiresPermissions("student:studentRegion:studentRegion")
    String Student(Model model){
        model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
        model.addAttribute("stu_student_classify" ,commonService.listFieldDict(getAppName(), "stu_student", "classify"));
        model.addAttribute("stu_student_audit_status" ,commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
        return "center/student/studentgl/student";
    }

    /**
     * 显示弹出页面
     */
    @GetMapping("/showSubject")
    String showSubject(Model model) {
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/showSubject";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        params.put("classify","'a','c'");
        Query query = new Query(params);
        List<StudentDO> studentList = studentService.list(query);
        for (StudentDO item : studentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
            item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));
            item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student", "audit_status", item.getAuditStatus()));
            item.setConfirmStatus(FieldDictUtil.get(getAppName(), "stu_student", "confirm_status", item.getConfirmStatus()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentService.count(query);
        PageUtils pageUtils = new PageUtils(studentList, total);
        return pageUtils;
    }



    //报考类别数据
    @ResponseBody
    @GetMapping("/exaExamItemlist")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public List<ExamItemDO> exaExamItemList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<ExamItemDO> examItemList = examItemService.list(params);
        for (ExamItemDO item : examItemList) {
            item.setExamMonth(FieldDictUtil.get(getAppName(), "exa_exam_item", "exam_month", item.getExamMonth()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return examItemList;
    }

    //报考县区数据
    @ResponseBody
    @GetMapping("/childRegionlist")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public List<RegionDO> childRegionList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<RegionDO> regionRegionList = regionRegionService.list(params);
        for (RegionDO item : regionRegionList) {
            item.setType(FieldDictUtil.get(getAppName(), "reg_region", "type", item.getType()));
            item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return regionRegionList;
    }

    //集体代码数据
    @ResponseBody
    @GetMapping("/groupslist")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public List<GroupsDO> groups(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<GroupsDO> groupsRegionList = groupsRegionService.list(params);
        for (GroupsDO item : groupsRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
            item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
        }
        return groupsRegionList;
    }

    //报考专业数据
    @ResponseBody
    @GetMapping("/specialityname")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public List<RegionSpecialityDO> specialitylist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<RegionSpecialityDO> regionSpecialityRegionList = regionSpecialityRegionService.regionSpecialitylist(params);
        for (RegionSpecialityDO item : regionSpecialityRegionList) {
            item.setName(item.getSpecialityid() + " " +FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return regionSpecialityRegionList;
    }



    @ResponseBody
    @GetMapping("/listshowSubject")
    public PageUtils listshowSubject(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SpecialityDO> specialityList = specialityService.list(query);
        for (SpecialityDO item : specialityList) {
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality", "type", item.getType()));
            item.setClassify(FieldDictUtil.get(getAppName(), "pla_speciality", "classify", item.getClassify()));
            item.setFlag(FieldDictUtil.get(getAppName(), "pla_speciality", "flag", item.getFlag()));
            item.setGrantType(FieldDictUtil.get(getAppName(), "pla_speciality", "grant_type", item.getGrantType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = specialityService.count(query);
        PageUtils pageUtils = new PageUtils(specialityList, total);
        return pageUtils;
    }


    /**
     *审核页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/showAudit/{id}")
    @RequiresPermissions("student:student:add")
    String showAudit(@PathVariable("id") String id,Model model){
        StudentDO student = studentService.get(id);
        model.addAttribute("student", student);
        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        return "center/student/student/add";
    }

    /**
     * 查询学生相应的专业信息
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/seachStuSubjectlist")

    public List seachStuSubjectlist(@RequestParam Map<String, Object> params){

        List<StudentSpecialityDO> studentList = studentSpecialityService.seachStuSubjectlist(params);
        for (StudentSpecialityDO item : studentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return studentList;
    }
    /**
     * 修改学生信息基本信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("student:student:edit")
    String edit(@PathVariable("id") String id,Model model){
        StudentDO student = studentService.get(id);
        student.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", student.getGroupid()+""));
        model.addAttribute("student", student);
        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        return "center/student/student/edit";
    }

    /**
     * 修改学生信息
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("student:student:edit")
    public R update( StudentDO studentStudent){
        studentStudent.setOperator(ShiroUtils.getUserId().toString());
        studentService.update(studentStudent);
        return R.ok();
    }

    /**
     * 删除学生档案
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("student:student:remove")
    public R remove( String id){
        //删除学生档案
        Map regMap= new HashMap();
        regMap.put("tableName","stu_student_speciality");
        regMap.put("filedName1","studentid");
        regMap.put("filedValue1",id);
        Map bkMap= new HashMap();
        bkMap.put("tableName","stu_student_course");
        bkMap.put("filedName1","studentid");
        bkMap.put("filedValue1",id);
        if(commonService.searchIfExist(regMap)>0)
        {
            return R.ok("对不起！您已经报考专业了，不能删除学生档案");
        }else if(commonService.searchIfExist(regMap)>0)
        {
            return R.ok("对不起！您已经报考课程了，不能删除学生档案");
        }else if(studentService.remove(id)>0){
            return R.ok("删除学生档案成功！");
        }

        return R.error();
    }
    /**
     * 单个审核/取消审核
     */
    @PostMapping( "/updateAudit")
    @ResponseBody
    @RequiresPermissions("student:student:audit")
    public R updateAudit( @RequestParam Map<String, Object> params ){
        String auditStatus=(String)params.get("auditStatus");
        String messages="审核成功！";
        if("a".equals(auditStatus))
        {
            messages="取消审核成功！";
        }
        params.put("operator",ShiroUtils.getUserId().toString());
        if(studentService.updateAudit(params)>0){
            return R.ok(messages);
        }
        return R.error();
    }

    /**
     * 批量审核/取消审核
     */
    @PostMapping( "/batchUpdateAudit")
    @ResponseBody
    @RequiresPermissions("student:student:batchAudit")
    public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("auditStatus") String auditStatus){

        String messages="批量审核成功！";
        if("a".equals(auditStatus))
        {
            messages="批量取消审核成功！";
        }

        if(studentService.batchUpdateAudit(ids,auditStatus)>0) {
            return R.ok(messages);
        }else
        {
            return R.error();
        }
    }

    /**
     * 批量退学/恢复退学
     */
    @PostMapping( "/batchUpdateTx")
    @ResponseBody
    @RequiresPermissions("student:student:batchTx")
    public R batchUpdateTx(@RequestParam("ids[]") Long[] ids,@RequestParam("status") String status){

        String messages="退学成功！";
        if("a".equals(status))
        {
            messages="恢复学籍成功！";
        }

        if(studentService.batchUpdateTx(ids,status)>0) {
            return R.ok(messages);
        }else
        {
            return R.error();
        }
    }

    /**
     * 批量导入学生信息
     */
    @PostMapping("/savBathData")
    @RequiresPermissions("student:studentRegion:savBathData")
    public String savBathData(@RequestParam(value="filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException {
        //判断文件是否为空
        if(file==null){
            request.setAttribute("msg","文件不能为空！");
            return "center/student/studentgl/importData";
        }

        //获取文件名
        String fileName=file.getOriginalFilename();

        //验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            request.setAttribute("msg","文件不能为空！");
            return "center/student/studentgl/importData";
        }

        //批量导入
        String message = studentService.batchImport(fileName,file,"kaowu");
        request.setAttribute("msg",message);
        return "center/student/studentgl/importData";
    }
    @RequestMapping("/searchOutExcel")
    @RequiresPermissions("student:studentRegion:searchOutExcel")
    public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response,HttpServletRequest request){

        request.getSession().removeAttribute("totalCount");
        request.getSession().removeAttribute("curCount");
        request.getSession().removeAttribute("percent");
        request.getSession().removeAttribute("percentText");
        //查询列表数据
        Configuration config = getConfig("config.properties");
        String configsrc =config.getString("url");
        String Filepath = configsrc+ "Student/";
        String strZipPath=configsrc+"StudentZip/";
        File file = new File(Filepath);
        if (!file.exists()) {
            file.mkdirs();
        }else {
            String[] tempList = file.list();
            File tempFile = null;
            for (int i = 0; i < tempList.length; i++) {
                if (Filepath.endsWith("/")) {
                    tempFile = new File(Filepath+tempList[i]);
                }else {
                    tempFile=new File(Filepath+"/"+tempList[i]);
                }
                if (tempFile.isFile()) {
                    tempFile.delete();
                }
            }
        }
        File fileZip = new File(strZipPath);
        if (!fileZip.exists()) {
            fileZip.mkdirs();
        }else {
            String[] tempList = fileZip.list();
            File tempFile = null;
            for (int i = 0; i < tempList.length; i++) {
                if (Filepath.endsWith("/")) {
                    tempFile = new File(Filepath+tempList[i]);
                }else {
                    tempFile=new File(Filepath+"/"+tempList[i]);
                }
                if (tempFile.isFile()) {
                    tempFile.delete();
                }
            }
        }
        ZipOutputStream out=null;
        params.put("classify","'a','c'");
        List<StudentDO> studentList = studentService.list(params);
        request.getSession().setAttribute("totalCount", studentList.size());

        for (StudentDO item : studentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
            item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));
            item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student", "audit_status", item.getAuditStatus()));
            item.setConfirmStatus(FieldDictUtil.get(getAppName(), "stu_student", "confirm_status", item.getConfirmStatus()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        if (studentList != null && studentList.size() > 0) {
            String[][] result = new String[studentList.size() + 1][14];

            result[0] = new String[] { "序号", "准考证号", "身份证号", "姓名", "性别", "民族", "移动电话", "专业名称","专业代码","状态","审核状态","确认状态"};
            if (studentList != null && studentList.size() > 0) {
                for (int i = 0; i < studentList.size(); i++) {
                    StudentDO studentDO = studentList.get(i);
                    result[i + 1][0] = String.valueOf(i + 1);
                    result[i + 1][1] = String.valueOf(studentDO.getId());
                    result[i + 1][2] = String.valueOf(studentDO.getCertificateNo());
                    result[i + 1][3] = String.valueOf(studentDO.getName());
                    result[i + 1][4] = String.valueOf(studentDO.getGender());
                    result[i + 1][5] = String.valueOf(studentDO.getNation());
                    result[i + 1][6] = String.valueOf(studentDO.getMphone());
                    result[i + 1][7] = String.valueOf(studentDO.getSpecialityName());
                    result[i + 1][8] = String.valueOf(studentDO.getSpecialityid());
                    result[i + 1][9] = String.valueOf(studentDO.getStatus());
                    result[i + 1][10] = String.valueOf(studentDO.getAuditStatus());
                    result[i + 1][11] = String.valueOf(studentDO.getConfirmStatus());
                    double dPercent=(double)(i)/studentList.size();   //将计算出来的数转换成double
                    int  percent=(int)(dPercent*100);               //再乘上100取整
                    request.getSession().setAttribute("curCount", i);
                    request.getSession().setAttribute("percent", percent);    //比如这里是50
                    request.getSession().setAttribute("percentText",percent+"%");//这里是50%
                }
            }
            String tempFileName="学生档案信息"+".xlsx";
            ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
            BufferedInputStream buffer1=null;
            String date = "";
            try {
                date = DateUtil.formatDate(new Date());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            OutputStream out1=null;
            String zipName="学生档案信息" +date+".zip";
            ZipUtils.createZip(Filepath,strZipPath+zipName);
            try {
                File fs = new File(strZipPath+zipName);
                //检查该文件是否存在
                if(!fs.exists()){
                    return null;
                }
                buffer1 = new BufferedInputStream(new FileInputStream(fs));
                byte[] buf = new byte[1024];
                int len = 0;
                response.reset();
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-disposition", "attachment;filename="+new String(fs.getName().getBytes("gbk"), "iso8859-1"));
                out1 = response.getOutputStream();
                while((len = buffer1.read(buf)) >0){
                    out1.write(buf,0,len);
                }
            }catch(Throwable e)
            {
            }finally
            {
                try
                {
                    buffer1.close();
                    out1.close();
                }catch(Throwable e)
                {

                    e.printStackTrace();
                }
            }
        }
        else
        {
            return "center/student/studentgl/student";
        }
        request.getSession().setAttribute("percent", 100);    //比如这里是50
        request.getSession().setAttribute("percentText",100+"%");//这里是50%
        request.getSession().setAttribute("curCount", studentList.size());
        return null;
    }
    //专业导入页面
    @GetMapping("/importData")
    @RequiresPermissions("student:studentRegion:savBathData")
    String importData( ){

        return "center/student/studentgl/importData";
    }
    //专业导入页面
    @GetMapping("/importData1")
    @RequiresPermissions("student:studentRegion:savBathDataSpeciality")
    String importData1( ){

        return "center/student/studentgl/importData1";
    }
    /**
     * 批量导入学生信息
     */
    @PostMapping("/savBathDataSpeciality")
    @RequiresPermissions("student:studentRegion:savBathDataSpeciality")

    public String savBathDataSpeciality(@RequestParam(value="filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException {
        //判断文件是否为空
        if(file==null){
            request.setAttribute("msg","文件不能为空！");
            return "center/student/studentgl/importData1";
        }

        //获取文件名
        String fileName=file.getOriginalFilename();

        //验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            request.setAttribute("msg","文件不能为空！");
            return "center/student/studentgl/importData1";
        }

        //批量导入
        String message = studentSpecialityService.batchImportKaoWu(fileName,file);;
        request.setAttribute("msg",message);
        return "center/student/studentgl/importData1";
    }

}
