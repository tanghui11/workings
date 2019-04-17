package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ScoreInDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreInStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/scoreInStudent")
public class ScoreInStudentController extends SystemBaseController{
	@Autowired
	private ScoreInStudentService scoreInStudentService;
	@Autowired
	private StudentSpecialityService studentSpecialityService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreInStudent:scoreInStudent")
	String ScoreInStudent(){
	    return "school/student/scoreInStudent/scoreInStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreInStudent:scoreInStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInDO> scoreInStudentList = scoreInStudentService.list(query);
        for (ScoreInDO item : scoreInStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInStudentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:scoreInStudent:add")
	String add(Model model){
	    return "school/student/scoreInStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreInStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInDO scoreInStudent = scoreInStudentService.get(id);
		model.addAttribute("scoreInStudent", scoreInStudent);
	    return "school/student/scoreInStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreInStudent:add")
	public R save( ScoreInDO scoreInStudent){
        scoreInStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInStudentService.save(scoreInStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreInStudent:edit")
	public R update( ScoreInDO scoreInStudent){
	    scoreInStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreInStudentService.update(scoreInStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreInStudent:remove")
	public R remove( Long id){
		if(scoreInStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreInStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreInStudentService.batchRemove(ids);
		return R.ok();
	}
	
}