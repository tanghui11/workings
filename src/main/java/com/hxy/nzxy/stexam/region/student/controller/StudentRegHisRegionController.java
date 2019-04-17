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

import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentRegHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生注册_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentRegHisRegion")
public class StudentRegHisRegionController extends SystemBaseController{
	@Autowired
	private StudentRegHisRegionService studentRegHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentRegHisRegion:studentRegHisRegion")
	String StudentRegHisRegion(){
	    return "region/student/studentRegHisRegion/studentRegHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentRegHisRegion:studentRegHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegHisDO> studentRegHisRegionList = studentRegHisRegionService.list(query);
        for (StudentRegHisDO item : studentRegHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentRegHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentRegHisRegion:add")
	String add(Model model){
	    return "region/student/studentRegHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentRegHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegHisDO studentRegHisRegion = studentRegHisRegionService.get(id);
		model.addAttribute("studentRegHisRegion", studentRegHisRegion);
	    return "region/student/studentRegHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentRegHisRegion:add")
	public R save( StudentRegHisDO studentRegHisRegion){
        studentRegHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegHisRegionService.save(studentRegHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentRegHisRegion:edit")
	public R update( StudentRegHisDO studentRegHisRegion){
	    studentRegHisRegion.setOperator(ShiroUtils.getUserId().toString());
		studentRegHisRegionService.update(studentRegHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHisRegion:remove")
	public R remove( Long id){
		if(studentRegHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentRegHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
