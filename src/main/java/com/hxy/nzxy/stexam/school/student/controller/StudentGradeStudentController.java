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

import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.school.student.service.StudentGradeStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentGradeStudent")
public class StudentGradeStudentController extends SystemBaseController{
	@Autowired
	private StudentGradeStudentService studentGradeStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentGradeStudent:studentGradeStudent")
	String StudentGradeStudent(){
	    return "school/student/studentGradeStudent/studentGradeStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentGradeStudent:studentGradeStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentGradeDO> studentGradeStudentList = studentGradeStudentService.list(query);
        for (StudentGradeDO item : studentGradeStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentGradeStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentGradeStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentGradeStudent:add")
	String add(Model model){
	    return "school/student/studentGradeStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentGradeStudent:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentGradeDO studentGradeStudent = studentGradeStudentService.get(id);
		model.addAttribute("studentGradeStudent", studentGradeStudent);
	    return "school/student/studentGradeStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentGradeStudent:add")
	public R save( StudentGradeDO studentGradeStudent){
        studentGradeStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentGradeStudentService.save(studentGradeStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentGradeStudent:edit")
	public R update( StudentGradeDO studentGradeStudent){
	    studentGradeStudent.setOperator(ShiroUtils.getUserId().toString());
		studentGradeStudentService.update(studentGradeStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentGradeStudent:remove")
	public R remove( String id){
		if(studentGradeStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentGradeStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentGradeStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
