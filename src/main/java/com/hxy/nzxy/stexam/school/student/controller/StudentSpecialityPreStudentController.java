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

import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityPreStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentSpecialityPreStudent")
public class StudentSpecialityPreStudentController extends SystemBaseController{
	@Autowired
	private StudentSpecialityPreStudentService studentSpecialityPreStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityPreStudent:studentSpecialityPreStudent")
	String StudentSpecialityPreStudent(){
	    return "school/student/studentSpecialityPreStudent/studentSpecialityPreStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityPreStudent:studentSpecialityPreStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityPreDO> studentSpecialityPreStudentList = studentSpecialityPreStudentService.list(query);
        for (StudentSpecialityPreDO item : studentSpecialityPreStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityPreStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityPreStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityPreStudent:add")
	String add(Model model){
	    return "school/student/studentSpecialityPreStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityPreStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityPreDO studentSpecialityPreStudent = studentSpecialityPreStudentService.get(id);
		model.addAttribute("studentSpecialityPreStudent", studentSpecialityPreStudent);
	    return "school/student/studentSpecialityPreStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityPreStudent:add")
	public R save( StudentSpecialityPreDO studentSpecialityPreStudent){
        studentSpecialityPreStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityPreStudentService.save(studentSpecialityPreStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityPreStudent:edit")
	public R update( StudentSpecialityPreDO studentSpecialityPreStudent){
	    studentSpecialityPreStudent.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityPreStudentService.update(studentSpecialityPreStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPreStudent:remove")
	public R remove( Long id){
		if(studentSpecialityPreStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPreStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityPreStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
