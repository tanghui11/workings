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

import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentHisStudent")
public class StudentHisStudentController extends SystemBaseController{
	@Autowired
	private StudentHisStudentService studentHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentHisStudent:studentHisStudent")
	String StudentHisStudent(){
	    return "school/student/studentHisStudent/studentHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentHisStudent:studentHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentHisDO> studentHisStudentList = studentHisStudentService.list(query);
        for (StudentHisDO item : studentHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentHisStudent:add")
	String add(Model model){
	    return "school/student/studentHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentHisStudent:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentHisDO studentHisStudent = studentHisStudentService.get(id);
		model.addAttribute("studentHisStudent", studentHisStudent);
	    return "school/student/studentHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentHisStudent:add")
	public R save( StudentHisDO studentHisStudent){
        studentHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentHisStudentService.save(studentHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentHisStudent:edit")
	public R update( StudentHisDO studentHisStudent){
	    studentHisStudent.setOperator(ShiroUtils.getUserId().toString());
		studentHisStudentService.update(studentHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentHisStudent:remove")
	public R remove( String id){
		if(studentHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
