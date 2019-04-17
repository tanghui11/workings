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

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreReplaceHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreReplaceHisStudent")
public class ScoreReplaceHisStudentController extends SystemBaseController{
	@Autowired
	private ScoreReplaceHisStudentService scoreReplaceHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplaceHisStudent:scoreReplaceHisStudent")
	String ScoreReplaceHisStudent(){
	    return "school/student/scoreReplaceHisStudent/scoreReplaceHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplaceHisStudent:scoreReplaceHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceHisDO> scoreReplaceHisStudentList = scoreReplaceHisStudentService.list(query);
        for (ScoreReplaceHisDO item : scoreReplaceHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplaceHisStudent:add")
	String add(Model model){
	    return "school/student/scoreReplaceHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplaceHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceHisDO scoreReplaceHisStudent = scoreReplaceHisStudentService.get(id);
		model.addAttribute("scoreReplaceHisStudent", scoreReplaceHisStudent);
	    return "school/student/scoreReplaceHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplaceHisStudent:add")
	public R save( ScoreReplaceHisDO scoreReplaceHisStudent){
        scoreReplaceHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceHisStudentService.save(scoreReplaceHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplaceHisStudent:edit")
	public R update( ScoreReplaceHisDO scoreReplaceHisStudent){
	    scoreReplaceHisStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceHisStudentService.update(scoreReplaceHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHisStudent:remove")
	public R remove( Long id){
		if(scoreReplaceHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
