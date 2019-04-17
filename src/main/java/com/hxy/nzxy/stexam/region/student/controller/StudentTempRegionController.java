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

import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.region.student.service.StudentTempRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:15
 */
 
@Controller
@RequestMapping("/student/studentTempRegion")
public class StudentTempRegionController extends SystemBaseController{
	@Autowired
	private StudentTempRegionService studentTempRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentTempRegion:studentTempRegion")
	String StudentTempRegion(){
	    return "region/student/studentTempRegion/studentTempRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentTempRegion:studentTempRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentTempDO> studentTempRegionList = studentTempRegionService.list(query);
        for (StudentTempDO item : studentTempRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentTempRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentTempRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentTempRegion:add")
	String add(Model model){
	    return "region/student/studentTempRegion/add";
	}

	@GetMapping("/edit/{type}")
	@RequiresPermissions("student:studentTempRegion:edit")
	String edit(@PathVariable("type") String type,Model model){
		StudentTempDO studentTempRegion = studentTempRegionService.get(type);
		model.addAttribute("studentTempRegion", studentTempRegion);
	    return "region/student/studentTempRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentTempRegion:add")
	public R save( StudentTempDO studentTempRegion){
        studentTempRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentTempRegionService.save(studentTempRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentTempRegion:edit")
	public R update( StudentTempDO studentTempRegion){
	    studentTempRegion.setOperator(ShiroUtils.getUserId().toString());
		studentTempRegionService.update(studentTempRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentTempRegion:remove")
	public R remove( String type){
		if(studentTempRegionService.remove(type)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentTempRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] types){
		studentTempRegionService.batchRemove(types);
		return R.ok();
	}
	
}
