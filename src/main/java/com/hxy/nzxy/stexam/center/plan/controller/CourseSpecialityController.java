package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseSpecialityDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 须加考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseSpeciality")
public class CourseSpecialityController extends SystemBaseController{
	@Autowired
	private CourseSpecialityService courseSpecialityService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseSpeciality:courseSpeciality")
	String CourseSpeciality(){
	    return "center/plan/courseSpeciality/courseSpeciality";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseSpeciality:courseSpeciality")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseSpecialityDO> courseSpecialityList = courseSpecialityService.list(query);
        for (CourseSpecialityDO item : courseSpecialityList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseSpecialityService.count(query);
		PageUtils pageUtils = new PageUtils(courseSpecialityList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseSpeciality:add")
	String add(Model model){
	    return "center/plan/courseSpeciality/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseSpeciality:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseSpecialityDO courseSpeciality = courseSpecialityService.get(id);
		model.addAttribute("courseSpeciality", courseSpeciality);
	    return "center/plan/courseSpeciality/edit";
	}
	
	/**
	 * 保存
	 */
		@ResponseBody
		@PostMapping("/save")
		@RequiresPermissions("plan:courseSpeciality:add")
		public R save( CourseSpecialityDO courseSpeciality){
			courseSpeciality.setOperator(ShiroUtils.getUserId().toString());
			if(courseSpecialityService.selectIn(courseSpeciality)>0){
				return R.error("该课程已添加！");
			}
			if(courseSpecialityService.save(courseSpeciality)>0){
				return R.ok();
			}
			return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseSpeciality:edit")
	public R update( CourseSpecialityDO courseSpeciality){
	    courseSpeciality.setOperator(ShiroUtils.getUserId().toString());
		courseSpecialityService.update(courseSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseSpeciality:remove")
	public R remove( Long id){
		if(courseSpecialityService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseSpeciality:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseSpecialityService.batchRemove(ids);
		return R.ok();
	}
	
}
