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

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCardPoolRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 准考证打印池
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCardPoolRegion")
public class StudentCardPoolRegionController extends SystemBaseController{
	@Autowired
	private StudentCardPoolRegionService studentCardPoolRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCardPoolRegion:studentCardPoolRegion")
	String StudentCardPoolRegion(){
	    return "region/student/studentCardPoolRegion/studentCardPoolRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCardPoolRegion:studentCardPoolRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCardPoolDO> studentCardPoolRegionList = studentCardPoolRegionService.list(query);
        for (StudentCardPoolDO item : studentCardPoolRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCardPoolRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCardPoolRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCardPoolRegion:add")
	String add(Model model){
	    return "region/student/studentCardPoolRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCardPoolRegion:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentCardPoolDO studentCardPoolRegion = studentCardPoolRegionService.get(id);
		model.addAttribute("studentCardPoolRegion", studentCardPoolRegion);
	    return "region/student/studentCardPoolRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCardPoolRegion:add")
	public R save( StudentCardPoolDO studentCardPoolRegion){
        studentCardPoolRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentCardPoolRegionService.save(studentCardPoolRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCardPoolRegion:edit")
	public R update( StudentCardPoolDO studentCardPoolRegion){
	    studentCardPoolRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCardPoolRegionService.update(studentCardPoolRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPoolRegion:remove")
	public R remove( String id){
		if(studentCardPoolRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPoolRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentCardPoolRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
