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

import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCourseHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCourseHisRegion")
public class StudentCourseHisRegionController extends SystemBaseController{
	@Autowired
	private StudentCourseHisRegionService studentCourseHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseHisRegion:studentCourseHisRegion")
	String StudentCourseHisRegion(){
	    return "region/student/studentCourseHisRegion/studentCourseHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseHisRegion:studentCourseHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseHisDO> studentCourseHisRegionList = studentCourseHisRegionService.list(query);
        for (StudentCourseHisDO item : studentCourseHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseHisRegion:add")
	String add(Model model){
	    return "region/student/studentCourseHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseHisDO studentCourseHisRegion = studentCourseHisRegionService.get(id);
		model.addAttribute("studentCourseHisRegion", studentCourseHisRegion);
	    return "region/student/studentCourseHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseHisRegion:add")
	public R save( StudentCourseHisDO studentCourseHisRegion){
        studentCourseHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseHisRegionService.save(studentCourseHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseHisRegion:edit")
	public R update( StudentCourseHisDO studentCourseHisRegion){
	    studentCourseHisRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCourseHisRegionService.update(studentCourseHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseHisRegion:remove")
	public R remove( Long id){
		if(studentCourseHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
