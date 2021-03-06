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

import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.region.student.service.StudentLogRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentLogRegion")
public class StudentLogRegionController extends SystemBaseController{
	@Autowired
	private StudentLogRegionService studentLogRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentLogRegion:studentLogRegion")
	String StudentLogRegion(){
	    return "region/student/studentLogRegion/studentLogRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentLogRegion:studentLogRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentLogDO> studentLogRegionList = studentLogRegionService.list(query);
        for (StudentLogDO item : studentLogRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentLogRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentLogRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentLogRegion:add")
	String add(Model model){
	    return "region/student/studentLogRegion/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:studentLogRegion:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		StudentLogDO studentLogRegion = studentLogRegionService.get(changeType);
		model.addAttribute("studentLogRegion", studentLogRegion);
	    return "region/student/studentLogRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentLogRegion:add")
	public R save( StudentLogDO studentLogRegion){
        studentLogRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentLogRegionService.save(studentLogRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentLogRegion:edit")
	public R update( StudentLogDO studentLogRegion){
	    studentLogRegion.setOperator(ShiroUtils.getUserId().toString());
		studentLogRegionService.update(studentLogRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentLogRegion:remove")
	public R remove( String changeType){
		if(studentLogRegionService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentLogRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		studentLogRegionService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
