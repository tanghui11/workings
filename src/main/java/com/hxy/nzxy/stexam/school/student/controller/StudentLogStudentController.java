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

import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.school.student.service.StudentLogStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentLogStudent")
public class StudentLogStudentController extends SystemBaseController{
	@Autowired
	private StudentLogStudentService studentLogStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentLogStudent:studentLogStudent")
	String StudentLogStudent(){
	    return "school/student/studentLogStudent/studentLogStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentLogStudent:studentLogStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentLogDO> studentLogStudentList = studentLogStudentService.list(query);
        for (StudentLogDO item : studentLogStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentLogStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentLogStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentLogStudent:add")
	String add(Model model){
	    return "school/student/studentLogStudent/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:studentLogStudent:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		StudentLogDO studentLogStudent = studentLogStudentService.get(changeType);
		model.addAttribute("studentLogStudent", studentLogStudent);
	    return "school/student/studentLogStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentLogStudent:add")
	public R save( StudentLogDO studentLogStudent){
        studentLogStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentLogStudentService.save(studentLogStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentLogStudent:edit")
	public R update( StudentLogDO studentLogStudent){
	    studentLogStudent.setOperator(ShiroUtils.getUserId().toString());
		studentLogStudentService.update(studentLogStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentLogStudent:remove")
	public R remove( String changeType){
		if(studentLogStudentService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentLogStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		studentLogStudentService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
