package com.hxy.nzxy.stexam.school.student.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.school.student.service.StudentStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentStudent")
public class StudentStudentController extends SystemBaseController{
	@Autowired
	private StudentStudentService studentStudentService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private StudentSpecialityStudentService studentSpecialityStudentService;
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;

	@GetMapping()
	@RequiresPermissions("student:studentStudent:studentStudent")
	String StudentStudent(){
	    return "school/student/studentStudent/studentStudent";
	}
	@GetMapping("/showPhoto")
	@RequiresPermissions("student:studentStudent:studentStudent")
	String showPhoto(){
		return "school/student/studentStudent/showPhoto";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentStudent:studentStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentDO> studentStudentList = studentStudentService.list(query);
        for (StudentDO item : studentStudentList) {
			item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
			item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
			item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
			item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
			item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
			item.setSpecialityName(item.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentStudent:add")
	String add(Model model,String collegeid,String teachSiteid){
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
		model.addAttribute("collegeid", collegeid);
		model.addAttribute("teachSiteid", teachSiteid);
		return "school/student/studentStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentStudent:edit")
	String edit(@PathVariable("id") String id,Model model){

		//报考专业
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(Long.valueOf(id));
		StudentDO studentStudent = studentStudentService.get(studentSpecialityStudent.getStudentid());
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
		//招生备案
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegSchoolService.getSelect(studentSpecialityStudent.getSchoolSpecialityRegid());
		schoolSpecialityRegDO.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegDO.getStatus()));
		schoolSpecialityRegDO.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegDO.getAuditStatus()));
		schoolSpecialityRegDO.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegDO.getClassify()));
		schoolSpecialityRegDO.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegDO.getType()));
		schoolSpecialityRegDO.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegDO.getMethod()));
		schoolSpecialityRegDO.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegDO.getEducateLength()));
		schoolSpecialityRegDO.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegDO.getRegSeason()));
		studentSpecialityStudent.setSchoolSpecialityRegName(schoolSpecialityRegDO.getSpecialityId()+schoolSpecialityRegDO.getSpecialityName()+"["+schoolSpecialityRegDO.getRegYear()+" "+schoolSpecialityRegDO.getRegSeason()+"]"+"["+schoolSpecialityRegDO.getClassify()+" "+schoolSpecialityRegDO.getType()+" "+schoolSpecialityRegDO.getEducateLength()+"]");
		studentSpecialityStudent.setGradSpecialityName("("+studentSpecialityStudent.getGradSpecialityid()+")"+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpecialityStudent.getGradSpecialityid()));
		model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);

		return "school/student/studentStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentStudent:add")
	public R save( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
        studentStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentStudentService.save(studentStudent,studentSpeciality)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentStudent:edit")
	public R update( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(studentSpeciality.getId());
		Date now = new Date();
		long l = now.getTime() -DateUtils.stringToDate(studentSpecialityStudent.getCreateDate(), "").getTime();
		long day = l / (24 * 60 * 60 * 1000);
		if (day>30){
			return R.ok("学生已注册了"+day+"天，超考30天，不能进行修改！");
		}
		studentStudent.setOperator(ShiroUtils.getUserId().toString());

		studentStudentService.update(studentStudent,studentSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentStudent:remove")
	public R remove( String id){
		//报考专业
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(Long.valueOf(id));
		if(studentStudentService.remove(studentSpecialityStudent.getStudentid())>0&&studentSpecialityStudentService.remove(Long.valueOf(id))>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentStudentService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 显示弹出页面
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")

	String showSubject( ){

		return "school/student/studentStudent/showSubject";
	}

	@ResponseBody
	@GetMapping("/listshowSubject")
	public PageUtils listshowSubject (@RequestParam Map<String, Object> params){
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

	//学生信息导入页面
	@GetMapping("/importData")
	@RequiresPermissions("student:studentStudent:savBathData")
	String importData( ){
		return "school/student/studentStudent/importData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("student:studentStudent:savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session,String teachid,String collegeid
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "school/student/studentStudent/importData";
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
			return "school/student/studentStudent/importData";
		}

		//批量导入
		String message = studentStudentService.batchImport(fileName,file,teachid,collegeid);
		request.setAttribute("msg",message);
		return "school/student/studentStudent/importData";
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateNMD")
	@RequiresPermissions("student:studentStudent:edit")
	public R updateNMD( StudentDO studentStudent){
		studentStudent.setOperator(ShiroUtils.getUserId().toString());
		studentStudentService.updateNMD(studentStudent);
		return R.ok();
	}
}
