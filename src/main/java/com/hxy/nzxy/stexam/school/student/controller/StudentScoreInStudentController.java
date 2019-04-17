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

import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.school.student.service.StudentScoreInStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentScoreInStudent")
public class StudentScoreInStudentController extends SystemBaseController{
	@Autowired
	private StudentScoreInStudentService studentScoreInStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentScoreInStudent:studentScoreInStudent")
	String StudentScoreInStudent(){
	    return "school/student/studentScoreInStudent/studentScoreInStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentScoreInStudent:studentScoreInStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentScoreInDO> studentScoreInStudentList = studentScoreInStudentService.list(query);
        for (StudentScoreInDO item : studentScoreInStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentScoreInStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentScoreInStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentScoreInStudent:add")
	String add(Model model){
	    return "school/student/studentScoreInStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentScoreInStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentScoreInDO studentScoreInStudent = studentScoreInStudentService.get(id);
		model.addAttribute("studentScoreInStudent", studentScoreInStudent);
	    return "school/student/studentScoreInStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentScoreInStudent:add")
	public R save( StudentScoreInDO studentScoreInStudent){
        studentScoreInStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentScoreInStudentService.save(studentScoreInStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentScoreInStudent:edit")
	public R update( StudentScoreInDO studentScoreInStudent){
	    studentScoreInStudent.setOperator(ShiroUtils.getUserId().toString());
		studentScoreInStudentService.update(studentScoreInStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreInStudent:remove")
	public R remove( Long id){
		if(studentScoreInStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreInStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentScoreInStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
