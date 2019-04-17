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

import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.region.student.service.StudentRegRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentRegRegion")
public class StudentRegRegionController extends SystemBaseController{
	@Autowired
	private StudentRegRegionService studentRegRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentRegRegion:studentRegRegion")
	String StudentRegRegion(){
	    return "region/student/studentRegRegion/studentRegRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentRegRegion:studentRegRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegDO> studentRegRegionList = studentRegRegionService.list(query);
        for (StudentRegDO item : studentRegRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentRegRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentRegRegion:add")
	String add(Model model){
	    return "region/student/studentRegRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentRegRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegDO studentRegRegion = studentRegRegionService.get(id);
		model.addAttribute("studentRegRegion", studentRegRegion);
	    return "region/student/studentRegRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentRegRegion:add")
	public R save( StudentRegDO studentRegRegion){
        studentRegRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegRegionService.save(studentRegRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentRegRegion:edit")
	public R update( StudentRegDO studentRegRegion){
	    studentRegRegion.setOperator(ShiroUtils.getUserId().toString());
		studentRegRegionService.update(studentRegRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentRegRegion:remove")
	public R remove( Long id){
		if(studentRegRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentRegRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentRegRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
