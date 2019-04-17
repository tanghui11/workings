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

import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import com.hxy.nzxy.stexam.school.student.service.StudentChangeStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生信息变更表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentChangeStudent")
public class StudentChangeStudentController extends SystemBaseController{
	@Autowired
	private StudentChangeStudentService studentChangeStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentChangeStudent:studentChangeStudent")
	String StudentChangeStudent(){
	    return "school/student/studentChangeStudent/studentChangeStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentChangeStudent:studentChangeStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentChangeDO> studentChangeStudentList = studentChangeStudentService.list(query);
        for (StudentChangeDO item : studentChangeStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentChangeStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentChangeStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentChangeStudent:add")
	String add(Model model){
	    return "school/student/studentChangeStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentChangeStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentChangeDO studentChangeStudent = studentChangeStudentService.get(id);
		model.addAttribute("studentChangeStudent", studentChangeStudent);
	    return "school/student/studentChangeStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentChangeStudent:add")
	public R save( StudentChangeDO studentChangeStudent){
        studentChangeStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentChangeStudentService.save(studentChangeStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentChangeStudent:edit")
	public R update( StudentChangeDO studentChangeStudent){
	    studentChangeStudent.setOperator(ShiroUtils.getUserId().toString());
		studentChangeStudentService.update(studentChangeStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentChangeStudent:remove")
	public R remove( Long id){
		if(studentChangeStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentChangeStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentChangeStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
