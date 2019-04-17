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

import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentHisRegion")
public class StudentHisRegionController extends SystemBaseController{
	@Autowired
	private StudentHisRegionService studentHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentHisRegion:studentHisRegion")
	String StudentHisRegion(){
	    return "region/student/studentHisRegion/studentHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentHisRegion:studentHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentHisDO> studentHisRegionList = studentHisRegionService.list(query);
        for (StudentHisDO item : studentHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentHisRegion:add")
	String add(Model model){
	    return "region/student/studentHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentHisRegion:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentHisDO studentHisRegion = studentHisRegionService.get(id);
		model.addAttribute("studentHisRegion", studentHisRegion);
	    return "region/student/studentHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentHisRegion:add")
	public R save( StudentHisDO studentHisRegion){
        studentHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentHisRegionService.save(studentHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentHisRegion:edit")
	public R update( StudentHisDO studentHisRegion){
	    studentHisRegion.setOperator(ShiroUtils.getUserId().toString());
		studentHisRegionService.update(studentHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentHisRegion:remove")
	public R remove( String id){
		if(studentHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
