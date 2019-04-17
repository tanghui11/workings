package com.hxy.nzxy.stexam.region.student.controller;

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

import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.region.student.service.StudentScoreInRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentScoreInRegion")
public class StudentScoreInRegionController extends SystemBaseController{
	@Autowired
	private StudentScoreInRegionService studentScoreInRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentScoreInMigration:studentScoreInMigration")
	String StudentScoreInRegion(){
	    return "region/region/studentInMigration/studentScoreInMigration";
	}
	@ResponseBody
	@GetMapping("/migrationList")
	@RequiresPermissions("student:studentScoreInMigration:studentScoreInMigration")
	public PageUtils migrationList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentScoreInDO> studentScoreInList = studentScoreInRegionService.migrationList(query);
		for (StudentScoreInDO item : studentScoreInList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentScoreInRegionService.migrationCount(query);
		PageUtils pageUtils = new PageUtils(studentScoreInList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentScoreInRegion:studentScoreInRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentScoreInDO> studentScoreInRegionList = studentScoreInRegionService.list(query);
        for (StudentScoreInDO item : studentScoreInRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentScoreInRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentScoreInRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentScoreInRegion:add")
	String add(Model model){
	    return "region/student/studentScoreInRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentScoreInRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentScoreInDO studentScoreInRegion = studentScoreInRegionService.get(id);
		model.addAttribute("studentScoreInRegion", studentScoreInRegion);
	    return "region/student/studentScoreInRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentScoreInRegion:add")
	public R save( StudentScoreInDO studentScoreInRegion){
        studentScoreInRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentScoreInRegionService.save(studentScoreInRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentScoreInRegion:edit")
	public R update( StudentScoreInDO studentScoreInRegion){
	    studentScoreInRegion.setOperator(ShiroUtils.getUserId().toString());
		studentScoreInRegionService.update(studentScoreInRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreInRegion:remove")
	public R remove( Long id){
		if(studentScoreInRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreInRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentScoreInRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
