package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreSchoolStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 校考成绩
 * 
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreSchoolStudent")
public class ScoreSchoolStudentController extends SystemBaseController{
	@Autowired
	private ScoreSchoolStudentService scoreSchoolStudentService;

	@GetMapping()
	@RequiresPermissions("student:scoreSchoolStudent:scoreSchoolStudent")
	String ScoreSchoolStudent(){
	    return "school/student/scoreSchoolStudent/scoreSchoolStudent";
	}

	@GetMapping("/schoolCourseList")
	@RequiresPermissions("student:scoreSchoolStudent:scoreSchoolStudent")
	String schoolCourse(){
		return "school/student/scoreSchoolStudent/schoolCourse";
	}

	@GetMapping("/studentScoreList")
	@RequiresPermissions("student:scoreSchoolStudent:scoreSchoolStudent")
	String studentScore(){
		return "school/student/scoreSchoolStudent/studentScore";
	}

	//考生基本信息
	//准考证号、姓名
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchoolStudent:scoreSchoolStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolDO> scoreSchoolStudentList = scoreSchoolStudentService.list(query);
        for (ScoreSchoolDO item : scoreSchoolStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolStudentList, total);
		return pageUtils;
	}

	//校考课程
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolStudent:scoreSchoolStudent")
	public PageUtils schoolCourseList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScoreSchoolDO> schoolCourseList = scoreSchoolStudentService.schoolCourseList(query);
		for (ScoreSchoolDO item : schoolCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = scoreSchoolStudentService.schoolCourseListcount(query);
		PageUtils pageUtils = new PageUtils(schoolCourseList, total);
		return pageUtils;
	}

	//考生成绩
	@ResponseBody
	@RequiresPermissions("student:scoreSchool:scoreSchool")
	public PageUtils studentScoreList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScoreSchoolDO> studentScoreList = scoreSchoolStudentService.studentScoreList(query);
		for (ScoreSchoolDO item : studentScoreList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = scoreSchoolStudentService.studentScoreListcount(query);
		PageUtils pageUtils = new PageUtils(studentScoreList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchoolStudent:add")
	String add(Model model){
	    return "school/student/scoreSchoolStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchoolStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolDO scoreSchoolStudent = scoreSchoolStudentService.get(id);
		model.addAttribute("scoreSchoolStudent", scoreSchoolStudent);
	    return "school/student/scoreSchoolStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchoolStudent:add")
	public R save( ScoreSchoolDO scoreSchoolStudent){
        scoreSchoolStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolStudentService.save(scoreSchoolStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchoolStudent:edit")
	public R update( ScoreSchoolDO scoreSchoolStudent){
	    scoreSchoolStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolStudentService.update(scoreSchoolStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolStudent:remove")
	public R remove( Long id){
		if(scoreSchoolStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolStudentService.batchRemove(ids);
		return R.ok();
	}
	
}