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

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentSpecialityHisStudent")
public class StudentSpecialityHisStudentController extends SystemBaseController{
	@Autowired
	private StudentSpecialityHisStudentService studentSpecialityHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityHisStudent:studentSpecialityHisStudent")
	String StudentSpecialityHisStudent(){
	    return "school/student/studentSpecialityHisStudent/studentSpecialityHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityHisStudent:studentSpecialityHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityHisDO> studentSpecialityHisStudentList = studentSpecialityHisStudentService.list(query);
        for (StudentSpecialityHisDO item : studentSpecialityHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityHisStudent:add")
	String add(Model model){
	    return "school/student/studentSpecialityHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityHisDO studentSpecialityHisStudent = studentSpecialityHisStudentService.get(id);
		model.addAttribute("studentSpecialityHisStudent", studentSpecialityHisStudent);
	    return "school/student/studentSpecialityHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityHisStudent:add")
	public R save( StudentSpecialityHisDO studentSpecialityHisStudent){
        studentSpecialityHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityHisStudentService.save(studentSpecialityHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityHisStudent:edit")
	public R update( StudentSpecialityHisDO studentSpecialityHisStudent){
	    studentSpecialityHisStudent.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityHisStudentService.update(studentSpecialityHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHisStudent:remove")
	public R remove( Long id){
		if(studentSpecialityHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
