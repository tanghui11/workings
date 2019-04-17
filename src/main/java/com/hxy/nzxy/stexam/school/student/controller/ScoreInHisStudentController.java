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

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreInHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreInHisStudent")
public class ScoreInHisStudentController extends SystemBaseController{
	@Autowired
	private ScoreInHisStudentService scoreInHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreInHisStudent:scoreInHisStudent")
	String ScoreInHisStudent(){
	    return "school/student/scoreInHisStudent/scoreInHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreInHisStudent:scoreInHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInHisDO> scoreInHisStudentList = scoreInHisStudentService.list(query);
        for (ScoreInHisDO item : scoreInHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreInHisStudent:add")
	String add(Model model){
	    return "school/student/scoreInHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreInHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInHisDO scoreInHisStudent = scoreInHisStudentService.get(id);
		model.addAttribute("scoreInHisStudent", scoreInHisStudent);
	    return "school/student/scoreInHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreInHisStudent:add")
	public R save( ScoreInHisDO scoreInHisStudent){
        scoreInHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInHisStudentService.save(scoreInHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreInHisStudent:edit")
	public R update( ScoreInHisDO scoreInHisStudent){
	    scoreInHisStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreInHisStudentService.update(scoreInHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHisStudent:remove")
	public R remove( Long id){
		if(scoreInHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreInHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
