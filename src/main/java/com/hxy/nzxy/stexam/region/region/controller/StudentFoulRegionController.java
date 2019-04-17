package com.hxy.nzxy.stexam.region.region.controller;

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

import com.hxy.nzxy.stexam.domain.StudentFoulDO;
import com.hxy.nzxy.stexam.region.region.service.StudentFoulRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试违规
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/studentFoulRegion")
public class StudentFoulRegionController extends SystemBaseController{
	@Autowired
	private StudentFoulRegionService studentFoulRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:studentFoulRegion:studentFoulRegion")
	String StudentFoulRegion(){
	    return "region/region/studentFoulRegion/studentFoulRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:studentFoulRegion:studentFoulRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentFoulDO> studentFoulRegionList = studentFoulRegionService.list(query);
        for (StudentFoulDO item : studentFoulRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentFoulRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentFoulRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:studentFoulRegion:add")
	String add(Model model){
	    return "region/region/studentFoulRegion/add";
	}

	@GetMapping("/edit/{studentCourseid}")
	@RequiresPermissions("region:studentFoulRegion:edit")
	String edit(@PathVariable("studentCourseid") Long studentCourseid,Model model){
		StudentFoulDO studentFoulRegion = studentFoulRegionService.get(studentCourseid);
		model.addAttribute("studentFoulRegion", studentFoulRegion);
	    return "region/region/studentFoulRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:studentFoulRegion:add")
	public R save( StudentFoulDO studentFoulRegion){
        studentFoulRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentFoulRegionService.save(studentFoulRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:studentFoulRegion:edit")
	public R update( StudentFoulDO studentFoulRegion){
	    studentFoulRegion.setOperator(ShiroUtils.getUserId().toString());
		studentFoulRegionService.update(studentFoulRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:studentFoulRegion:remove")
	public R remove( Long studentCourseid){
		if(studentFoulRegionService.remove(studentCourseid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:studentFoulRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] studentCourseids){
		studentFoulRegionService.batchRemove(studentCourseids);
		return R.ok();
	}
	
}
