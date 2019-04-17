package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.center.student.service.ScoreSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import com.hxy.nzxy.stexam.school.student.domain.SchoolCourseScoreInputDO;
import com.hxy.nzxy.stexam.school.student.service.SchoolCourseScoreInputService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 校考课程成绩录入
 * 
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/schoolCourseScoreInput")
public class SchoolCourseScoreInputController extends SystemBaseController{
	@Autowired
	private SchoolCourseScoreInputService schoolCourseScoreInputService;
	@Autowired
	private ScoreSchoolService scoreSchoolService;

	@GetMapping("/schoolScore")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	String schoolScore(){
	    return "school/student/schoolCourseScoreInput/schoolScoreStudent";
	}

	@GetMapping("/courseInfo")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	String courseInfo(){
		return "school/student/schoolCourseScoreInput/specialityCourse";
	}

	@GetMapping("/studentInfo")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	String studentInfo(){
		return "school/student/schoolCourseScoreInput/studentFileStudent";
	}

	@GetMapping("/schoolScoreList")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	String schoolScoreList(){
		return "school/student/schoolCourseScoreInput/schoolScoreList";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolCourseScoreInputDO> schoolCourseScoreInputList = schoolCourseScoreInputService.list(query);
        for (SchoolCourseScoreInputDO item : schoolCourseScoreInputList) {
        	item.setStatus(FieldDictUtil.get(getAppName(), "stu_school_score", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolCourseScoreInputService.count(query);
		PageUtils pageUtils = new PageUtils(schoolCourseScoreInputList, total);
		return pageUtils;
	}
//校考课程成绩
	@ResponseBody
	@GetMapping("/listScoreSchool")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	public List listScoreSchool(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<ScoreSchoolDO> listScoreSchoolList = scoreSchoolService.list(params);
		for (ScoreSchoolDO item : listScoreSchoolList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_score_school", "status", item.getStatus()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
			return listScoreSchoolList;
	}



	@ResponseBody
	@GetMapping("/listSchoolCourse")
	@RequiresPermissions("student:schoolCourseScoreInput:schoolCourseScoreInput")
	public List listSchoolCourse(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<SpecialityCourseDO> schoolCourseScoreInputList = schoolCourseScoreInputService.listSchoolCourse(params);
		return schoolCourseScoreInputList;
	}
	@GetMapping("/add")
	@RequiresPermissions("student:schoolCourseScoreInput:add")
	String add(Model model){
	    return "school/student/schoolScoreStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:schoolCourseScoreInput:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolCourseScoreInputDO schoolCourseScoreInput = schoolCourseScoreInputService.get(id);
		model.addAttribute("schoolScoreStudent", schoolCourseScoreInput);
	    return "school/student/schoolScoreStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:schoolCourseScoreInput:add")
	public R save( ScoreSchoolDO scoreSchoolDO){
		scoreSchoolDO.setOperator(ShiroUtils.getUserId().toString());
		//已入库呀
		scoreSchoolDO.setStatus("b");
		if(scoreSchoolService.save(scoreSchoolDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:schoolCourseScoreInput:edit")
	public R update( SchoolCourseScoreInputDO schoolCourseScoreInput){
		schoolCourseScoreInput.setOperator(ShiroUtils.getUserId().toString());
		schoolCourseScoreInputService.update(schoolCourseScoreInput);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:schoolCourseScoreInput:remove")
	public R remove( Long id){
		if(scoreSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:schoolCourseScoreInput:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolCourseScoreInputService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 分数录入
	 * 参数需要包括
	 * 准考证号、studentid
	 * 课程代码、courseid
	 * 成绩、grade
	 * 专业代码	specialityid
	 * 主考院校编号	schoolid
	 * 主考学院编号	collegeid
	 * 专业方向	direction
	 */
	@PostMapping( "/scoreIn")
	@ResponseBody
	@RequiresPermissions("student:schoolCourseScoreInput:batchRemove")
	public R scoreIn(@RequestParam Map<String,String> map){
		String message = "";
		String operator = ShiroUtils.getUserId().toString();
		map.put("operator", operator);
//		if(schoolScoreStudentService.whetherAreadyIn(map) != 0){
//
//		}
		int i = schoolCourseScoreInputService.inPutGrade(map);
		if (i != 0){
			message = "分数录入成功";
		}

		return R.ok(message);
	}
}
