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

import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.school.student.service.StudentTempStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentTempStudent")
public class StudentTempStudentController extends SystemBaseController{
	@Autowired
	private StudentTempStudentService studentTempStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentTempStudent:studentTempStudent")
	String StudentTempStudent(){
	    return "school/student/studentTempStudent/studentTempStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentTempStudent:studentTempStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentTempDO> studentTempStudentList = studentTempStudentService.list(query);
        for (StudentTempDO item : studentTempStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentTempStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentTempStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentTempStudent:add")
	String add(Model model){
	    return "school/student/studentTempStudent/add";
	}

	@GetMapping("/edit/{type}")
	@RequiresPermissions("student:studentTempStudent:edit")
	String edit(@PathVariable("type") String type,Model model){
		StudentTempDO studentTempStudent = studentTempStudentService.get(type);
		model.addAttribute("studentTempStudent", studentTempStudent);
	    return "school/student/studentTempStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentTempStudent:add")
	public R save( StudentTempDO studentTempStudent){
        studentTempStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentTempStudentService.save(studentTempStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentTempStudent:edit")
	public R update( StudentTempDO studentTempStudent){
	    studentTempStudent.setOperator(ShiroUtils.getUserId().toString());
		studentTempStudentService.update(studentTempStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentTempStudent:remove")
	public R remove( String type){
		if(studentTempStudentService.remove(type)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentTempStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] types){
		studentTempStudentService.batchRemove(types);
		return R.ok();
	}
	
}
