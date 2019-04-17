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

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCourseTempRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCourseTempRegion")
public class StudentCourseTempRegionController extends SystemBaseController{
	@Autowired
	private StudentCourseTempRegionService studentCourseTempRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseTempRegion:studentCourseTempRegion")
	String StudentCourseTempRegion(){
	    return "region/student/studentCourseTempRegion/studentCourseTempRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseTempRegion:studentCourseTempRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseTempDO> studentCourseTempRegionList = studentCourseTempRegionService.list(query);
        for (StudentCourseTempDO item : studentCourseTempRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseTempRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseTempRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseTempRegion:add")
	String add(Model model){
	    return "region/student/studentCourseTempRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseTempRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseTempDO studentCourseTempRegion = studentCourseTempRegionService.get(id);
		model.addAttribute("studentCourseTempRegion", studentCourseTempRegion);
	    return "region/student/studentCourseTempRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseTempRegion:add")
	public R save( StudentCourseTempDO studentCourseTempRegion){
        studentCourseTempRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseTempRegionService.save(studentCourseTempRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseTempRegion:edit")
	public R update( StudentCourseTempDO studentCourseTempRegion){
	    studentCourseTempRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCourseTempRegionService.update(studentCourseTempRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTempRegion:remove")
	public R remove( Long id){
		if(studentCourseTempRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTempRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseTempRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
