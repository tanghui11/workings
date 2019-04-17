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

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreChangeLogStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 成绩变更日志表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/scoreChangeLogStudent")
public class ScoreChangeLogStudentController extends SystemBaseController{
	@Autowired
	private ScoreChangeLogStudentService scoreChangeLogStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreChangeLogStudent:scoreChangeLogStudent")
	String ScoreChangeLogStudent(){
	    return "school/student/scoreChangeLogStudent/scoreChangeLogStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreChangeLogStudent:scoreChangeLogStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreChangeLogDO> scoreChangeLogStudentList = scoreChangeLogStudentService.list(query);
        for (ScoreChangeLogDO item : scoreChangeLogStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreChangeLogStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreChangeLogStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreChangeLogStudent:add")
	String add(Model model){
	    return "school/student/scoreChangeLogStudent/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:scoreChangeLogStudent:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		ScoreChangeLogDO scoreChangeLogStudent = scoreChangeLogStudentService.get(changeType);
		model.addAttribute("scoreChangeLogStudent", scoreChangeLogStudent);
	    return "school/student/scoreChangeLogStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreChangeLogStudent:add")
	public R save( ScoreChangeLogDO scoreChangeLogStudent){
        scoreChangeLogStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreChangeLogStudentService.save(scoreChangeLogStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreChangeLogStudent:edit")
	public R update( ScoreChangeLogDO scoreChangeLogStudent){
	    scoreChangeLogStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreChangeLogStudentService.update(scoreChangeLogStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLogStudent:remove")
	public R remove( String changeType){
		if(scoreChangeLogStudentService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLogStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		scoreChangeLogStudentService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
