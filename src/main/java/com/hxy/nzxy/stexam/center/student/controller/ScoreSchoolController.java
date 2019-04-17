package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.ScoreSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
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
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/scoreSchool")
public class ScoreSchoolController extends SystemBaseController{
	@Autowired
	private ScoreSchoolService scoreSchoolService;
	@GetMapping()
	@RequiresPermissions("student:scoreSchool:scoreSchool")
	String ScoreSchool(){
	    return "center/student/scoreSchool/scoreSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchool:scoreSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){


		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolDO> scoreSchoolList = scoreSchoolService.list(query);
        for (ScoreSchoolDO item : scoreSchoolList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_score_school", "status", item.getStatus()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchool:add")
	String add(Model model){
	    return "center/student/scoreSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolDO scoreSchool = scoreSchoolService.get(id);
		model.addAttribute("scoreSchool", scoreSchool);
	    return "center/student/scoreSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchool:add")
	public R save( ScoreSchoolDO scoreSchool){
        scoreSchool.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolService.save(scoreSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchool:edit")
	public R update( ScoreSchoolDO scoreSchool){
	    scoreSchool.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolService.update(scoreSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchool:remove")
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
	@RequiresPermissions("student:scoreSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolService.batchRemove(ids);
		return R.ok();
	}
	
}