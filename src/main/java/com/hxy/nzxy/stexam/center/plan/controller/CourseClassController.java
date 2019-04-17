package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseClassService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseClassDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseClass")
public class CourseClassController extends SystemBaseController{
	@Autowired
	private CourseClassService courseClassService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseClass:courseClass")
	String CourseClass(){
	    return "center/plan/courseClass/courseClass";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseClass:courseClass")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseClassDO> courseClassList = courseClassService.list(query);
        for (CourseClassDO item : courseClassList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseClassService.count(query);
		PageUtils pageUtils = new PageUtils(courseClassList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseClass:add")
	String add(Model model){
	    return "center/plan/courseClass/add";
	}

	@GetMapping("/edit/{type}")
	@RequiresPermissions("plan:courseClass:edit")
	String edit(@PathVariable("type") String type,Model model){
		CourseClassDO courseClass = courseClassService.get(type);
		model.addAttribute("courseClass", courseClass);
	    return "center/plan/courseClass/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseClass:add")
	public R save( CourseClassDO courseClass){
        courseClass.setOperator(ShiroUtils.getUserId().toString());
		if(courseClassService.save(courseClass)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseClass:edit")
	public R update( CourseClassDO courseClass){
	    courseClass.setOperator(ShiroUtils.getUserId().toString());
		courseClassService.update(courseClass);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseClass:remove")
	public R remove( String type){
		if(courseClassService.remove(type)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseClass:batchRemove")
	public R remove(@RequestParam("ids[]") String[] types){
		courseClassService.batchRemove(types);
		return R.ok();
	}
	
}
