package com.hxy.nzxy.stexam.region.student.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.center.exam.service.ExamItemService;
import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.center.plan.service.CourseService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.region.service.GroupsRegionService;
import com.hxy.nzxy.stexam.region.region.service.RegionRegionService;
import com.hxy.nzxy.stexam.region.region.service.RegionSpecialityRegionService;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityRegionService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.region.student.service.StudentRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生基本信息表
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */

@Controller
@RequestMapping("/student/studentRegion")
public class StudentRegionController extends SystemBaseController {
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
    private StudentSpecialityRegionService studentSpecialityRegionService;

    @GetMapping()
    @RequiresPermissions("student:studentRegion:studentRegion")
    String StudentRegion(Model model) {
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/studentRegion";
    }
    @GetMapping("/showPhoto")
    String showPhoto(Model model) {
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/showPhoto";
    }
    @GetMapping("/showPhotoCenter")
    String showPhotoCenter(Model model) {
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/showPhotoCenter";
    }
    /**
     * 显示弹出页面
     */
    @GetMapping("/showSubject")
    String showSubject(Model model) {
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/showSubject";
    }
    /**
     * 学生报名选择专业
     */
    @GetMapping("/SchoolSpecialityRegSchoolStudent")
    String SchoolSpecialityRegSchoolStudent(Model model){
        model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
        return "region/student/studentRegion/schoolSpecialityRegSchoolStudent";
    }

    @GetMapping("/toenexam")
    String toenexam(String examTaskid,Long childRegionid,String studentid,Long specialityRecordid,Model model) {
        model.addAttribute("specialityRecordid",specialityRecordid);
        model.addAttribute("regionid",ShiroUtils.getUser().getWorkerid());
        model.addAttribute("examTaskid",examTaskid);
        model.addAttribute("childRegionid",childRegionid);
        model.addAttribute("studentid",studentid);
        return "region/student/studentRegion/enexam";
    }
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        params.put("classify","c");
        //根据考试任务 获取考ji号
        if(params.get("examTaskid").equals(null)||params.get("examTaskid").equals("")){
            return  new PageUtils(new ArrayList<>(),0);
        }
        TaskDO task= taskService.get(Long.valueOf(params.get("examTaskid").toString()));
        params.put("kjh",task.getExamYear().substring(2)+task.getExamMonth());
        Query query = new Query(params);
        List<StudentDO> studentRegionList = studentRegionService.list(query);
        for (StudentDO item : studentRegionList) {
            item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid() + ""));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
            item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
            item.setSpecialityName(item.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentRegionService.count(query);
        PageUtils  pageUtils = new PageUtils(studentRegionList, total);
        return pageUtils;
    }

    //考试任务数据
    @ResponseBody
    @GetMapping("/listTask")
    @RequiresPermissions("student:studentRegion:studentRegion")
    List<TaskDO> edit(@RequestParam Map<String, Object> params, Model model) {
        List<TaskDO> taskList = taskService.taskList(params);
        for (TaskDO item : taskList) {
            item.setExamMonth(FieldDictUtil.get(getAppName(), "exam_task", "exam_month", item.getExamMonth()));
            item.setExamName(item.getExamYear() + "  " + item.getExamMonth());
        }
        return taskList;
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
    @GetMapping("/group")
    @RequiresPermissions("student:studentRegion:studentRegion")
    String group(Model model) {

        return "region/student/studentRegion/group";
    }
    @GetMapping("/specialityRecord")
    @RequiresPermissions("student:studentRegion:studentRegion")
    String specialityRecord(Model model) {

        return "region/student/studentRegion/specialityRecord";
    }
    //集体代码数据
    @ResponseBody
    @GetMapping("/groupslist")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public PageUtils groups(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GroupsDO> groupsRegionList = groupsRegionService.list(query);
        for (GroupsDO item : groupsRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
            item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
        }
        int total = groupsRegionService.count(query);
        PageUtils pageUtils = new PageUtils(groupsRegionList, total);
        return pageUtils;

    }
    //集体代码数据
    @ResponseBody
    @GetMapping("/groupslist2")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public List<GroupsDO> groupslist2(@RequestParam Map<String, Object> params) {
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
    public PageUtils specialitylist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RegionSpecialityDO> regionSpecialityRegionList = regionSpecialityRegionService.regionSpecialitylist(query);
        for (RegionSpecialityDO item : regionSpecialityRegionList) {
            item.setName(item.getSpecialityid() + " " +FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = regionSpecialityRegionService.count(query);
        PageUtils pageUtils = new PageUtils(regionSpecialityRegionList, total);
        return pageUtils;
    }

    //现报专业数据
    @ResponseBody
    @GetMapping("/planame")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public PageUtils planamelist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RegionSpecialityDO> planamelist = regionSpecialityRegionService.regionSpecialitylist(query);
        for (RegionSpecialityDO item : planamelist) {
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = regionSpecialityRegionService.count(query);
        PageUtils pageUtils = new PageUtils(planamelist, total);
        return pageUtils;
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

    @GetMapping("/add")
    @RequiresPermissions("student:studentRegion:add")
    String add(String examTaskid,Model model,String childRegionid) {
        model.addAttribute("regionid",ShiroUtils.getUser().getWorkerid());
        model.addAttribute("examTaskid",examTaskid);
        model.addAttribute("childRegionid",childRegionid);
        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        return "region/student/studentRegion/add";
    }

    @ResponseBody
    @GetMapping("/enexam")
    @RequiresPermissions("student:studentRegion:studentRegion")
    public PageUtils enexam(@RequestParam Map<String, Object> params) {
        String specialityRecordid = (String) params.get("specialityRecordid");
        TaskDO lastTask = studentRegionService.getLastTask(params);
        params.put("examTaskid",lastTask.getId());
        List<SpecialityCourseDO> courseids = studentRegionService.getids(specialityRecordid);
        String ids="";
        for(int i =0;i<courseids.size();i++){
            SpecialityCourseDO item = courseids.get(i);
            ids+=item.getCourseid()+",";
        }
        if(ids!=null&&ids!=""){
            ids=ids.substring(0,ids.length()-1);
            params.put("ids",ids);

        }
        //params.put("courseids",courseids);
        Query query = new Query(params);
        List<CourseDO> examList = studentRegionService.examTaskList(query);
        for (CourseDO item : examList) {
            item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
        }
        int total = studentRegionService.getEnexamCount(query);
        PageUtils pageUtils = new PageUtils(examList, total);
        return pageUtils;
    }

    /**
     * 报考信息保存
     */
    @ResponseBody
    @PostMapping("/saveexam")
    @RequiresPermissions("student:studentRegion:add")
    public R saveexam(String studentid,Long childRegionid,@RequestParam( value = "examCourseid[]")Long[] examCourseid,Long regionid) {
        StudentCourseDO studentCourse = new StudentCourseDO();
        studentCourse.setStudentid(studentid);
        studentCourse.setChildRegionid(childRegionid);
        studentCourse.setRegionid(regionid);

        studentCourse.setStatus("a");
        studentCourse.setType("c");
        studentCourse.setArrangeStatus("a");
        studentCourse.setOperator(ShiroUtils.getUserId().toString());
        for (int i=0;i<examCourseid.length;i++){
            studentCourse.setExamCourseid(examCourseid[i]);
            //可以优化批量
            studentRegionService.saveexam(studentCourse);
        }
        if (1 > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("student:studentRegion:edit")
    String edit(@PathVariable("id") String id, Model model) {
        //id是s  根据 studentSpecialityid =getbyStudentSpecialityid
        StudentSpecialityDO studentSpecialityStudent = studentRegionService.getByStudentId(id);
        StudentDO studentRegion = studentRegionService.get(studentSpecialityStudent.getStudentid());

        studentRegion.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", studentRegion.getGroupid()+""));
        //model.addAttribute("reg_groups_id", commonService.listFieldDict(getAppName(), "reg_groups", "id"));
        //model.addAttribute("stu_student_stu_student", commonService.listFieldDict(getAppName(), "stu_student", "stu_student"));
        //model.addAttribute("stu_student_classify", commonService.listFieldDict(getAppName(), "stu_student", "classify"));
        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));


        /*SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegSchoolService.getSelect(studentSpecialityStudent.getSchoolSpecialityRegid());
        schoolSpecialityRegDO.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegDO.getStatus()));
        schoolSpecialityRegDO.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegDO.getAuditStatus()));
        schoolSpecialityRegDO.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegDO.getClassify()));
        schoolSpecialityRegDO.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegDO.getType()));
        schoolSpecialityRegDO.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegDO.getMethod()));
        schoolSpecialityRegDO.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegDO.getEducateLength()));
        schoolSpecialityRegDO.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegDO.getRegSeason()));
        studentSpecialityStudent.setSchoolSpecialityRegName(schoolSpecialityRegDO.getSpecialityId()+schoolSpecialityRegDO.getSpecialityName()+"["+schoolSpecialityRegDO.getRegYear()+" "+schoolSpecialityRegDO.getRegSeason()+"]"+"["+schoolSpecialityRegDO.getClassify()+" "+schoolSpecialityRegDO.getType()+" "+schoolSpecialityRegDO.getEducateLength()+"]");*/
        //SpecialityDO specialityDO = specialityService.get(studentSpecialityStudent.getGradSpecialityid());
        studentSpecialityStudent.setGradSpecialityName(studentSpecialityStudent.getGradSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpecialityStudent.getGradSpecialityid()));
        studentSpecialityStudent.setSchoolSpecialityRegName(studentSpecialityStudent.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpecialityStudent.getSpecialityid()));

        //studentRegion.setSpecialityName(studentRegion.getSpecialityid() + " " +FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentRegion.getSpecialityid()));
        //studentRegion.setGradSpecialityName(studentRegion.getGradSpecialityid()+"  " +studentRegion.getGradSpecialityName());
        model.addAttribute("regionid",ShiroUtils.getUser().getWorkerid());
        model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);
        model.addAttribute("studentRegion", studentRegion);
        return "region/student/studentRegion/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("student:studentRegion:add")
    public R save(StudentDO studentRegion,StudentSpecialityDO studentSpeciality) {
        studentRegion.setOperator(ShiroUtils.getUserId().toString());


        if (studentRegionService.save(studentRegion,studentSpeciality) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("student:studentRegion:edit")
    public R update(StudentDO studentRegion,StudentSpecialityDO studentSpeciality) {
        //id是s  根据 studentSpecialityid =getbyStudentSpecialityid
        StudentSpecialityDO studentSpecialityStudent = studentRegionService.getByStudentId(studentSpeciality.getStudentSpecialityid());
        Date now = new Date();
        long l = now.getTime() -DateUtils.stringToDate(studentSpecialityStudent.getCreateDate(), "").getTime();
        long day = l / (24 * 60 * 60 * 1000);
        if (day>30){
            return R.ok("学生已注册了"+day+"天，超考30天，不能进行修改！");
        }
        studentRegion.setOperator(ShiroUtils.getUserId().toString());
        studentRegionService.update(studentRegion,studentSpeciality);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("student:studentRegion:remove")
    public R remove(long id) {
        if(studentSpecialityRegionService.remove(id)>0){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("student:studentRegion:batchRemove")
    public R remove(@RequestParam("ids[]")Long[] ids) {
        studentSpecialityRegionService.batchRemove(ids);
        return R.ok();
    }
    //学生信息导入页面
    @GetMapping("/importData")
    @RequiresPermissions("student:studentRegion:savBathData")
    String importData( ){
        return "region/student/studentRegion/importData";
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
        List<StudentDO> studentRegionList = studentRegionService.list(params);
        request.getSession().setAttribute("totalCount", studentRegionList.size());
        for (StudentDO item : studentRegionList) {
            item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid() + ""));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
            item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
            item.setSpecialityName(item.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        if (studentRegionList != null && studentRegionList.size() > 0) {
            String[][] result = new String[studentRegionList.size() + 1][10];

            result[0] = new String[] { "序号", "准考证号", "身份证号", "姓名", "籍贯", "移动电话", "通讯地址", "毕业院校","专业名称","注册时间"};
            if (studentRegionList != null && studentRegionList.size() > 0) {
                for (int i = 0; i < studentRegionList.size(); i++) {
                    StudentDO studentDO = studentRegionList.get(i);
                    result[i + 1][0] = String.valueOf(i + 1);
                    result[i + 1][1] = String.valueOf(studentDO.getStudentid());
                    result[i + 1][2] = String.valueOf(studentDO.getCertificateNo());
                    result[i + 1][3] = String.valueOf(studentDO.getName());
                    result[i + 1][4] = String.valueOf(studentDO.getNativePlace());
                    result[i + 1][5] = String.valueOf(studentDO.getMphone());
                    result[i + 1][6] = String.valueOf(studentDO.getAddress());
                    result[i + 1][7] = String.valueOf(studentDO.getGradSchool());
                    result[i + 1][8] = String.valueOf(studentDO.getSpecialityName());
                    result[i + 1][9] = String.valueOf(studentDO.getCreateDate());
                    double dPercent=(double)(i)/studentRegionList.size();   //将计算出来的数转换成double
                    int  percent=(int)(dPercent*100);//再乘上100取整
                    request.getSession().setAttribute("curCount", i);
                    request.getSession().setAttribute("percent", percent);    //比如这里是50
                    request.getSession().setAttribute("percentText",percent+"%");//这里是50%
                }
            }
            String tempFileName="报名信息"+".xlsx";
            ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
            BufferedInputStream buffer1=null;
            String date = "";
            try {
                date = DateUtil.formatDate(new Date());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            OutputStream out1=null;
            String zipName="报名信息" +date+".zip";
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
            return "region/student/studentRegion/studentRegion";
        }

        request.getSession().setAttribute("percent", 100);    //比如这里是50
        request.getSession().setAttribute("percentText",100+"%");//这里是50%
        request.getSession().setAttribute("curCount", studentRegionList.size());
        return null;
    }

}
