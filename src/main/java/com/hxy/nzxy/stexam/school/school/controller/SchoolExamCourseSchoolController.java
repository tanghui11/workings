package com.hxy.nzxy.stexam.school.school.controller;

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

import com.hxy.nzxy.stexam.domain.SchoolExamCourseDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolExamCourseSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 学院考试课程选择
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/schoolExamCourseSchool")
public class SchoolExamCourseSchoolController extends SystemBaseController{
	@Autowired
	private SchoolExamCourseSchoolService schoolExamCourseSchoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolExamCourseSchool:schoolExamCourseSchool")
	String SchoolExamCourseSchool(){
	    return "school/school/schoolExamCourseSchool/schoolExamCourseSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolExamCourseSchool:schoolExamCourseSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolExamCourseDO> schoolExamCourseSchoolList = schoolExamCourseSchoolService.list(query);
        for (SchoolExamCourseDO item : schoolExamCourseSchoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolExamCourseSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolExamCourseSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolExamCourseSchool:add")
	String add(Model model){
	    return "school/school/schoolExamCourseSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolExamCourseSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolExamCourseDO schoolExamCourseSchool = schoolExamCourseSchoolService.get(id);
		model.addAttribute("schoolExamCourseSchool", schoolExamCourseSchool);
	    return "school/school/schoolExamCourseSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolExamCourseSchool:add")
	public R save( SchoolExamCourseDO schoolExamCourseSchool){
        schoolExamCourseSchool.setOperator(ShiroUtils.getUserId().toString());
		if(schoolExamCourseSchoolService.save(schoolExamCourseSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolExamCourseSchool:edit")
	public R update( SchoolExamCourseDO schoolExamCourseSchool){
	    schoolExamCourseSchool.setOperator(ShiroUtils.getUserId().toString());
		schoolExamCourseSchoolService.update(schoolExamCourseSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolExamCourseSchool:remove")
	public R remove( Long id){
		if(schoolExamCourseSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolExamCourseSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolExamCourseSchoolService.batchRemove(ids);
		return R.ok();
	}
	
}
