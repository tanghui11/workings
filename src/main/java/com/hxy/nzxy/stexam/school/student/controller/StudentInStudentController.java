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

import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.school.student.service.StudentInStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentInStudent")
public class StudentInStudentController extends SystemBaseController{
	@Autowired
	private StudentInStudentService studentInStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentInStudent:studentInStudent")
	String StudentInStudent(){
	    return "school/student/studentInStudent/studentInStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentInStudent:studentInStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInDO> studentInStudentList = studentInStudentService.list(query);
        for (StudentInDO item : studentInStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentInStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentInStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentInStudent:add")
	String add(Model model){
	    return "school/student/studentInStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentInStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentInDO studentInStudent = studentInStudentService.get(id);
		model.addAttribute("studentInStudent", studentInStudent);
	    return "school/student/studentInStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentInStudent:add")
	public R save( StudentInDO studentInStudent){
        studentInStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentInStudentService.save(studentInStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentInStudent:edit")
	public R update( StudentInDO studentInStudent){
	    studentInStudent.setOperator(ShiroUtils.getUserId().toString());
		studentInStudentService.update(studentInStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentInStudent:remove")
	public R remove( Long id){
		if(studentInStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentInStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentInStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
