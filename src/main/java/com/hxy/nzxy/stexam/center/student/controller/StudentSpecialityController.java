package com.hxy.nzxy.stexam.center.student.controller;

import java.io.*;
import java.util.*;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.center.plan.service.OldCourseService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityCourseService;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldService;
import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.center.student.service.StudentService;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentSpeciality")
public class StudentSpecialityController extends SystemBaseController {
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
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
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
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
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
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.list(query);
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid())+item.getDirection());
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
    @GetMapping("/listStudentSpeciality")
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
    public List<StudentSpecialityDO> listStudentSpeciality(@RequestParam Map<String, Object> map) {
        //查询列表数据
        String studentid = map.get("studentid").toString();
        List<StudentSpecialityDO> studentSpecialityList = studentSpecialityService.listStudentSpeciality(studentid);
        for (StudentSpecialityDO item : studentSpecialityList) {
            item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid() + ""));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid() + ""));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid() + ""));
            item.setGradSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getGradSpecialityid()));
            item.setSpecialityid(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "id", item.getSpecialityRecordid().toString()));
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
        return studentSpecialityList;
    }

    @ResponseBody
    @GetMapping("/information")
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
    public StudentSpecialityDO information(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Long id = Long.valueOf(params.get("id").toString());
        StudentSpecialityDO studentSpeciality = studentSpecialityService.get(id);
        studentSpeciality.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", studentSpeciality.getTeachSiteid() + ""));
        studentSpeciality.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", studentSpeciality.getCollegeid() + ""));
        studentSpeciality.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", studentSpeciality.getSchoolid() + ""));
        studentSpeciality.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpeciality.getSpecialityid())+studentSpeciality.getDirection());
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
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
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

    @GetMapping("/applyExcel")
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
    String applyExcel() {
        return "center/student/studentAudit/applyExcel";
    }

    @GetMapping("/add")
    @RequiresPermissions("student:studentSpeciality:add")
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
    @RequiresPermissions("student:studentSpeciality:edit")
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
    @RequiresPermissions("student:studentSpeciality:add")
    String addApply(@PathVariable("id") Long id, Model model) {
        model.addAttribute("stu_student_speciality_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "status"));
        model.addAttribute("stu_student_speciality_graduate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "graduate"));
        model.addAttribute("stu_student_speciality_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "audit_status"));
        model.addAttribute("stu_student_speciality_grad_audit_status", commonService.listFieldDict(getAppName(), "stu_student_speciality", "grad_audit_status"));
        model.addAttribute("stu_student_speciality_print_certificate", commonService.listFieldDict(getAppName(), "stu_student_speciality", "print_certificate"));
        model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
        StudentSpecialityDO studentSpeciality = studentSpecialityService.get(id);
        model.addAttribute("studentSpeciality", studentSpeciality);
        return "school/student/schoolStudentApply/add";
    }

    @GetMapping("/editApply/{studentid}")
    @RequiresPermissions("student:studentSpeciality:edit")
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
        model.addAttribute("student", student);
        return "school/student/schoolStudentApply/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("student:studentSpeciality:add")
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
    @RequiresPermissions("student:studentSpeciality:edit")
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
    @RequiresPermissions("student:studentSpeciality:remove")
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
    @RequiresPermissions("student:studentSpeciality:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        studentSpecialityService.batchRemove(ids);
        return R.ok();
    }

    //专业导入页面
    @GetMapping("/importData")
    @RequiresPermissions("student:studentSpeciality:importData")
    String importData() {

        return "center/student/studentSpeciality/importData";
    }

    /**
     * 批量导入
     */
    @PostMapping("/savBathData")
    @RequiresPermissions("student:studentSpeciality:importData")
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
    @RequiresPermissions("student:studentSpeciality:searchOutExcel")
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
        for (StudentSpecialityDO item : studentStudentList) {
            item.setGraduate(FieldDictUtil.get(getAppName(), "stu_student_speciality", "graduate", item.getGraduate()));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setGradSpecialityName("(" + item.getGradSpecialityid() + ")" + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getGradSpecialityid()));
            item.setSpecialityName("(" + item.getSpecialityid() + ")" + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));

        }
        int total = studentSpecialityService.countQu(query);
        PageUtils pageUtils = new PageUtils(studentStudentList, total);
        return pageUtils;
    }

    @GetMapping("/pageInto")
    @RequiresPermissions("student:studentSpeciality:studentSpeciality")
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
       /* for (int i = 0; i < studentStudentList.size(); i++) {
            if (!"".equals(studentStudentList.get(i).getSourceCourseid()) && studentStudentList.get(i).getSourceCourseid() != null) {
                if (studentStudentList.get(i).getGrade() == null || "".equals(studentStudentList.get(i).getGrade())) {
                    studentStudentList.get(i).setGrade("0");
                    grade = Float.parseFloat(studentStudentList.get(i).getGrade());
                }
                //判断源课程的成绩是否比现有课程成绩大，如果大，那么显示替换成源课程成绩，毕业申请判断时可能也要用
                float float1=studentSpecialityService.getSourceCourse(studentStudentList.get(i).getSourceCourseid().toString(), studentStudentList.get(i).getSpecialityRecordid().toString(), studentStudentList.get(i).getStudentid().toString());
                if (float1> grade) {
                    studentStudentList.get(i).setGrade(String.valueOf(float1));                }
            }
        }*/
        if (studentStudentList.size() != 0) {
            for (StudentSpecialityDO item : studentStudentList) {
                item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_course", "type", item.getType()));

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
        params.put("userStatus","a");
        List<ScoreDO> studentStudentList = studentSpecialityService.listScore(params);
        for (ScoreDO item : studentStudentList) {
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
            item.setUseStatus(FieldDictUtil.get(getAppName(), "stu_score", "use_status", item.getStatus()));
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
        StringBuilder msg = new StringBuilder();

        //查找规则-查看列表中的课程是否存在顶替规则
        List<CommonCourseReplaceDO> courseList = studentSpecialityService.getCommonCourseReplace(params);
        if (courseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }


        Map<String, List<CommonCourseReplaceDO>> map = new HashMap<>();
        int i=1;
        for(CommonCourseReplaceDO course : courseList){
            String key=course.getCourseid()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getCourseid());
            if(course.getAppendCourseid1()!=null&&!course.getAppendCourseid1().equals("")){
                key+=","+course.getAppendCourseid1()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid1());
            }
            if(course.getAppendCourseid2()!=null&&!course.getAppendCourseid2().equals("")){
                key+=","+course.getAppendCourseid2()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid2());
            }
            if(course.getAppendCourseid3()!=null&&!course.getAppendCourseid3().equals("")){
                key+=","+course.getAppendCourseid3()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid3());
            }
            if(course.getAppendCourseid4()!=null&&!course.getAppendCourseid4().equals("")){
                key+=","+course.getAppendCourseid4()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid4());
            }
            key=i+"：如果考生不考【"+key+"】";
            if(course.getType().equals("a")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程【";
            }
            if(course.getType().equals("b")){
                key+="考生必须选择如下课程中的共计"+course.getLeastScore()+"分的课程【";
            }
            if(course.getType().equals("c")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程共计"+course.getLeastScore()+"分的课程【";
            }
            if(map.containsKey(key)){//map中存在此id，将数据存放当前key的map中
                map.get(key).add(course);
            }else{//map中不存在，新建key，用来存放数据
                List<CommonCourseReplaceDO> tmpList = new ArrayList<>();
                tmpList.add(course);
                map.put(key, tmpList);
            }
        }

        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            List<CommonCourseReplaceDO> courseReplaceDOS = map.get(key);
            msg.append(key);
            for (CommonCourseReplaceDO        courseReplaceDO:courseReplaceDOS){
                msg.append(courseReplaceDO.getCourseidReplace()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplaceDO.getCourseidReplace())+";");

            }
        }
        msg.append("】</br>");
        return msg.toString();
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
        //


        //查找规则-查看列表中的课程是否存在顶替规则
        List<CourseReplaceDO> courseList = studentSpecialityService.listCourseReplace(params.get("specialityRecordid").toString());

        if (courseList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }

        Map<String, List<CourseReplaceDO>> map = new HashMap<>();
        int i=1;
        for(CourseReplaceDO course : courseList){
            String key=course.getCourseid()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getCourseid());
            if(course.getAppendCourseid1()!=null&&!course.getAppendCourseid1().equals("")){
                key+=","+course.getAppendCourseid1()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid1());
            }
            if(course.getAppendCourseid2()!=null&&!course.getAppendCourseid2().equals("")){
                key+=","+course.getAppendCourseid2()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid2());
            }
            if(course.getAppendCourseid3()!=null&&!course.getAppendCourseid3().equals("")){
                key+=","+course.getAppendCourseid3()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid3());
            }
            if(course.getAppendCourseid4()!=null&&!course.getAppendCourseid4().equals("")){
                key+=","+course.getAppendCourseid4()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", course.getAppendCourseid4());
            }
            key=i+"：如果考生不考【"+key+"】";
            if(course.getType().equals("a")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程【";
            }
            if(course.getType().equals("b")){
                key+="考生必须选择如下课程中的共计"+course.getLeastScore()+"分的课程【";
            }
            if(course.getType().equals("c")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程共计"+course.getLeastScore()+"分的课程【";
            }
            if(map.containsKey(key)){//map中存在此id，将数据存放当前key的map中
                map.get(key).add(course);
            }else{//map中不存在，新建key，用来存放数据
                List<CourseReplaceDO> tmpList = new ArrayList<>();
                tmpList.add(course);
                map.put(key, tmpList);
            }
        }

        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            List<CourseReplaceDO> courseReplaceDOS = map.get(key);
            msg.append(key);
            for (CourseReplaceDO courseReplaceDO:courseReplaceDOS){
                    msg.append(courseReplaceDO.getCourseidReplace()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplaceDO.getCourseidReplace())+";");

            }
         }
        msg.append("】</br>");
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

      //  List<CourseAppendItemDO> itemList = studentSpecialityService.listCourseAppendItem(courseList);


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

        List<CourseCheckDO> itemList = studentSpecialityService.listCourseCheckItem(params);
        if (itemList.size() == 0) {
            msg.append("没有找到相关数据");
            return msg.toString();
        }

        Map<String, List<CourseCheckDO>> map = new HashMap<>();
        int i=1;
        for(CourseCheckDO course : itemList){
            String key=course.getName();

            key=i+"："+key+"<br/>";
            if(course.getType().equals("a")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程【";
            }
            if(course.getType().equals("b")){
                key+="考生必须选择如下课程中的共计"+course.getLeastScore()+"分的课程【";
            }
            if(course.getType().equals("c")){
                key+="考生必须选择如下课程中的"+course.getLeastNum()+"门课程共计"+course.getLeastScore()+"分的课程【";
            }
            if(map.containsKey(key)){//map中存在此id，将数据存放当前key的map中
                map.get(key).add(course);
            }else{//map中不存在，新建key，用来存放数据
                List<CourseCheckDO> tmpList = new ArrayList<>();
                tmpList.add(course);
                map.put(key, tmpList);
            }
        }

        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            List<CourseCheckDO> courseCheckList = map.get(key);
            msg.append(key);
            for (CourseCheckDO courseCheckDO:courseCheckList){
                msg.append(courseCheckDO.getCourseid()+" "+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseCheckDO.getCourseid())+";");

            }
        }
        msg.append("】</br>");

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
    @PostMapping("/editStudentInfo")
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
    @PostMapping("/editDoubleRight")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editDoubleRight(@RequestParam Map<String, Object> params) {
        String msg = "";
        int sum = 0;
        //课程列表
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);
        //成绩列表
        List<ScoreDO> studentStudentList2 = studentSpecialityService.listScore(params);
        for (int i = 0; i < studentStudentList.size(); i++) {
            for (int k = 0; k < studentStudentList2.size(); k++) {
                if (studentStudentList.get(i).getCourseid().equals(studentStudentList2.get(k).getCourseid())) {
                    studentSpecialityService.editDoubleRight(studentStudentList.get(i).getCourseid(), params.get("specialityRecordid").toString(), params.get("studentid").toString(), params.get("type").toString());
                    sum = sum+1;
                }
            }


        }
        msg = "增加全部匹配课程成功";
        if (sum == 0) {
            return R.error("没有找到该考生合格成绩！");
        }
        if(params.get("type").equals("a")){
            msg = "取消全部匹配课程成功";
        }
        return R.ok(msg);
    }

    /**
     * < 和 >和功能键
     * map中需要包含 collegeid  teachSiteid specialityRecordid（专业开设id）studentid, courseid  type-->a  未使用  b--》使用
     */
    @PostMapping("/editFloatRight")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editFloatRight(@RequestParam Map<String, Object> params) {
        String msg = "";
        //课程列表
        List<StudentSpecialityDO> studentStudentList = studentSpecialityService.listCourseScore(params);

        for (int i = 0; i < studentStudentList.size(); i++) {
            if (studentStudentList.get(i).getCourseid().equals(params.get("courseid").toString())) {
                int i1 = studentSpecialityService.editDoubleRight(params.get("courseid").toString(), params.get("specialityRecordid").toString(), params.get("studentid").toString(), params.get("type").toString());
              if(i1>0){
                  return "a".equals(params.get("type")) ? R.ok("单项匹配取消成功！") : R.ok("单项匹配成功！");
              }
                break;
            }
        }

        msg = "该课程在专业计划中不存在！";
        return R.error( msg);
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
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        String courseid1 = params.get("courseid1").toString();
        String courseid2 = params.get("courseid2").toString();
        float score1 = Float.parseFloat(params.get("score1").toString());
        float score2 = Float.parseFloat(params.get("score2").toString());
        String specialityRecordid = params.get("specialityRecordid").toString();
        if (score1 > score2) {
            if (studentSpecialityService.selectCommonReolace(courseid1, courseid2) > 0) {
                studentSpecialityService.updateScore(params);
                studentSpecialityService.updateScore1(params);
                a = 1;
            }
            if (studentSpecialityService.selectCourseReplace(courseid1, courseid2, specialityRecordid) > 0) {
                studentSpecialityService.updateScore(params);
                studentSpecialityService.updateScore1(params);
                b = 1;
            }
//            //课程复选和课程多考  不确定功能暂时不写
//            if (studentSpecialityService.selectCourseAppend(courseid1, courseid2, specialityRecordid) > 0) {
//                  c=1;
//            }
//            if (studentSpecialityService.selectCourseCheck(courseid1, courseid2) > 0) {
//                  d=1;
//            }
            if (a == 0 && b == 0 && c == 0 && d == 0) {
                return R.error(0, "没有找到顶替关系");
            }

        } else {
            return R.error(0, "被顶替成绩大于顶替成绩，操作失败");
        }
        a = b = c = d = 0;
        return R.ok();
    }


    /**
     * 取消顶替功能键
     * map中需要包含 courseid1   courseid2  score1  score2 studentid
     * （scoreCourseid成绩表的courseid），（courseid课程表成绩）
     * 从顶替表中查找是否存在可顶替课程，如果存在，被替换课程源课程添加信息；替换课程使用状态改为·已使用·
     */
    @Transactional
    @PostMapping("/editReplaceLeft")
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public R editReplaceLeft(@RequestParam Map<String, Object> params) {

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        params.put("type","g");
        params.put("userStatus","b");
        List<ScoreDO> scoreDOS = studentSpecialityService.listScoreReplace(params);
        if(scoreDOS.size()>0){
            scoreService.remove(scoreDOS.get(0).getId());
            params.put("courseid1",scoreDOS.get(0).getSourceCourseid());
            studentSpecialityService.updateScoreBack(params);
            return R.ok();
        }

//            //课程复选和课程多考  不确定功能暂时不写
//            if (studentSpecialityService.selectCourseAppend(courseid1, courseid2, specialityRecordid) > 0) {
//                  c=1;
//            }
//            if (studentSpecialityService.selectCourseCheck(courseid1, courseid2) > 0) {
//                  d=1;
//            }
        if (a == 0 && b == 0 && c == 0 && d == 0) {
            return R.error(0, "没有找到可以取消的顶替关系");
        }


        a = b = c = d = 0;
        return R.ok();
    }

    @GetMapping("/listStudentAudit")
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
        for (int p = 0; p < list.size(); p++) {
            if ("b".equals(list.get(p).getGradAuditStatus())) {
                if (p == 0) {
                    msg += "准考证为" + list.get(p).getStudentid();
                } else if (p == list.size() - 1) {
                    msg += list.get(p).getStudentid() + "的考生已审核，不能退回";
                } else {
                    msg += "," + list.get(p).getStudentid();
                }
            }
        }

        String gradAuditOperator = ShiroUtils.getUserId().toString();
        for (int k = 0; k < ids.length; k++) {
            i = studentSpecialityService.updateGradute(ids[k], gradAuditOperator);
            if (i == 0) {
                if (k == 0) {
                    msg += "id为" + ids[k];
                } else if (k == ids.length - 1) {
                    msg += ids[k] + "的考生退回失败";
                } else {
                    msg += "," + ids[k];
                }
            }
        }
        return R.ok(msg);
    }

    @RequestMapping(value = "/selectStudentInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    public StudentSpecialityDO selectStudentInfo(@RequestParam Map<String, Object> map) {
        Long id = Long.valueOf(map.get("id").toString());
        StudentSpecialityDO studentSpeciality = studentSpecialityService.selectStudentInfo(id);
        studentSpeciality.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", studentSpeciality.getTeachSiteid() + ""));
        studentSpeciality.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", studentSpeciality.getCollegeid() + ""));
        studentSpeciality.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", studentSpeciality.getSchoolid() + ""));
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
    String check() {
        return "center/student/studentAudit/check";
    }

    @GetMapping("/checkRegister")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String checkRegister() {
        return "center/student/studentAudit/checkRegister";
    }

    @GetMapping("/insertPage")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String insertPage() {
        return "center/student/scoreOld/scoreOld";
    }

    //补录
    @Transactional
    @PostMapping("/insertStudent")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public R insertStudent(@RequestParam Map<String, Object> map) {
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
        if (i != 0 && k != 0) {
            return R.ok();
        } else {
            return R.error("补录成绩失败，请检查考生信息并重试");
        }
    }

    @GetMapping("/showSubjectBL")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    String showSubjectBL() {
        return "region/student/scoreOld/showSubject";
    }


    @GetMapping("/listOldCourse")
    @RequiresPermissions("student:studentFileStudent:studentStudent")
    @ResponseBody
    public PageUtils listOldCourse(@RequestParam Map<String, Object> map) {
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
    public R apply(@RequestParam Map<String, Object> map) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("specialityRecordid", map.get("specialityRecordid"));
        map1.put("limit", 20);
        map1.put("offset", 0);
        List<SpecialityCourseDO> specialityCourse = specialityCourseService.list(map1);
        List<ScoreDO> score = scoreService.getScoreStudent(map.get("studentid").toString(), map.get("specialityRecordid").toString(), specialityCourse);
        if (score.size() != 0) {
            for (int i = 0; i < score.size(); i++) {
                if (score.get(i).getGrade() >= 60) {
                    continue;
                } else {
                    if (score.get(i).getSourceCourseid() != null && "".equals(score.get(i).getSourceCourseid())) {
                        continue;
                    } else {
                        return R.error(0, "请确认所有科目成绩大于或等于及格线！");
                    }
                }
            }
            StudentSpecialityDO studentSpeciality = studentSpecialityService.selectInfomation(map.get("studentid").toString(), map.get("specialityRecordid").toString());
            if ("".equals(studentSpeciality.getGradSpecialityid()) || studentSpeciality.getGradSpecialityid() == null) {
                return R.error(0, "没找到该考生原专业编号");
            }
            if ("b".equals(studentSpeciality.getStatus())) {
                return R.error(0, "该考生已毕业，无法进行修改");
            }
            if ("a".equals(map.get("graduate").toString()) && "a".equals(studentSpeciality.getStatus())) {
                return R.error(0, "该考生还没有提交毕业申请");
            }
            if ("b".equals(map.get("graduate").toString()) && "b".equals(studentSpeciality.getStatus())) {
                return R.error(0, "该考生已经提交了毕业申请，请勿重复操作");
            }
            studentSpecialityService.getInfomation(map.get("studentid").toString(), map.get("specialityRecordid").toString(), map.get("graduate").toString());
        } else {
            return R.error(0, "没找到该考生参考成绩");
        }

        return R.ok();

    }

    //打印审批表学分学科和学分要求
    @GetMapping("/applyExcelScore")
    @ResponseBody
    public List<ScoreDO> applyExcelScore(@RequestParam Map<String,Object> map){
        String studentid = map.get("studentid").toString();
        String specialityRecordid = map.get("specialityRecordid").toString();
        List<ScoreDO> scoreStudent = studentSpecialityService.applyExcelScore(studentid,specialityRecordid);
        for(ScoreDO item : scoreStudent){
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            if("".equals(item.getCourseName()) || item.getCourseName() == null){
                item.setCourseName(FieldDictUtil.get(getAppName(), "pla_old_course_nzxy", "id", item.getCourseid()));
                item.setCourseScore("0");
            }
            item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setStatus(FieldDictUtil.get(getAppName(), "stu_score", "status", item.getStatus()));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return scoreStudent;
    }

    //院校端毕业生申请表格打印
    @GetMapping("/schoolPrint")
    @ResponseBody
   public List<ScoreDO> schoolPrnt(@RequestParam Map<String, Object> map){
        List<ScoreDO> list = scoreService.schoolScoreStudent(map);
        for(ScoreDO item : list){
                item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "name", item.getCourseid()));
                if( item.getSourceCourseid() != null && !"".equals( item.getSourceCourseid())){
                    item.setSourceCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "name", item.getSourceCourseid().toString()));
                }

                item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
        }
        return list;
    }

}
