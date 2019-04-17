package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.center.school.service.SchoolService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.student.service.StudentRegionService;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityRegionService;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.school.student.service.StudentFileStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生基本信息表学生信息管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentFileOption")
public class StudentFileOptionController extends SystemBaseController{
	@Autowired
	private StudentRegionService studentRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private StudentSpecialityRegionService studentSpecialityRegionService;
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;
	@Autowired
	private StudentStudentService studentStudentService;
	@Autowired
	private SpecialityRecordService specialityRecordService;
	@Autowired
	private SchoolService schoolService;
	@GetMapping()
	@RequiresPermissions("student:studentFileOption:studentStudent")
	String StudentStudent(Model model){
		model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
		model.addAttribute("stu_student_confirm_status", commonService.listFieldDict(getAppName(), "stu_student", "confirm_status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));

		return "center/student/studentFileOption/studentFileStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentFileOption:studentStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
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
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentFileOption:add")
	String add(Model model){
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));



		return "center/student/studentFileOption/add";
	}

    @GetMapping("/collegePage")
    @RequiresPermissions("student:studentFileOption:studentStudent")
    String collegePage(Model model){
        return "center/student/studentFileOption/college";
    }
	@GetMapping("/teachSitePage")
	@RequiresPermissions("student:studentFileOption:studentStudent")
	String teachSitePage(Model model){
		return "center/student/studentFileOption/teachSite";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentFileOption:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentSpecialityDO studentSpecialityStudent = studentRegionService.getByStudentId(id);
		StudentDO studentRegion = studentRegionService.get(studentSpecialityStudent.getStudentid());
		if(studentRegion.getType().equals("a")){
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



			studentSpecialityStudent.setGradSpecialityName(studentSpecialityStudent.getGradSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpecialityStudent.getGradSpecialityid()));
			studentSpecialityStudent.setSchoolSpecialityRegName(studentSpecialityStudent.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentSpecialityStudent.getSpecialityid()));

			//studentRegion.setSpecialityName(studentRegion.getSpecialityid() + " " +FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", studentRegion.getSpecialityid()));
			//studentRegion.setGradSpecialityName(studentRegion.getGradSpecialityid()+"  " +studentRegion.getGradSpecialityName());
			model.addAttribute("regionid",ShiroUtils.getUser().getWorkerid());
			model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);
			model.addAttribute("studentRegion", studentRegion);

			return "center/student/studentFileOption/edit";

		}else{
			//报考专业
			studentRegion.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", studentRegion.getGroupid()+""));
			model.addAttribute("studentRegion", studentRegion);
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
			SchoolDO schoolDO = schoolService.get(studentRegion.getSchoolid());
			studentSpecialityStudent.setSchoolName(schoolDO.getCode()+" "+schoolDO.getName());
			model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);
			return "center/student/studentFileOption/edit";
		}




	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentRegion:add")
	public R save(StudentDO studentRegion,StudentSpecialityDO studentSpeciality) {
		studentRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegion.getType().equals("a")){
			if (studentRegionService.save(studentRegion,studentSpeciality) > 0) {
				return R.ok();
			}
		}else{
			if (studentStudentService.save(studentRegion,studentSpeciality) > 0) {
				return R.ok();
			}
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
		/*StudentSpecialityDO studentSpecialityStudent = studentRegionService.getByStudentId(studentSpeciality.getStudentSpecialityid());
		Date now = new Date();
		long l = now.getTime() -DateUtils.stringToDate(studentSpecialityStudent.getCreateDate(), "").getTime();
		long day = l / (24 * 60 * 60 * 1000);
		if (day>30){
			return R.ok("学生已注册了"+day+"天，超考30天，不能进行修改！");
		}*/
		studentRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegion.getType().equals("a")){
			studentRegionService.update(studentRegion,studentSpeciality);
		}else{
			studentStudentService.update(studentRegion,studentSpeciality);
		}


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
	/**
	 * 显示弹出页面
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")

	String showSubject( ){

		return "center/student/studentStudent/showSubject";
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

	/**
	 * 学生报名选择专业考区
	 */
	@GetMapping("/SchoolSpecialityRegSchoolStudentCenter")
	String SchoolSpecialityRegSchoolStudentCenter(Model model){
		model.addAttribute("regionid", ShiroUtils.getUser().getWorkerid());
		return "region/student/studentRegion/schoolSpecialityRegSchoolStudentCenter";
	}
	/**
	 * 学生报名选择助学
	 */
	@GetMapping("/SchoolSpecialityRegSchoolStudentCenter2")
	String SchoolSpecialityRegSchoolStudentCenter2(Model model){

		Map map= new HashMap();
		map.put("status","a");
		map.put("type","b");
		//当前用户的开设专业传schoolid
		List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
		//查询对应的专业
		List<String> list=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
		List<SpecialityDO> specialityList=specialityService.listSpeciality(list);
		model.addAttribute("specialityList", specialityList);
		return "school/school/schoolSpecialityRegSchool/schoolSpecialityRegSchoolStudentCenter";
	}

}
