package com.hxy.nzxy.stexam.school.student.controller;

import java.util.List;
import java.util.Map;

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

import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreSchoolHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreSchoolHisStudent")
public class ScoreSchoolHisStudentController extends SystemBaseController{
	@Autowired
	private ScoreSchoolHisStudentService scoreSchoolHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreSchoolHisStudent:scoreSchoolHisStudent")
	String ScoreSchoolHisStudent(){
	    return "school/student/scoreSchoolHisStudent/scoreSchoolHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchoolHisStudent:scoreSchoolHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolHisDO> scoreSchoolHisStudentList = scoreSchoolHisStudentService.list(query);
        for (ScoreSchoolHisDO item : scoreSchoolHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchoolHisStudent:add")
	String add(Model model){
	    return "school/student/scoreSchoolHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchoolHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolHisDO scoreSchoolHisStudent = scoreSchoolHisStudentService.get(id);
		model.addAttribute("scoreSchoolHisStudent", scoreSchoolHisStudent);
	    return "school/student/scoreSchoolHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchoolHisStudent:add")
	public R save( ScoreSchoolHisDO scoreSchoolHisStudent){
        scoreSchoolHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolHisStudentService.save(scoreSchoolHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchoolHisStudent:edit")
	public R update( ScoreSchoolHisDO scoreSchoolHisStudent){
	    scoreSchoolHisStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolHisStudentService.update(scoreSchoolHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHisStudent:remove")
	public R remove( Long id){
		if(scoreSchoolHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
