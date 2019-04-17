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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentSpecialityRegion")
public class StudentSpecialityRegionController extends SystemBaseController{
	@Autowired
	private StudentSpecialityRegionService studentSpecialityRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityRegion:studentSpecialityRegion")
	String StudentSpecialityRegion(){
	    return "region/student/studentSpecialityRegion/studentSpecialityRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityRegion:studentSpecialityRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityDO> studentSpecialityRegionList = studentSpecialityRegionService.list(query);
        for (StudentSpecialityDO item : studentSpecialityRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityRegion:add")
	String add(Model model){
	    return "region/student/studentSpecialityRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityDO studentSpecialityRegion = studentSpecialityRegionService.get(id);
		model.addAttribute("studentSpecialityRegion", studentSpecialityRegion);
	    return "region/student/studentSpecialityRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityRegion:add")
	public R save( StudentSpecialityDO studentSpecialityRegion){
        studentSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityRegionService.save(studentSpecialityRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityRegion:edit")
	public R update( StudentSpecialityDO studentSpecialityRegion){
	    studentSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityRegionService.update(studentSpecialityRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityRegion:remove")
	public R remove( Long id){
		if(studentSpecialityRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
