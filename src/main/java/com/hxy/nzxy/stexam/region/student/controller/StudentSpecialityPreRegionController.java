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

import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityPreRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:15
 */
 
@Controller
@RequestMapping("/student/studentSpecialityPreRegion")
public class StudentSpecialityPreRegionController extends SystemBaseController{
	@Autowired
	private StudentSpecialityPreRegionService studentSpecialityPreRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityPreRegion:studentSpecialityPreRegion")
	String StudentSpecialityPreRegion(){
	    return "region/student/studentSpecialityPreRegion/studentSpecialityPreRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityPreRegion:studentSpecialityPreRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityPreDO> studentSpecialityPreRegionList = studentSpecialityPreRegionService.list(query);
        for (StudentSpecialityPreDO item : studentSpecialityPreRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityPreRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityPreRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityPreRegion:add")
	String add(Model model){
	    return "region/student/studentSpecialityPreRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityPreRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityPreDO studentSpecialityPreRegion = studentSpecialityPreRegionService.get(id);
		model.addAttribute("studentSpecialityPreRegion", studentSpecialityPreRegion);
	    return "region/student/studentSpecialityPreRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityPreRegion:add")
	public R save( StudentSpecialityPreDO studentSpecialityPreRegion){
        studentSpecialityPreRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityPreRegionService.save(studentSpecialityPreRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityPreRegion:edit")
	public R update( StudentSpecialityPreDO studentSpecialityPreRegion){
	    studentSpecialityPreRegion.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityPreRegionService.update(studentSpecialityPreRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPreRegion:remove")
	public R remove( Long id){
		if(studentSpecialityPreRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPreRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityPreRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
