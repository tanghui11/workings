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

import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreImportStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 成绩导入临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/scoreImportStudent")
public class ScoreImportStudentController extends SystemBaseController{
	@Autowired
	private ScoreImportStudentService scoreImportStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreImportStudent:scoreImportStudent")
	String ScoreImportStudent(){
	    return "school/student/scoreImportStudent/scoreImportStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreImportStudent:scoreImportStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreImportDO> scoreImportStudentList = scoreImportStudentService.list(query);
        for (ScoreImportDO item : scoreImportStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreImportStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreImportStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreImportStudent:add")
	String add(Model model){
	    return "school/student/scoreImportStudent/add";
	}

	@GetMapping("/edit/{kemuid}")
	@RequiresPermissions("student:scoreImportStudent:edit")
	String edit(@PathVariable("kemuid") String kemuid,Model model){
		ScoreImportDO scoreImportStudent = scoreImportStudentService.get(kemuid);
		model.addAttribute("scoreImportStudent", scoreImportStudent);
	    return "school/student/scoreImportStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreImportStudent:add")
	public R save( ScoreImportDO scoreImportStudent){
        scoreImportStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreImportStudentService.save(scoreImportStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreImportStudent:edit")
	public R update( ScoreImportDO scoreImportStudent){
	    scoreImportStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreImportStudentService.update(scoreImportStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreImportStudent:remove")
	public R remove( String kemuid){
		if(scoreImportStudentService.remove(kemuid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreImportStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] kemuids){
		scoreImportStudentService.batchRemove(kemuids);
		return R.ok();
	}
	
}
