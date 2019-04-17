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

import com.hxy.nzxy.stexam.domain.ScoreOldDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreOldStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老课程成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreOldStudent")
public class ScoreOldStudentController extends SystemBaseController{
	@Autowired
	private ScoreOldStudentService scoreOldStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreOldStudent:scoreOldStudent")
	String ScoreOldStudent(){
	    return "school/student/scoreOldStudent/scoreOldStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreOldStudent:scoreOldStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreOldDO> scoreOldStudentList = scoreOldStudentService.list(query);
        for (ScoreOldDO item : scoreOldStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreOldStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreOldStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreOldStudent:add")
	String add(Model model){
	    return "school/student/scoreOldStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreOldStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreOldDO scoreOldStudent = scoreOldStudentService.get(id);
		model.addAttribute("scoreOldStudent", scoreOldStudent);
	    return "school/student/scoreOldStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreOldStudent:add")
	public R save( ScoreOldDO scoreOldStudent){
        scoreOldStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreOldStudentService.save(scoreOldStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreOldStudent:edit")
	public R update( ScoreOldDO scoreOldStudent){
	    scoreOldStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreOldStudentService.update(scoreOldStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldStudent:remove")
	public R remove( Long id){
		if(scoreOldStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreOldStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
