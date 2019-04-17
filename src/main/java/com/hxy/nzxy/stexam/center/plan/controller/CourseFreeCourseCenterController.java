package com.hxy.nzxy.stexam.center.plan.controller;

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

import com.hxy.nzxy.stexam.domain.CourseFreeCourseDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCourseCenterService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 课程免考规则课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
 
@Controller
@RequestMapping("/plan/courseFreeCourseCenter")
public class CourseFreeCourseCenterController extends SystemBaseController{
	@Autowired
	private CourseFreeCourseCenterService courseFreeCourseCenterService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseFreeCourseCenter:courseFreeCourseCenter")
	String CourseFreeCourseCenter(){
	    return "center/plan/courseFreeCourseCenter/courseFreeCourseCenter";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseFreeCourseCenter:courseFreeCourseCenter")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseFreeCourseDO> courseFreeCourseCenterList = courseFreeCourseCenterService.list(query);
        for (CourseFreeCourseDO item : courseFreeCourseCenterList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseFreeCourseCenterService.count(query);
		PageUtils pageUtils = new PageUtils(courseFreeCourseCenterList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseFreeCourseCenter:add")
	String add(Model model){
	    return "center/plan/courseFreeCourseCenter/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseFreeCourseCenter:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseFreeCourseDO courseFreeCourseCenter = courseFreeCourseCenterService.get(id);
		model.addAttribute("courseFreeCourseCenter", courseFreeCourseCenter);
	    return "center/plan/courseFreeCourseCenter/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseFreeCourseCenter:add")
	public R save( CourseFreeCourseDO courseFreeCourseCenter){
        courseFreeCourseCenter.setOperator(ShiroUtils.getUserId().toString());
		if(courseFreeCourseCenterService.save(courseFreeCourseCenter)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseFreeCourseCenter:edit")
	public R update( CourseFreeCourseDO courseFreeCourseCenter){
	    courseFreeCourseCenter.setOperator(ShiroUtils.getUserId().toString());
		courseFreeCourseCenterService.update(courseFreeCourseCenter);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCourseCenter:remove")
	public R remove( String id){
		if(courseFreeCourseCenterService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCourseCenter:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		courseFreeCourseCenterService.batchRemove(ids);
		return R.ok();
	}
	
}
