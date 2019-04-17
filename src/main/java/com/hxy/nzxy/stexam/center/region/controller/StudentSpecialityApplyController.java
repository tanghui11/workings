package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.plan.service.OldCourseService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityCourseService;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldService;
import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.center.student.service.StudentService;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentSpecialityApply")
public class StudentSpecialityApplyController extends SystemBaseController {
    @Autowired
    private StudentSpecialityService studentSpecialityService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;
    @Autowired
    private OldCourseService oldCourseService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ScoreOldService scoreOldService;
    @Autowired
    private SpecialityCourseService specialityCourseService;
    @GetMapping()
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    String StudentSpeciality(@RequestParam("studentid") String studentid, Model model) {
        if (studentid != null && !"".equals(studentid)) {
            StudentDO student = studentService.get(studentid);
            model.addAttribute("student", student);
        }
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        return "center/student/studentSpeciality/studentSpeciality";
    }

    @GetMapping("/pageIntoAudit")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    String pageIntoAudit(Model model) {

        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        return "center/student/studentAudit/studentSpeciality";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.list(query);
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
            item.setGradAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "grad_audit_status", item.getGradAuditStatus()));
            item.setPrintCertificate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "print_certificate", item.getPrintCertificate()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentSpecialityService.count(query);
        PageUtils pageUtils = new PageUtils(studentSpecialityList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/information")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    public StudentSpecialityDO information(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Long id = Long.valueOf(params.get("id").toString());
        StudentSpecialityDO studentSpeciality = studentSpecialityService.get(id);
        studentSpeciality.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", studentSpeciality.getTeachSiteid() + ""));
        studentSpeciality.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", studentSpeciality.getCollegeid() + ""));
        studentSpeciality.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", studentSpeciality.getSchoolid() + ""));
        studentSpeciality.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpeciality.getSpecialityid()));
        studentSpeciality.setOperator(UserUtil.getName(studentSpeciality.getOperator()));
        studentSpeciality.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", studentSpeciality.getClassify()));
        studentSpeciality.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", studentSpeciality.getType()));
        studentSpeciality.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", studentSpeciality.getEducateLength()));
        studentSpeciality.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", studentSpeciality.getStatus()));
        studentSpeciality.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", studentSpeciality.getGraduate()));
        studentSpeciality.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", studentSpeciality.getAuditStatus()));
        studentSpeciality.setGradAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "grad_audit_status", studentSpeciality.getGradAuditStatus()));
        studentSpeciality.setPrintCertificate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "print_certificate", studentSpeciality.getPrintCertificate()));
        studentSpeciality.setUpdateDate(DateUtils.format(studentSpeciality.getUpdateDate(), DateUtils.DATE_PATTERN));
        return studentSpeciality;
    }

    /**
     * 毕业生审核列表
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listStudent")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    public PageUtils listStudent(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.listStudent(query);
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
            item.setGradAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "grad_audit_status", item.getGradAuditStatus()));
            item.setPrintCertificate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "print_certificate", item.getPrintCertificate()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentSpecialityService.count(query);
        PageUtils pageUtils = new PageUtils(studentSpecialityList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("student:studentSpecialityApply:add")
    String add(Model model) {
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        return "center/student/studentSpeciality/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("student:studentSpecialityApply:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        StudentSpecialityDO studentSpeciality = studentSpecialityService.get(id);
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));

        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));

        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));

        SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegSchoolService.getSelect(studentSpeciality.getSchoolSpecialityRegid());
        schoolSpecialityRegDO.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegDO.getStatus()));
        schoolSpecialityRegDO.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegDO.getAuditStatus()));
        schoolSpecialityRegDO.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegDO.getClassify()));
        schoolSpecialityRegDO.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegDO.getType()));
        schoolSpecialityRegDO.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegDO.getMethod()));
        schoolSpecialityRegDO.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegDO.getEducateLength()));
        schoolSpecialityRegDO.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegDO.getRegSeason()));
        studentSpeciality.setSchoolSpecialityRegName(schoolSpecialityRegDO.getSpecialityId() + schoolSpecialityRegDO.getSpecialityName() + "[" + schoolSpecialityRegDO.getRegYear() + " " + schoolSpecialityRegDO.getRegSeason() + "]" + "[" + schoolSpecialityRegDO.getClassify() + " " + schoolSpecialityRegDO.getType() + " " + schoolSpecialityRegDO.getEducateLength() + "]");
        studentSpeciality.setGradSpecialityName("(" + studentSpeciality.getGradSpecialityid() + ")" + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpeciality.getGradSpecialityid()));
        model.addAttribute("studentSpeciality", studentSpeciality);
        return "center/student/studentSpeciality/edit";
    }

    @GetMapping("/addApply/{id}")
    @RequiresPermissions("student:studentSpecialityApply:add")
    String addApply(@PathVariable("id") Long id,Model model) {
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        StudentSpecialityDO  studentSpeciality = studentSpecialityService.get(id);
        model.addAttribute("studentSpeciality", studentSpeciality);
        return "school/student/schoolStudentApply/add";
    }

    @GetMapping("/editApply/{studentid}")
    @RequiresPermissions("student:studentSpecialityApply:edit")
    String editApply(@PathVariable("studentid") String studentid, Model model) {
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));

        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));

        model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
        model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
        model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
        model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
        model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
      StudentDO student = studentService.get(studentid);
//        student.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", student.getType()));
//        student.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", student.getGender()));
//        student.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", student.getHomeType()));
//        student.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", student.getPolitics()));
//        student.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", student.getNation()));
//        student.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", student.getProfession()));
//        student.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", student.getCertificateType()));
         model.addAttribute("student",student);
        return "school/student/schoolStudentApply/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("student:studentSpecialityApply:add")
    public R save(StudentSpecialityDO studentSpeciality) {
        studentSpeciality.setOperator(ShiroUtils.getUserId().toString());
        if (studentSpecialityService.save(studentSpeciality) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("student:studentSpecialityApply:edit")
    public R update(StudentSpecialityDO studentSpeciality) {
        studentSpeciality.setOperator(ShiroUtils.getUserId().toString());
        studentSpecialityService.update(studentSpeciality);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("student:studentSpecialityApply:remove")
    public R remove(Long id) {
        if (studentSpecialityService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("student:studentSpecialityApply:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        studentSpecialityService.batchRemove(ids);
        return R.ok();
    }

    //专业导入页面
    @GetMapping("/importData")
    @RequiresPermissions("student:studentSpecialityApply:importData")
    String importData() {

        return "center/student/studentSpeciality/importData";
    }

    /**
     * 批量导入
     */
    @PostMapping("/savBathData")
    @RequiresPermissions("student:studentSpecialityApply:importData")
    public String savBathData(@RequestParam(value = "filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException {
        //判断文件是否为空
        if (file == null) {
            request.setAttribute("msg", "文件不能为空！");
            return "center/student/studentSpeciality/importData";
        }

        //获取文件名
        String fileName = file.getOriginalFilename();

        //验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (StringUtils.isEmpty(fileName) || size == 0) {
            request.setAttribute("msg", "文件不能为空！");
            return "center/student/studentSpeciality/importData";
        }

        //批量导入
        String message = studentSpecialityService.batchImport(fileName, file);
        request.setAttribute("msg", message);
        return "center/student/studentSpeciality/importData";
    }

    @RequestMapping("/searchOutExcel")
    @RequiresPermissions("student:studentSpecialityApply:searchOutExcel")
    public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {

        request.getSession().removeAttribute("totalCount");
        request.getSession().removeAttribute("curCount");
        request.getSession().removeAttribute("percent");
        request.getSession().removeAttribute("percentText");
        //查询列表数据
        Configuration config = getConfig("config.properties");
        String configsrc = config.getString("url");
        String Filepath = configsrc + "Student/";
        String strZipPath = configsrc + "StudentZip/";
        File file = new File(Filepath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            String[] tempList = file.list();
            File tempFile = null;
            for (int i = 0; i < tempList.length; i++) {
                if (Filepath.endsWith("/")) {
                    tempFile = new File(Filepath + tempList[i]);
                } else {
                    tempFile = new File(Filepath + "/" + tempList[i]);
                }
                if (tempFile.isFile()) {
                    tempFile.delete();
                }
            }
        }
        File fileZip = new File(strZipPath);
        if (!fileZip.exists()) {
            fileZip.mkdirs();
        } else {
            String[] tempList = fileZip.list();
            File tempFile = null;
            for (int i = 0; i < tempList.length; i++) {
                if (Filepath.endsWith("/")) {
                    tempFile = new File(Filepath + tempList[i]);
                } else {
                    tempFile = new File(Filepath + "/" + tempList[i]);
                }
                if (tempFile.isFile()) {
                    tempFile.delete();
                }
            }
        }
        ZipOutputStream out = null;
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.list(params);
        request.getSession().setAttribute("totalCount", studentSpecialityList.size());
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
            item.setGradAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "grad_audit_status", item.getGradAuditStatus()));
            item.setPrintCertificate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "print_certificate", item.getPrintCertificate()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        if (studentSpecialityList != null && studentSpecialityList.size() > 0) {
            String[][] result = new String[studentSpecialityList.size() + 1][15];

            result[0] = new String[]{"序号", "招生年份", "准考证号", "毕业院校", "专业代码", "专业名称", "毕业证书号", "学院", "教学点", "原学历", "现报专业", "学制", "审核状态", "操作员", "操作日期"};
            if (studentSpecialityList != null && studentSpecialityList.size() > 0) {
                for (int i = 0; i < studentSpecialityList.size(); i++) {
                    StudentSpecialityDO StudentSpeciality = studentSpecialityList.get(i);
                    result[i + 1][0] = String.valueOf(i + 1);
                    result[i + 1][1] = String.valueOf(StudentSpeciality.getRegYear());
                    result[i + 1][2] = String.valueOf(StudentSpeciality.getStudentid());
                    result[i + 1][3] = String.valueOf(StudentSpeciality.getGradSchool());
                    result[i + 1][4] = String.valueOf(StudentSpeciality.getSpecialityid());
                    result[i + 1][5] = String.valueOf(StudentSpeciality.getSpecialityName());
                    result[i + 1][6] = String.valueOf(StudentSpeciality.getGradCertificate());
                    result[i + 1][7] = String.valueOf(StudentSpeciality.getCollegeName());
                    result[i + 1][8] = String.valueOf(StudentSpeciality.getTeachName());
                    result[i + 1][9] = String.valueOf(StudentSpeciality.getEducation());
                    result[i + 1][10] = String.valueOf(StudentSpeciality.getSpecialityName());
                    result[i + 1][11] = String.valueOf(StudentSpeciality.getEducateLength());
                    result[i + 1][12] = String.valueOf(StudentSpeciality.getStatus());
                    result[i + 1][13] = String.valueOf(StudentSpeciality.getOperator());
                    result[i + 1][14] = String.valueOf(StudentSpeciality.getUpdateDate());
                    double dPercent = (double) (i) / studentSpecialityList.size();   //将计算出来的数转换成double
                    int percent = (int) (dPercent * 100);               //再乘上100取整
                    request.getSession().setAttribute("curCount", i);
                    request.getSession().setAttribute("percent", percent);    //比如这里是50
                    request.getSession().setAttribute("percentText", percent + "%");//这里是50%
                }
            }
            String tempFileName = "学生报考专业信息" + ".xlsx";
            ExcelUtil.writeExcelOs(result, Filepath + tempFileName);
            BufferedInputStream buffer1 = null;
            String date = "";
            try {
                date = DateUtil.formatDate(new Date());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            OutputStream out1 = null;
            String zipName = "学生报考专业信息" + date + ".zip";
            ZipUtils.createZip(Filepath, strZipPath + zipName);
            try {
                File fs = new File(strZipPath + zipName);
                //检查该文件是否存在
                if (!fs.exists()) {
                    return null;
                }
                buffer1 = new BufferedInputStream(new FileInputStream(fs));
                byte[] buf = new byte[1024];
                int len = 0;
                response.reset();
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-disposition", "attachment;filename=" + new String(fs.getName().getBytes("gbk"), "iso8859-1"));
                out1 = response.getOutputStream();
                while ((len = buffer1.read(buf)) > 0) {
                    out1.write(buf, 0, len);
                }
            } catch (Throwable e) {
            } finally {
                try {
                    buffer1.close();
                    out1.close();
                } catch (Throwable e) {

                    e.printStackTrace();
                }
            }
        } else {
            return "center/student/student/student";
        }
        request.getSession().setAttribute("percent", 100);    //比如这里是50
        request.getSession().setAttribute("percentText", 100 + "%");//这里是50%
        request.getSession().setAttribute("curCount", studentSpecialityList.size());
        return null;
    }

    @GetMapping("/applyPage")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String applyPage() {
        return "school/student/schoolStudentApply/studentFileStudent";
    }

    @ResponseBody
    @GetMapping("/listQu")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public PageUtils listQu(@RequestParam Map<String, Object> params) {
        //查询列表数据
        String schoolid = ShiroUtils.getUser().getWorkerid();
        Query query = new Query(params);
        query.put("schoolid", schoolid);
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listQu(query);
        for(StudentSpecialityDO item :studentStudentList ){
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setGradSpecialityName("(" + item.getGradSpecialityid() + ")" + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getGradSpecialityid()));

        }
        int total = studentSpecialityService.countQu(query);
        PageUtils pageUtils = new PageUtils(studentStudentList, total);
        return pageUtils;
    }

    @GetMapping("pageInto")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    String pageInto() {
        return "school/student/schoolStudentApply/specialityCourse";
    }

    /**
     * 参数中需要包含 学生id  studentid  specialityRecordid 专业课程课程
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listCourseScore")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public List<StudentSpecialityDO> listCourseScore(@RequestParam Map<String, Object> params) {
        Float grade = null;
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        for (int i = 0; i < studentStudentList.size(); i++) {
            if (!"".equals(studentStudentList.get(i).getSourceCourseid()) && studentStudentList.get(i).getSourceCourseid() != null) {
                if (studentStudentList.get(i).getGrade() == null || "".equals(studentStudentList.get(i).getGrade())) {
                    studentStudentList.get(i).setGrade("0");
                    grade = Float.parseFloat(studentStudentList.get(i).getGrade());
                }
                //判断源课程的成绩是否比现有课程成绩大，如果大，那么显示替换成源课程成绩，毕业申请判断时可能也要用
                if (studentSpecialityService.getSourceCourse(studentStudentList.get(i).getSourceCourseid().toString(), studentStudentList.get(i).getSpecialityRecordid().toString(), studentStudentList.get(i).getStudentid().toString()) > grade) {
                    studentStudentList.get(i).setGrade(String.valueOf(studentSpecialityService.getSourceCourse(studentStudentList.get(i).getSourceCourseid().toString(), studentStudentList.get(i).getSpecialityRecordid().toString(), studentStudentList.get(i).getStudentid().toString())));
                }
            }
        }
        if(studentStudentList.size() != 0){
            for(StudentSpecialityDO item : studentStudentList){
                item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
            }
        }

        return studentStudentList;
    }

    /**
     * 参数中需要包含 学生id  studentid  考生成绩表
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listScore")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public List<ScoreDO> listScore(@RequestParam Map<String, Object> params) {
        List<ScoreDO> studentStudentList = studentSpecialityService.listScore(params);
        for (ScoreDO item : studentStudentList) {
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
        }
        return studentStudentList;
    }

    /**
     * 通用课程顶替的规则
     * ok
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listTYKCReplace")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public String listTYKCReplace(@RequestParam Map<String, Object> params) {
     /*   StringBuilder msg = new StringBuilder();
        //需要查询通用课程顶替规则的课程
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        if (studentStudentList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        //查找规则-查看列表中的课程是否存在顶替规则
        List<CommonCourseReplaceDO> commonCourseList = studentSpecialityService.getCommonCourseReplace(studentStudentList);
        if (commonCourseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        List<CommonCourseReplaceItemDO> itemList = studentSpecialityService.getItemList(commonCourseList);
        List<String> list = null;
        for (CommonCourseReplaceItemDO item : itemList) {
            item.setCommomCourseReplaceName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCommomCourseReplaceid()));
            item.setCommomCourseReplaceItemName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCommomCourseReplaceItemid()));
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (!list.contains(itemList.get(i).getCommomCourseReplaceid())) {
                list.add(itemList.get(i).getCommomCourseReplaceid());
                if (i != 0) {
                    msg.append("</br>");
                }
                msg.append((i + 1) + "：如果考生不考【" + itemList.get(i).getCommomCourseReplaceItemid() + " " + itemList.get(i).getCommomCourseReplaceName() + "】" + "</br>考生必须选择如下课程中的1门课程【");
                for (int p = i; p < itemList.size(); p++) {
                    if (itemList.get(i).getCommomCourseReplaceid().equals(itemList.get(p).getCommomCourseReplaceid())) {
                        msg.append(itemList.get(p).getCommomCourseReplaceItemid() + " " + itemList.get(i).getCommomCourseReplaceItemName() + ";");
                        if (p == itemList.size() - 1) {
                            msg.append("】;");
                        }
                    } else {
                        msg.append("】;");
                    }
                }
            }
        }

        return msg.toString();*/return 1+"";
    }

    /**
     * 顶替课程的规则 ok
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listCourseReplace")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public String listCourseReplace(@RequestParam Map<String, Object> params) {
        StringBuilder msg = new StringBuilder();

        //查找规则-查看列表中的课程是否存在顶替规则
        List<CourseReplaceDO> courseList = studentSpecialityService.listCourseReplace(params.get("specialityRecordid").toString());
        if (courseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        for (CourseReplaceDO courseplace:courseList
             ) {

        }

        Map<Integer, List<CourseReplaceDO>> map = new HashMap<>();
        for(CourseReplaceDO course : courseList){
            if(map.containsKey(course.getCourseid())){//map中存在此id，将数据存放当前key的map中
                map.get(course.getCourseid()).add(course);
            }else{//map中不存在，新建key，用来存放数据
                List<CourseReplaceDO> tmpList = new ArrayList<>();
                tmpList.add(course);
                map.put(Integer.valueOf(course.getCourseid()), tmpList);
            }
        }



        return msg.toString();
    }

    /**
     * 课程加考的规则
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listCourseAppend")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public String listCourseAppend(@RequestParam Map<String, Object> params) {
        StringBuilder msg = new StringBuilder();
        //需要查询通用课程顶替规则的课程
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        if (studentStudentList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        //查找规则-查看列表中的课程是否存在顶替规则
        List<CourseAppendDO> courseList = studentSpecialityService.listCourseAppend(params.get("specialityRecordid").toString(), studentStudentList);
        if (courseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        List<CourseAppendItemDO> itemList = studentSpecialityService.listCourseAppendItem(courseList);
        for (int i = 0; i < courseList.size(); i++) {
            if (i != 0) {
                msg.append("</br>");
            }
            msg.append((i + 1) + "：如果考生不考【" + courseList.get(i).getName() + " " + courseList.get(i).getCourseName() + "】" + "</br>考生必须选择如下课程中的1门课程【");
            for (int p = 0; p < itemList.size(); p++) {
                if (courseList.get(i).getId().equals(itemList.get(p).getCourseAppendid())) {
                    msg.append(itemList.get(p).getCourseid() + " " + itemList.get(i).getName() + ";");
                    if (p == itemList.size() - 1) {
                        msg.append("】;");
                    }
                } else {
                    msg.append("】;</br>");
                }
            }
        }

        return msg.toString();
    }

    /**
     * 课程复选的规则
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listCourseCheck")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public String listCourseCheck(@RequestParam Map<String, Object> params) {
        StringBuilder msg = new StringBuilder();
       /* //需要查询通用课程顶替规则的课程
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        if (studentStudentList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        //查找规则-查看列表中的课程是否存在顶替规则
        List<CourseCheckDO> courseList = studentSpecialityService.listCourseCheck(params.get("specialityRecordid").toString(), studentStudentList);
        if (courseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }
        List<CourseCheckItemDO> itemList = studentSpecialityService.listCourseCheckItem(courseList);
        for (int i = 0; i < courseList.size(); i++) {
            if (i != 0) {
                msg.append("</br>");
            }
            msg.append((i + 1) + "：如果考生不考【" + courseList.get(i).getName() + "】" + "</br>考生必须选择如下课程中的1门课程【");
            for (int p = 0; p < itemList.size(); p++) {
                if (courseList.get(i).getId().equals(itemList.get(p).getCourseCheckid())) {
                    msg.append(itemList.get(p).getCourseid() + " " + itemList.get(i).getCourseName() + ";");
                    if (p == itemList.size() - 1) {
                        msg.append("】;");
                    }
                } else {
                    msg.append("】;");
                }
            }
        }*/

        return msg.toString();
    }


    @GetMapping("/updateBK")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String updateBK() {
        return "school/student/schoolStudentApply/edit";
    }

    /**
     * 修改报考信息
     * map中需要包含 collegeid  teachSiteid specialityRecordid（专业开设id）studentid
     */
    @PostMapping("editStudentInfo")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editStudentInfo(@RequestParam Map<String, Object> map) {
        String msg = "";
        int res = studentSpecialityService.editStudentInfo(map);
        if (res != 0) {
            msg = "修改报考成绩成功";
        } else {
            msg = "修改报考成绩失败";
            return R.error(0, msg);
        }
        return R.ok(msg);
    }

    /**
     * 《和》功能键
     * map中需要包含 collegeid  teachSiteid specialityRecordid（专业开设id）studentid  type-->a  未使用  b--》使用
     */
    @PostMapping("editDoubleRight")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editDoubleRight(@RequestParam Map<String, Object> params) {
        String msg = "";
        int sum =0;
        //课程列表
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        //成绩列表
        List<ScoreDO> studentStudentList2 = studentSpecialityService.listScore(params);
        for (int i = 0; i < studentStudentList.size(); i++) {
            for (int k = 0; k < studentStudentList2.size(); k++) {
                if (studentStudentList.get(i).getCourseid().equals(studentStudentList2.get(k).getCourseid())) {
                    studentSpecialityService.editDoubleRight(studentStudentList.get(i).getCourseid(), params.get("specialityRecordid").toString(), params.get("studentid").toString(), params.get("type").toString());
                sum += sum;
                }
            }

            if(sum == 0){
                return R.error("没有找到该考生合格成绩！");
            }else{
                sum = 0;
            }
        }
        msg = "增加全部匹配课程成功";
        return R.ok(msg);
    }

    /**
     * < 和 >和功能键
     * map中需要包含 collegeid  teachSiteid specialityRecordid（专业开设id）studentid, courseid  type-->a  未使用  b--》使用
     */
    @PostMapping("editFloatRight")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editFloatRight(@RequestParam Map<String, Object> params) {
        String msg = "";
        //课程列表
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        for (int i = 0; i < studentStudentList.size(); i++) {
            if (studentStudentList.get(i).getCourseid().equals(params.get("courseid").toString())) {
                studentSpecialityService.editDoubleRight(params.get("courseid").toString(), params.get("specialityRecordid").toString(), params.get("studentid").toString(), params.get("type").toString());
            } else {
                msg = "该课程在专业计划中不存在";
                return R.error(0, msg);
            }
        }
		/*if ("a".equals(params.get("type"))){
			return R.ok("单项匹配成功");
		}
		if("b".equals(params.get("type"))){
			return R.ok("单项匹配取消成功");
		}*/
        return "a".equals(params.get("type")) ? R.ok("单项匹配成功") : R.ok("单项匹配取消成功");
    }

    /**
     * 顶替功能键
     * map中需要包含 courseid1   courseid2  score1  score2 studentid
     * （scoreCourseid成绩表的courseid），（courseid课程表成绩）
     * 从顶替表中查找是否存在可顶替课程，如果存在，被替换课程源课程添加信息；替换课程使用状态改为·已使用·
     */
    @PostMapping("/editReplaceRight")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editReplaceRight(@RequestParam Map<String, Object> params) {
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        String courseid1 = params.get("courseid1").toString();
        String courseid2 = params.get("courseid2").toString();
        float score1 = Float.parseFloat(params.get("score1").toString());
        float score2 = Float.parseFloat(params.get("score2").toString());
        String specialityRecordid = params.get("specialityRecordid").toString();
        if (score1 > score2) {
            if (studentSpecialityService.selectCommonReolace(courseid1, courseid2) > 0) {
                studentSpecialityService.updateScore(params);
                studentSpecialityService.updateScore1(params);
                a=1;
            }
            if (studentSpecialityService.selectCourseReplace(courseid1, courseid2, specialityRecordid) > 0) {
                studentSpecialityService.updateScore(params);
                studentSpecialityService.updateScore1(params);
                b=1;
            }
//            //课程复选和课程多考  不确定功能暂时不写
//            if (studentSpecialityService.selectCourseAppend(courseid1, courseid2, specialityRecordid) > 0) {
//                  c=1;
//            }
//            if (studentSpecialityService.selectCourseCheck(courseid1, courseid2) > 0) {
//                  d=1;
//            }
            if(a ==0 && b ==0 && c ==0 && d ==0){
                return R.error(0,"没有找到顶替关系");
            }

        } else {
            return R.error(0, "被顶替成绩大于顶替成绩，操作失败");
        }
        a=b=c=d=0;
        return R.ok();
    }


    /**
     * 取消顶替功能键
     * map中需要包含 courseid1   courseid2  score1  score2 studentid
     * （scoreCourseid成绩表的courseid），（courseid课程表成绩）
     * 从顶替表中查找是否存在可顶替课程，如果存在，被替换课程源课程添加信息；替换课程使用状态改为·已使用·
     */
    @Transactional
    @PostMapping("editReplaceLeft")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editReplaceLeft(@RequestParam Map<String, Object> params) {
        String courseid1 = params.get("courseid1").toString();
        String courseid2 = params.get("courseid2").toString();
        String specialityRecordid = params.get("specialityRecordid").toString();
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        if (studentSpecialityService.selectCommonReolace(courseid1, courseid2) > 0) {
            studentSpecialityService.updateScore(params);
            studentSpecialityService.updateScore1(params);
            a=1;
        }
        if (studentSpecialityService.selectCourseReplace(courseid1, courseid2, specialityRecordid) > 0) {
            studentSpecialityService.updateScore(params);
            studentSpecialityService.updateScore1(params);
            b=1;
        }
//            //课程复选和课程多考  不确定功能暂时不写
//            if (studentSpecialityService.selectCourseAppend(courseid1, courseid2, specialityRecordid) > 0) {
//                  c=1;
//            }
//            if (studentSpecialityService.selectCourseCheck(courseid1, courseid2) > 0) {
//                  d=1;
//            }
        if(a ==0 && b ==0 && c ==0 && d ==0){
            return R.error(0,"没有找到可以取消的顶替关系");
        }

        studentSpecialityService.updateScoreBack(params);
        studentSpecialityService.updateScoreBack1(params);
        a=b=c=d=0;
        return R.ok();
    }

    @GetMapping("listStudentAudit")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public PageUtils listStudentAudit(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.listStudent(query);
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
            item.setGradAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "grad_audit_status", item.getGradAuditStatus()));
            item.setPrintCertificate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "print_certificate", item.getPrintCertificate()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentSpecialityService.countStudent(query);
        PageUtils pageUtils = new PageUtils(studentSpecialityList, total);
        return pageUtils;
    }

    @RequestMapping(value = "/updateAuditStudent", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R updateAuditStudent(@RequestParam("ids[]") Long[] ids, @RequestParam("gradAuditStatus") String gradAuditStatus) {
        int i = 0;
        String msg = "";
        List<StudentSpecialityDO> list = studentSpecialityService.selectAuditStudent(ids);
        if (list.size() == 0) {
            return R.error(0, "请先选择要审核考生");
        }
        String gradAuditOperator = ShiroUtils.getUserId().toString();
        for (int k = 0; k < ids.length; k++) {
            i = studentSpecialityService.updateAuditStudent(ids[k], gradAuditStatus, gradAuditOperator);
            if (i == 0) {
                if (k == 0) {
                    msg += "id为" + ids[k];
                } else if (k == ids.length - 1) {
                    msg += ids[k] + "的考生审核失败";
                } else {
                    msg += ids[k] + ",";
                }
            }
        }
        return R.ok(msg);
    }

    @RequestMapping(value = "/updateGradute", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R updateGradute(@RequestParam("ids[]") Long[] ids) {
        int i = 0;
        String msg = "";
        List<StudentSpecialityDO> list = studentSpecialityService.selectAuditStudent(ids);
        if (list.size() == 0) {
            return R.error(0, "请先选择要退回考生");
        }
        for(int p = 0;p<list.size();p++){
            if("b".equals(list.get(p).getGradAuditStatus())){
                if (p == 0) {
                    msg += "准考证为" + list.get(p).getStudentid();
                } else if (p == list.size() - 1) {
                    msg += list.get(p).getStudentid() + "的考生已审核，不能退回";
                } else {
                    msg += ","+list.get(p).getStudentid() ;
                }
            }
        }

        String gradAuditOperator = ShiroUtils.getUserId().toString();
        for (int k = 0; k < ids.length; k++) {
            i = studentSpecialityService.updateGradute(ids[k],gradAuditOperator);
            if (i == 0) {
                if (k == 0) {
                    msg += "id为" + ids[k];
                } else if (k == ids.length - 1) {
                    msg += ids[k] + "的考生退回失败";
                } else {
                    msg += ","+ids[k] ;
                }
            }
        }
        return R.ok(msg);
    }

    @RequestMapping(value = "/selectStudentInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public StudentSpecialityDO selectStudentInfo(@RequestParam Map<String,Object> map) {
        Long id = Long.valueOf(map.get("id").toString());
        StudentSpecialityDO studentSpeciality = studentSpecialityService.selectStudentInfo(id);
        studentSpeciality.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", studentSpeciality.getTeachSiteid()+""));
        studentSpeciality.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", studentSpeciality.getCollegeid()+""));
        studentSpeciality.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", studentSpeciality.getSchoolid()+""));
        studentSpeciality.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpeciality.getSpecialityid()));
        studentSpeciality.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", studentSpeciality.getType()));
        studentSpeciality.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", studentSpeciality.getGender()));
        studentSpeciality.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", studentSpeciality.getPolitics()));
        studentSpeciality.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", studentSpeciality.getNation()));
        studentSpeciality.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", studentSpeciality.getProfession()));
        studentSpeciality.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", studentSpeciality.getStatus()));
        studentSpeciality.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", studentSpeciality.getClassify()));
        studentSpeciality.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student", "audit_status", studentSpeciality.getAuditStatus()));
        studentSpeciality.setUpdateDate(DateUtils.format(studentSpeciality.getUpdateDate(), DateUtils.DATE_PATTERN));
        return studentSpeciality;
    }

    @GetMapping("/check")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String check(){
        return "center/student/studentAudit/check";
    }
    @GetMapping("/checkRegister")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String checkRegister(){
        return "center/student/studentAudit/checkRegister";
    }

    @GetMapping("/insertPage")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String insertPage(){
      return "center/student/scoreOld/scoreOld";
    }
    //补录
    @Transactional
    @PostMapping("/insertStudent")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public R insertStudent(@RequestParam Map<String ,Object> map){
        ScoreDO score = new ScoreDO();
            score.setSpecialityRecordid(Long.parseLong(map.get("specialityRecordid").toString()));
            score.setStudentid(map.get("studentid").toString());
            score.setCourseid(map.get("courseid").toString());
            score.setGrade(Float.parseFloat(map.get("grade").toString()));
            score.setCourseid(map.get("courseid").toString());
            score.setType("h");
            score.setGrade(Float.parseFloat(map.get("grade").toString()));
            score.setExamFlag("0");
            score.setStatus("0");
            score.setUseStatus("a");
            score.setFlag("a");
            score.setOperator(ShiroUtils.getUserId().toString());

        int i = scoreService.save(score);
        ScoreOldDO scoreOld = new ScoreOldDO();
        scoreOld.setStudentid(map.get("studentid").toString());
        scoreOld.setCourseid(map.get("courseid").toString());
        scoreOld.setGrade(Float.parseFloat(map.get("grade").toString()));
        scoreOld.setStatus("a");
        scoreOld.setRemark(map.get("remark").toString());
        scoreOld.setOperator(ShiroUtils.getUserId().toString());
        int k = scoreOldService.save(scoreOld);
        if(i!=0 && k!=0){
            return R.ok();
        }else{
            return R.error("补录成绩失败，请检查考生信息并重试");
        }
    }

    @GetMapping("/showSubjectBL")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String showSubjectBL(){
        return "center/student/scoreOld/showSubject";
    }


    @GetMapping("/listOldCourse")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public PageUtils listOldCourse(@RequestParam Map<String ,Object> map){
        Query query = new Query(map);
        List<OldCourseDO> list = oldCourseService.list(query);
        int total = oldCourseService.count(query);
        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;

    }

    //申请
    @Transactional
    @PostMapping("/apply")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public R apply(@RequestParam Map<String ,Object> map){
        Map<String, Object> map1 = new HashMap<String,Object>();
        map1.put("specialityRecordid",map.get("specialityRecordid"));
        map1.put("limit",20);
        map1.put("offset",0);
     List<SpecialityCourseDO> specialityCourse = specialityCourseService.list(map1);
     if(specialityCourse.size() ==0){
         return R.error(0,"没有找到该报考专业的课程信息");
     }
     List<ScoreDO> score = scoreService.getScoreStudent(map.get("studentid").toString(),map.get("specialityRecordid").toString(),specialityCourse);
     if(score.size()!=0){
         for(int i =0;i<score.size();i++){
             if(score.get(i).getGrade()>= 60){
                 continue;
             }else{
                 if(score.get(i).getSourceCourseid() != null && "".equals(score.get(i).getSourceCourseid())){
                     continue;
                 }else{
                     return R.error(0,"请确认所有科目成绩大于或等于及格线！");
                 }
             }
         }
         StudentSpecialityDO studentSpeciality = studentSpecialityService.selectInfomation(map.get("studentid").toString(),map.get("specialityRecordid").toString());
         if("".equals(studentSpeciality.getGradSpecialityid()) || studentSpeciality.getGradSpecialityid() ==null){
             return R.error(0,"没找到该考生原专业编号");
         }
         if("b".equals(studentSpeciality.getStatus())){
             return R.error(0,"该考生已毕业，无法进行修改");
         }
         if("a".equals(map.get("graduate").toString()) && "a".equals(studentSpeciality.getStatus())){
             return R.error(0,"该考生还没有提交毕业申请");
         }
         if("b".equals(map.get("graduate").toString()) && "b".equals(studentSpeciality.getStatus())){
             return R.error(0,"该考生已经提交了毕业申请，请勿重复操作");
         }
         studentSpecialityService.getInfomation(map.get("studentid").toString(),map.get("specialityRecordid").toString(),map.get("graduate").toString());
     }else{
         return R.error(0,"没找到该考生参考成绩");
     }

        return R.ok();

    }

    @GetMapping("/regionStudentApply")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    String regionStudentApply(){
        return "region/student/schoolStudentApply/studentFileStudent";
    }

    @GetMapping("/pageToAdd")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String pageToAdd(Model model){
        model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        return "region/student/schoolStudentApply/add";
    }

    @GetMapping("/regionInsertPage")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String regionInsertPage(){
        return "region/student/scoreOld/scoreOld";
    }

    @GetMapping("/specialityAdd")
    @RequiresPermissions("student:studentSpecialityApply:studentSpeciality")
    String specialityAdd(){
        return "region/student/schoolStudentApply/gradeEntering";
    }

    //tianjia新专业
    @Transactional
    @PostMapping("/insertNewSpeciality")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public R insertNewSpeciality(@RequestParam Map<String ,Object> map){
     StudentSpecialityDO studentSpeciality = studentSpecialityService.selectStudentSpeciality(map.get("studentid").toString());
     if(studentSpeciality == null || "".equals(studentSpeciality)){
         return R.error(0,"该考生不在在籍状态！！");
     }
     studentSpeciality.setGradSchool(map.get("gradSchool").toString());
     if(!"".equals(map.get("gradCertificate").toString()) || map.get("gradCertificate").toString() != null){
         studentSpeciality.setGradCertificate(map.get("gradCertificate").toString());
     }
     if(!"".equals(map.get("gradSpecialityid").toString()) || map.get("gradSpecialityid").toString() != null){
         studentSpeciality.setGradSpecialityid(map.get("gradSpecialityid").toString());
         }
     if(!"".equals(map.get("education").toString()) || map.get("education").toString() != null){
         studentSpeciality.setEducation(map.get("education").toString());
         }
     if(!"".equals(map.get("specialityRecordid").toString()) || map.get("specialityRecordid").toString() != null){
         studentSpeciality.setSpecialityRecordid(Long.parseLong(map.get("specialityRecordid").toString()));
         }
     studentSpeciality.setStatus("a");
     studentSpeciality.setGraduate("a");
     if(studentSpecialityService.save(studentSpeciality)!=0){
         return R.ok();
     }
        return R.ok();

    }

    @Transactional
    @GetMapping("/selectStudent")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public List<StudentDO> selectStudent(@RequestParam Map<String ,Object> map){
        List<StudentDO> list = new ArrayList<StudentDO>();
        StudentDO student = studentService.get(map.get("id").toString());
        student.setStudentid(student.getId());
        student.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", student.getGender()));
        student.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", student.getProfession()));
        student.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", student.getCertificateType()));
        student.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", student.getStatus()));
        student.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", student.getNation()));
        list.add(student);
        return list;
    }

    @GetMapping("/printPage")
    String printPage(){
        return "region/student/schoolStudentApply/applyExcel";
    }
}
