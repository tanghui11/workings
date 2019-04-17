package com.hxy.nzxy.stexam.center.school.controller;

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
import com.hxy.nzxy.stexam.center.school.service.SchoolExamCourseService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 学院考试课程选择
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/schoolExamCourse")
public class SchoolExamCourseController extends SystemBaseController{
	@Autowired
	private SchoolExamCourseService schoolExamCourseService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolExamCourse:schoolExamCourse")
	String SchoolExamCourse(){
	    return "center/school/schoolExamCourse/schoolExamCourse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolExamCourse:schoolExamCourse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolExamCourseDO> schoolExamCourseList = schoolExamCourseService.list(query);
        for (SchoolExamCourseDO item : schoolExamCourseList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolExamCourseService.count(query);
		PageUtils pageUtils = new PageUtils(schoolExamCourseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolExamCourse:add")
	String add(Model model){
	    return "center/school/schoolExamCourse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolExamCourse:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolExamCourseDO schoolExamCourse = schoolExamCourseService.get(id);
		model.addAttribute("schoolExamCourse", schoolExamCourse);
	    return "center/school/schoolExamCourse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolExamCourse:add")
	public R save( SchoolExamCourseDO schoolExamCourse){
        schoolExamCourse.setOperator(ShiroUtils.getUserId().toString());
		if(schoolExamCourseService.save(schoolExamCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolExamCourse:edit")
	public R update( SchoolExamCourseDO schoolExamCourse){
	    schoolExamCourse.setOperator(ShiroUtils.getUserId().toString());
		schoolExamCourseService.update(schoolExamCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolExamCourse:remove")
	public R remove( Long id){
		if(schoolExamCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolExamCourse:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolExamCourseService.batchRemove(ids);
		return R.ok();
	}
	
}
