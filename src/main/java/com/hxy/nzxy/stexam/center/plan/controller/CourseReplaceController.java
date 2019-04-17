package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseReplaceService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseReplaceDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseReplace")
public class CourseReplaceController extends SystemBaseController{
	@Autowired
	private CourseReplaceService courseReplaceService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseReplace:courseReplace")
	String CourseReplace(){
	    return "center/plan/courseReplace/courseReplace";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseReplace:courseReplace")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseReplaceDO> courseReplaceList = courseReplaceService.list(query);
        for (CourseReplaceDO item : courseReplaceList) {
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_course_replace", "flag", item.getFlag()));
			item.setCourseClass(FieldDictUtil.get(getAppName(), "pla_course_replace", "course_class", item.getCourseClass()));
			item.setSpecialityRecordName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", item.getSpecialityRecordid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_replace", "type", item.getType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseReplaceService.count(query);
		PageUtils pageUtils = new PageUtils(courseReplaceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseReplace:add")
	String add(Model model){
		model.addAttribute("pla_course_replace_flag", commonService.listFieldDict(getAppName(), "pla_course_replace", "flag"));
		model.addAttribute("pla_course_replace_course_class", commonService.listFieldDict(getAppName(), "pla_course_replace", "course_class"));
		model.addAttribute("pla_course_replace_type", commonService.listFieldDict(getAppName(), "pla_course_replace", "type"));
	    return "center/plan/courseReplace/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseReplace:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseReplaceDO courseReplace = courseReplaceService.get(id);
		model.addAttribute("pla_course_replace_flag", commonService.listFieldDict(getAppName(), "pla_course_replace", "flag"));
		model.addAttribute("pla_course_replace_course_class", commonService.listFieldDict(getAppName(), "pla_course_replace", "course_class"));
		model.addAttribute("pla_course_replace_type", commonService.listFieldDict(getAppName(), "pla_course_replace", "type"));

		courseReplace.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplace.getCourseid()));
		if(courseReplace.getAppendCourseid1()!="" && courseReplace.getAppendCourseid1()!= null){
			courseReplace.setAppendCourseid1Name(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplace.getAppendCourseid1()));
		}if(courseReplace.getAppendCourseid2()!="" && courseReplace.getAppendCourseid2()!= null){
			courseReplace.setAppendCourseid2Name(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplace.getAppendCourseid2()));
		}if(courseReplace.getAppendCourseid3()!="" && courseReplace.getAppendCourseid3()!= null){
			courseReplace.setAppendCourseid3Name(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplace.getAppendCourseid3()));
		}if(courseReplace.getAppendCourseid4()!="" && courseReplace.getAppendCourseid4()!= null){
			courseReplace.setAppendCourseid4Name(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseReplace.getAppendCourseid4()));
		}
		courseReplace.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", courseReplace.getSpecialityRecordid()+""));
		model.addAttribute("courseReplace", courseReplace);
	    return "center/plan/courseReplace/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseReplace:add")
	public R save( CourseReplaceDO courseReplace){
        courseReplace.setOperator(ShiroUtils.getUserId().toString());
		if(courseReplaceService.save(courseReplace)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseReplace:edit")
	public R update( CourseReplaceDO courseReplace){
	    courseReplace.setOperator(ShiroUtils.getUserId().toString());
		courseReplaceService.update(courseReplace);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseReplace:remove")
	public R remove( Long id){
		if(courseReplaceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseReplace:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseReplaceService.batchRemove(ids);
		return R.ok();
	}
	
}
