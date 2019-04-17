package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseAppendService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseAppendDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 专业加考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseAppend")
public class CourseAppendController extends SystemBaseController{
	@Autowired
	private CourseAppendService courseAppendService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseAppend:courseAppend")
	String CourseAppend(){
	    return "center/plan/courseAppend/courseAppend";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseAppend:courseAppend")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseAppendDO> courseAppendList = courseAppendService.list(query);
        for (CourseAppendDO item : courseAppendList) {
        	item.setClassify(FieldDictUtil.get(getAppName(), "pla_course_append", "classify", item.getClassify()));
        	item.setType(FieldDictUtil.get(getAppName(), "pla_course_append", "type", item.getType()));
        	item.setSpecialityRecordName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", item.getSpecialityRecordid()+""));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseAppendService.count(query);
		PageUtils pageUtils = new PageUtils(courseAppendList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseAppend:add")
	String add(Model model){
		model.addAttribute("pla_course_append_classify", commonService.listFieldDict(getAppName(), "pla_course_append", "classify"));
		model.addAttribute("pla_course_append_type", commonService.listFieldDict(getAppName(), "pla_course_append", "type"));
	    return "center/plan/courseAppend/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseAppend:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseAppendDO courseAppend = courseAppendService.get(id);
		model.addAttribute("pla_course_append_classify", commonService.listFieldDict(getAppName(), "pla_course_append", "classify"));
		model.addAttribute("pla_course_append_type", commonService.listFieldDict(getAppName(), "pla_course_append", "type"));
		courseAppend.setSpecialityRecordName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_specialityName", courseAppend.getSpecialityRecordid()+""));

		model.addAttribute("courseAppend", courseAppend);
	    return "center/plan/courseAppend/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseAppend:add")
	public R save( CourseAppendDO courseAppend){
        courseAppend.setOperator(ShiroUtils.getUserId().toString());
		if(courseAppendService.save(courseAppend)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseAppend:edit")
	public R update( CourseAppendDO courseAppend){
	    courseAppend.setOperator(ShiroUtils.getUserId().toString());
		courseAppendService.update(courseAppend);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseAppend:remove")
	public R remove( Long id){
		if(courseAppendService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseAppend:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseAppendService.batchRemove(ids);
		return R.ok();
	}

}
