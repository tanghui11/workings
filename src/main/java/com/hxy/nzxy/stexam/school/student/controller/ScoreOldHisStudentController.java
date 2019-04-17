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

import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreOldHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老课程成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreOldHisStudent")
public class ScoreOldHisStudentController extends SystemBaseController{
	@Autowired
	private ScoreOldHisStudentService scoreOldHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreOldHisStudent:scoreOldHisStudent")
	String ScoreOldHisStudent(){
	    return "school/student/scoreOldHisStudent/scoreOldHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreOldHisStudent:scoreOldHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreOldHisDO> scoreOldHisStudentList = scoreOldHisStudentService.list(query);
        for (ScoreOldHisDO item : scoreOldHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreOldHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreOldHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreOldHisStudent:add")
	String add(Model model){
	    return "school/student/scoreOldHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreOldHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreOldHisDO scoreOldHisStudent = scoreOldHisStudentService.get(id);
		model.addAttribute("scoreOldHisStudent", scoreOldHisStudent);
	    return "school/student/scoreOldHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreOldHisStudent:add")
	public R save( ScoreOldHisDO scoreOldHisStudent){
        scoreOldHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreOldHisStudentService.save(scoreOldHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreOldHisStudent:edit")
	public R update( ScoreOldHisDO scoreOldHisStudent){
	    scoreOldHisStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreOldHisStudentService.update(scoreOldHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHisStudent:remove")
	public R remove( Long id){
		if(scoreOldHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreOldHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
