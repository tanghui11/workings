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

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentSpecialityHisRegion")
public class StudentSpecialityHisRegionController extends SystemBaseController{
	@Autowired
	private StudentSpecialityHisRegionService studentSpecialityHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityHisRegion:studentSpecialityHisRegion")
	String StudentSpecialityHisRegion(){
	    return "region/student/studentSpecialityHisRegion/studentSpecialityHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityHisRegion:studentSpecialityHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityHisDO> studentSpecialityHisRegionList = studentSpecialityHisRegionService.list(query);
        for (StudentSpecialityHisDO item : studentSpecialityHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityHisRegion:add")
	String add(Model model){
	    return "region/student/studentSpecialityHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityHisDO studentSpecialityHisRegion = studentSpecialityHisRegionService.get(id);
		model.addAttribute("studentSpecialityHisRegion", studentSpecialityHisRegion);
	    return "region/student/studentSpecialityHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityHisRegion:add")
	public R save( StudentSpecialityHisDO studentSpecialityHisRegion){
        studentSpecialityHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityHisRegionService.save(studentSpecialityHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityHisRegion:edit")
	public R update( StudentSpecialityHisDO studentSpecialityHisRegion){
	    studentSpecialityHisRegion.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityHisRegionService.update(studentSpecialityHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHisRegion:remove")
	public R remove( Long id){
		if(studentSpecialityHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
