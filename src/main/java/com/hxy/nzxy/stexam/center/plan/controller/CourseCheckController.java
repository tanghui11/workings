package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseCheckService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseCheckDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程复选
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseCheck")
public class CourseCheckController extends SystemBaseController{
	@Autowired
	private CourseCheckService courseCheckService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseCheck:courseCheck")
	String CourseCheck(){
	    return "center/plan/courseCheck/courseCheck";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseCheck:courseCheck")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseCheckDO> courseCheckList = courseCheckService.list(query);
        for (CourseCheckDO item : courseCheckList) {
        	item.setType(FieldDictUtil.get(getAppName(), "pla_course_check", "type", item.getType()));
			item.setSpecialityRecordName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", item.getSpecialityRecordid()+""));

			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseCheckService.count(query);
		PageUtils pageUtils = new PageUtils(courseCheckList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseCheck:add")
	String add(Model model){
		model.addAttribute("pla_course_check_type", commonService.listFieldDict(getAppName(), "pla_course_check", "type"));
		model.addAttribute("pla_course_classify", commonService.listFieldDict(getAppName(), "pla_course", "classify"));
		model.addAttribute("pla_course_attribute", commonService.listFieldDict(getAppName(), "pla_course", "attribute"));
	    return "center/plan/courseCheck/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseCheck:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseCheckDO courseCheck = courseCheckService.get(id);
		courseCheck.setSpecialityRecordName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", courseCheck.getSpecialityRecordid()+""));
		model.addAttribute("courseCheck", courseCheck);
		model.addAttribute("pla_course_check_type", commonService.listFieldDict(getAppName(), "pla_course_check", "type"));
		model.addAttribute("pla_course_classify", commonService.listFieldDict(getAppName(), "pla_course", "classify"));
		model.addAttribute("pla_course_attribute", commonService.listFieldDict(getAppName(), "pla_course", "attribute"));

	    return "center/plan/courseCheck/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseCheck:add")
	public R save( CourseCheckDO courseCheck){
        courseCheck.setOperator(ShiroUtils.getUserId().toString());
		if(courseCheckService.save(courseCheck)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseCheck:edit")
	public R update( CourseCheckDO courseCheck){
	    courseCheck.setOperator(ShiroUtils.getUserId().toString());
		courseCheckService.update(courseCheck);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseCheck:remove")
	public R remove( Long id){
		if(courseCheckService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseCheck:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseCheckService.batchRemove(ids);
		return R.ok();
	}
	
}
