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

import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentRegHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生注册_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentRegHisStudent")
public class StudentRegHisStudentController extends SystemBaseController{
	@Autowired
	private StudentRegHisStudentService studentRegHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentRegHisStudent:studentRegHisStudent")
	String StudentRegHisStudent(){
	    return "school/student/studentRegHisStudent/studentRegHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentRegHisStudent:studentRegHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegHisDO> studentRegHisStudentList = studentRegHisStudentService.list(query);
        for (StudentRegHisDO item : studentRegHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentRegHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentRegHisStudent:add")
	String add(Model model){
	    return "school/student/studentRegHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentRegHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegHisDO studentRegHisStudent = studentRegHisStudentService.get(id);
		model.addAttribute("studentRegHisStudent", studentRegHisStudent);
	    return "school/student/studentRegHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentRegHisStudent:add")
	public R save( StudentRegHisDO studentRegHisStudent){
        studentRegHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegHisStudentService.save(studentRegHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentRegHisStudent:edit")
	public R update( StudentRegHisDO studentRegHisStudent){
	    studentRegHisStudent.setOperator(ShiroUtils.getUserId().toString());
		studentRegHisStudentService.update(studentRegHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHisStudent:remove")
	public R remove( Long id){
		if(studentRegHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentRegHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
