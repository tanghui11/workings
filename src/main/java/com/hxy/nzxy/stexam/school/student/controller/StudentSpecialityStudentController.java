package com.hxy.nzxy.stexam.school.student.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
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

import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentSpecialityStudent")
public class StudentSpecialityStudentController extends SystemBaseController{
	@Autowired
	private StudentSpecialityStudentService studentSpecialityStudentService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private ScoreService scoreService;
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityStudent:studentSpecialityStudent")
	String StudentSpecialityStudent(@RequestParam String studentid,Model model){
		model.addAttribute("studentid", studentid);

		return "school/student/studentSpecialityStudent/studentSpecialityStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityStudent:studentSpecialityStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityDO> studentSpecialityStudentList = studentSpecialityStudentService.list(query);
        for (StudentSpecialityDO item : studentSpecialityStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityStudent:add")
	String add(Model model){
	    return "school/student/studentSpecialityStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(id);

		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));

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
	    return "school/student/studentSpecialityStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityStudent:add")
	public R save( StudentSpecialityDO studentSpecialityStudent){

        studentSpecialityStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityStudentService.save(studentSpecialityStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityStudent:edit")
	public R update( StudentSpecialityDO studentSpecialityStudent){
	    studentSpecialityStudent.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityStudentService.update(studentSpecialityStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityStudent:remove")
	public R remove( Long id){
		if(studentSpecialityStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityStudentService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/studentApply")
	@RequiresPermissions("student:studentSpecialityStudent:studentSpecialityStudent")
	String studentApply(){

		return "school/student/schoolStudentApply/applyExcel";
	}

	@GetMapping("/applyExcel")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityStudent:studentSpecialityStudent")
	public List<ScoreDO> applyExcel(@RequestParam Map<String,Object> map){
		map.put("offset",0);
		map.put("limit",20);
		Query query = new Query(map);
		String studentid = map.get("studentid").toString();
		String specialityRecordid = map.get("specialityRecordid").toString();
		List<ScoreDO> scoreStudent = studentSpecialityStudentService.applyExcelScore(studentid,specialityRecordid);
		for(ScoreDO item : scoreStudent){
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
			if(!"".equals(item.getSourceCourseid()) && item.getSourceCourseid() != null){
				item.setSourceCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
				item.setGrade(scoreService.list(query).get(0).getGrade());
			}
		}
		return scoreStudent;
	}
}
