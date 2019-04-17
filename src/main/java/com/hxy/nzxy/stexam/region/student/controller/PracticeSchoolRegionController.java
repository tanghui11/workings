package com.hxy.nzxy.stexam.region.student.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
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

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.region.student.service.PracticeSchoolRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/practiceSchoolRegion")
public class PracticeSchoolRegionController extends SystemBaseController{
	@Autowired
	private PracticeSchoolRegionService practiceSchoolRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchoolRegion:practiceSchoolRegion")
	String PracticeSchoolRegion(){
	    return "region/student/practiceSchoolRegion/practiceSchoolRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchoolRegion:practiceSchoolRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolDO> practiceSchoolRegionList = practiceSchoolRegionService.list(query);
        for (PracticeSchoolDO item : practiceSchoolRegionList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "audit_status", item.getAuditStatus()));

            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

        }
		int total = practiceSchoolRegionService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolRegionList, total);
		return pageUtils;
	}
	@GetMapping("/sjCourse")
	@RequiresPermissions("student:practiceSchoolRegion:practiceSchoolRegion")
	String sjCourse(){
		return "region/student/practiceSchoolRegion/sjCourse";
	}
	@ResponseBody
	@GetMapping("/listCourse")
	@RequiresPermissions("student:practiceSchoolRegion:practiceSchoolRegion")
	public PageUtils listCourse(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SpecialityCourseDO> specialityCourseDOList = practiceSchoolRegionService.listCourse(query);
		for (SpecialityCourseDO item : specialityCourseDOList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

		}
		int total = practiceSchoolRegionService.countCourse(query);
		PageUtils pageUtils = new PageUtils(specialityCourseDOList, total);
		return pageUtils;
	}


	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchoolRegion:add")
	String add(Model model){
	    return "region/student/practiceSchoolRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchoolRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolDO practiceSchoolRegion = practiceSchoolRegionService.get(id);
		model.addAttribute("practiceSchoolRegion", practiceSchoolRegion);
	    return "region/student/practiceSchoolRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchoolRegion:add")
	public R save( PracticeSchoolDO practiceSchoolRegion){
        practiceSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
        practiceSchoolRegion.setStatus("a");
		practiceSchoolRegion.setGrade(0f);
        practiceSchoolRegion.setConfirmStatus("a");
        practiceSchoolRegion.setAuditStatus("a");
		if(practiceSchoolRegionService.save(practiceSchoolRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchoolRegion:edit")
	public R update( PracticeSchoolDO practiceSchoolRegion){
	    practiceSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolRegionService.update(practiceSchoolRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolRegion:remove")
	public R remove( Long id){
		if(practiceSchoolRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
