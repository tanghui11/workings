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

import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import com.hxy.nzxy.stexam.region.student.service.StudentChangeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生信息变更表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentChangeRegion")
public class StudentChangeRegionController extends SystemBaseController{
	@Autowired
	private StudentChangeRegionService studentChangeRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentChangeRegion:studentChangeRegion")
	String StudentChangeRegion(){
	    return "region/student/studentChangeRegion/studentChangeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentChangeRegion:studentChangeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentChangeDO> studentChangeRegionList = studentChangeRegionService.list(query);
        for (StudentChangeDO item : studentChangeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentChangeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentChangeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentChangeRegion:add")
	String add(Model model){
	    return "region/student/studentChangeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentChangeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentChangeDO studentChangeRegion = studentChangeRegionService.get(id);
		model.addAttribute("studentChangeRegion", studentChangeRegion);
	    return "region/student/studentChangeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentChangeRegion:add")
	public R save( StudentChangeDO studentChangeRegion){
        studentChangeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentChangeRegionService.save(studentChangeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentChangeRegion:edit")
	public R update( StudentChangeDO studentChangeRegion){
	    studentChangeRegion.setOperator(ShiroUtils.getUserId().toString());
		studentChangeRegionService.update(studentChangeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentChangeRegion:remove")
	public R remove( Long id){
		if(studentChangeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentChangeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentChangeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
