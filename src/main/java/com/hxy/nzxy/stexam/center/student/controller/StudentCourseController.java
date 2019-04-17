package com.hxy.nzxy.stexam.center.student.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
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

import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourse")
public class StudentCourseController extends SystemBaseController{
	@Autowired
	private StudentCourseService studentCourseService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private StudentCourseDao studentCourseDao;
	@GetMapping()
	@RequiresPermissions("student:studentCourse:studentCourse")
	String StudentCourse(){
	    return "center/student/studentCourse/studentCourse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourse:studentCourse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.list(query);
        for (StudentCourseDO item : studentCourseList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	@GetMapping("/area")
	@RequiresPermissions("student:studentCourseArea:studentCourseArea")
	String StudentCourseArea(){
		return "center/student/studentCourse/studentCourseArea";
	}

	@ResponseBody
	@GetMapping("/listArea")
	@RequiresPermissions("student:studentCourseArea:studentCourseArea")
	public PageUtils listArea(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.list(query);
		for (StudentCourseDO item : studentCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentCourseService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourse:add")
	String add(Model model){
	    return "center/student/studentCourse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourse:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourse = studentCourseService.get(id);
		model.addAttribute("studentCourse", studentCourse);
	    return "center/student/studentCourse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourse:add")
	public R save( StudentCourseDO studentCourse){
        studentCourse.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseService.save(studentCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourse:edit")
	public R update( StudentCourseDO studentCourse){
	    studentCourse.setOperator(ShiroUtils.getUserId().toString());
		studentCourseService.update(studentCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourse:remove")
	public R remove( Long id){
		if(studentCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourse:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseService.batchRemove(ids);
		return R.ok();
	}

}
