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

import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.region.student.service.StudentGradeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentGradeRegion")
public class StudentGradeRegionController extends SystemBaseController{
	@Autowired
	private StudentGradeRegionService studentGradeRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentGradeRegion:studentGradeRegion")
	String StudentGradeRegion(){
	    return "region/student/studentGradeRegion/studentGradeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentGradeRegion:studentGradeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentGradeDO> studentGradeRegionList = studentGradeRegionService.list(query);
        for (StudentGradeDO item : studentGradeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentGradeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentGradeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentGradeRegion:add")
	String add(Model model){
	    return "region/student/studentGradeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentGradeRegion:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentGradeDO studentGradeRegion = studentGradeRegionService.get(id);
		model.addAttribute("studentGradeRegion", studentGradeRegion);
	    return "region/student/studentGradeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentGradeRegion:add")
	public R save( StudentGradeDO studentGradeRegion){
        studentGradeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentGradeRegionService.save(studentGradeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentGradeRegion:edit")
	public R update( StudentGradeDO studentGradeRegion){
	    studentGradeRegion.setOperator(ShiroUtils.getUserId().toString());
		studentGradeRegionService.update(studentGradeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentGradeRegion:remove")
	public R remove( String id){
		if(studentGradeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentGradeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentGradeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
