package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.center.school.service.SchoolSpecialityRegService;
import com.hxy.nzxy.stexam.center.school.service.SpecialityRegService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.school.student.service.StudentFileStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentStudentService;
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
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentFile")
public class StudentFileController extends SystemBaseController{
	@Autowired
	private StudentFileStudentService studentFileStudentService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private StudentSpecialityStudentService studentSpecialityStudentService;
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;
	@Autowired
	private SpecialityRegService specialityRegService;
	@Autowired
	private SchoolSpecialityRegService schoolSpecialityRegService;
	@GetMapping()
	@RequiresPermissions("student:studentFile:student")
	String StudentStudent(Model model){
		model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
		model.addAttribute("stu_student_confirm_status", commonService.listFieldDict(getAppName(), "stu_student", "confirm_status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));

		return "center/student/studentFileStudent/studentFileStudent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentFile:student")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentDO> studentStudentList = studentFileStudentService.list(query);
        for (StudentDO item : studentStudentList) {

			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));

			item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
			item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
			item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
			item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
			item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
			item.setSpecialityName(item.getSpecialityid()+item.getSpecialityName()+item.getDirection());
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentFileStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:student:add")
	String add(Model model){
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));



		return "center/student/studentFile/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentFile:edit")
	String edit(@PathVariable("id") String id,Model model){
		//报考专业
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(Long.valueOf(id));
	//	StudentDO studentStudent = studentStudentService.get(studentSpecialityStudent.getStudentid());
		StudentDO studentStudent = studentFileStudentService.get(studentSpecialityStudent.getStudentid());
		studentStudent.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", studentStudent.getGroupid()+""));
		model.addAttribute("studentStudent", studentStudent);
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
		//报考专业
	//	StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.getByStudentId(id);
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegSchoolService.getSelect(studentSpecialityStudent.getSchoolSpecialityRegid());
		schoolSpecialityRegDO.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegDO.getStatus()));
		schoolSpecialityRegDO.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegDO.getAuditStatus()));
		schoolSpecialityRegDO.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegDO.getClassify()));
		schoolSpecialityRegDO.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegDO.getType()));
		schoolSpecialityRegDO.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegDO.getMethod()));
		schoolSpecialityRegDO.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegDO.getEducateLength()));
		schoolSpecialityRegDO.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegDO.getRegSeason()));
		studentSpecialityStudent.setSchoolSpecialityRegName(schoolSpecialityRegDO.getSpecialityId()+schoolSpecialityRegDO.getSpecialityName()+"["+schoolSpecialityRegDO.getRegYear()+" "+schoolSpecialityRegDO.getRegSeason()+"]"+"["+schoolSpecialityRegDO.getClassify()+" "+schoolSpecialityRegDO.getType()+" "+schoolSpecialityRegDO.getEducateLength()+"]");
		SpecialityDO specialityDO = specialityService.get(studentSpecialityStudent.getGradSpecialityid());
		studentSpecialityStudent.setGradSpecialityName("("+specialityDO.getId()+")"+specialityDO.getName());
		model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);

		return "center/student/studentFile/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentFile:add")
	public R save( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
        studentStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentFileStudentService.save(studentStudent,studentSpeciality)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentFile:edit")
	public R update( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
	    studentStudent.setOperator(ShiroUtils.getUserId().toString());
		studentFileStudentService.update(studentStudent,studentSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentFile:remove")
	public R remove( String id){
		//报考专业
		if(studentSpecialityStudentService.remove(Long.valueOf(id))>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentFile:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentFileStudentService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 显示弹出页面
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")

	String showSubject( ){

		return "center/student/studentFileStudent/showSubject";
	}

	@ResponseBody
	@GetMapping("/listshowSubject")
	public PageUtils listshowSubject (@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<SpecialityRegDO> specialityRegList = specialityRegService.list(query);
		for (SpecialityRegDO item : specialityRegList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", String.valueOf(item.getSchoolid())));
			item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", String.valueOf(item.getSubjectCode())));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

		}
		int total = specialityRegService.count(query);
		PageUtils pageUtils = new PageUtils(specialityRegList, total);
		return pageUtils;
	}
	@GetMapping("/schoolSpecialityReg")
	String SchoolSpecialityReg(Model model){
		model.addAttribute("sch_school_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "status"));
		model.addAttribute("sch_school_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "audit_status"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		model.addAttribute("sch_school_speciality_reg_reg_season", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season"));
		return "center/student/studentFileStudent/schoolSpecialityReg";
	}

	@ResponseBody
	@GetMapping("/listSchoolSpecialityReg")
	public PageUtils listSchoolSpecialityReg(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SchoolSpecialityRegDO> schoolSpecialityRegList = schoolSpecialityRegService.list(query);
		for (SchoolSpecialityRegDO item : schoolSpecialityRegList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", item.getMethod()));
			item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
			item.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", item.getRegSeason()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolSpecialityRegService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSpecialityRegList, total);
		return pageUtils;
	}

	@GetMapping("/reg")
	String reg(Model model,String id){
		model.addAttribute("studentSpecialityid",id);
		return "center/student/studentFileStudent/studentRegStudent";
	}

	/**
	 * 考生补注册批量导入弹出表
	 * @param
	 * @return
	 */
	@GetMapping("/importData")

	String importData( ){

		return "center/student/studentFileStudent/importData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/SFsavBathData")
	@RequiresPermissions("student:studentFile:add")
	public String MKsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/studentFileStudent/importData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/studentFileStudent/importData";
		}

		//批量导入
		String message = studentFileStudentService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/student/studentFileStudent/importData";
	}
}
