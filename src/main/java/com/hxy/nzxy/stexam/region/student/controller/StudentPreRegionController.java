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

import com.hxy.nzxy.stexam.domain.StudentPreDO;
import com.hxy.nzxy.stexam.region.student.service.StudentPreRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentPreRegion")
public class StudentPreRegionController extends SystemBaseController{
	@Autowired
	private StudentPreRegionService studentPreRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentPreRegion:studentPreRegion")
	String StudentPreRegion(){
	    return "region/student/studentPreRegion/studentPreRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentPreRegion:studentPreRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentPreDO> studentPreRegionList = studentPreRegionService.list(query);
        for (StudentPreDO item : studentPreRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentPreRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentPreRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentPreRegion:add")
	String add(Model model){
	    return "region/student/studentPreRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentPreRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentPreDO studentPreRegion = studentPreRegionService.get(id);
		model.addAttribute("studentPreRegion", studentPreRegion);
	    return "region/student/studentPreRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentPreRegion:add")
	public R save( StudentPreDO studentPreRegion){
        studentPreRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentPreRegionService.save(studentPreRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentPreRegion:edit")
	public R update( StudentPreDO studentPreRegion){
	    studentPreRegion.setOperator(ShiroUtils.getUserId().toString());
		studentPreRegionService.update(studentPreRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentPreRegion:remove")
	public R remove( Long id){
		if(studentPreRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentPreRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentPreRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
