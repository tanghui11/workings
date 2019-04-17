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

import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreReplaceStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreReplaceStudent")
public class ScoreReplaceStudentController extends SystemBaseController{
	@Autowired
	private ScoreReplaceStudentService scoreReplaceStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplaceStudent:scoreReplaceStudent")
	String ScoreReplaceStudent(){
	    return "school/student/scoreReplaceStudent/scoreReplaceStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplaceStudent:scoreReplaceStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceDO> scoreReplaceStudentList = scoreReplaceStudentService.list(query);
        for (ScoreReplaceDO item : scoreReplaceStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplaceStudent:add")
	String add(Model model){
	    return "school/student/scoreReplaceStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplaceStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceDO scoreReplaceStudent = scoreReplaceStudentService.get(id);
		model.addAttribute("scoreReplaceStudent", scoreReplaceStudent);
	    return "school/student/scoreReplaceStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplaceStudent:add")
	public R save( ScoreReplaceDO scoreReplaceStudent){
        scoreReplaceStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceStudentService.save(scoreReplaceStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplaceStudent:edit")
	public R update( ScoreReplaceDO scoreReplaceStudent){
	    scoreReplaceStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceStudentService.update(scoreReplaceStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceStudent:remove")
	public R remove( Long id){
		if(scoreReplaceStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
