package com.hxy.nzxy.stexam.school.student.controller;

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

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseTempStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentCourseTempStudent")
public class StudentCourseTempStudentController extends SystemBaseController{
	@Autowired
	private StudentCourseTempStudentService studentCourseTempStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseTempStudent:studentCourseTempStudent")
	String StudentCourseTempStudent(){
	    return "school/student/studentCourseTempStudent/studentCourseTempStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseTempStudent:studentCourseTempStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseTempDO> studentCourseTempStudentList = studentCourseTempStudentService.list(query);
        for (StudentCourseTempDO item : studentCourseTempStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseTempStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseTempStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseTempStudent:add")
	String add(Model model){
	    return "school/student/studentCourseTempStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseTempStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseTempDO studentCourseTempStudent = studentCourseTempStudentService.get(id);
		model.addAttribute("studentCourseTempStudent", studentCourseTempStudent);
	    return "school/student/studentCourseTempStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseTempStudent:add")
	public R save( StudentCourseTempDO studentCourseTempStudent){
        studentCourseTempStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseTempStudentService.save(studentCourseTempStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseTempStudent:edit")
	public R update( StudentCourseTempDO studentCourseTempStudent){
	    studentCourseTempStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCourseTempStudentService.update(studentCourseTempStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTempStudent:remove")
	public R remove( Long id){
		if(studentCourseTempStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTempStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseTempStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
