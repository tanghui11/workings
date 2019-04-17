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

import com.hxy.nzxy.stexam.domain.ScoreHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/scoreHisStudent")
public class ScoreHisStudentController extends SystemBaseController{
	@Autowired
	private ScoreHisStudentService scoreHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreHisStudent:scoreHisStudent")
	String ScoreHisStudent(){
	    return "school/student/scoreHisStudent/scoreHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreHisStudent:scoreHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreHisDO> scoreHisStudentList = scoreHisStudentService.list(query);
        for (ScoreHisDO item : scoreHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreHisStudent:add")
	String add(Model model){
	    return "school/student/scoreHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreHisDO scoreHisStudent = scoreHisStudentService.get(id);
		model.addAttribute("scoreHisStudent", scoreHisStudent);
	    return "school/student/scoreHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreHisStudent:add")
	public R save( ScoreHisDO scoreHisStudent){
        scoreHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreHisStudentService.save(scoreHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreHisStudent:edit")
	public R update( ScoreHisDO scoreHisStudent){
	    scoreHisStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreHisStudentService.update(scoreHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreHisStudent:remove")
	public R remove( Long id){
		if(scoreHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
