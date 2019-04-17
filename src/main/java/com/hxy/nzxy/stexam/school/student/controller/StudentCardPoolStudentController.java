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

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCardPoolStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 准考证打印池
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentCardPoolStudent")
public class StudentCardPoolStudentController extends SystemBaseController{
	@Autowired
	private StudentCardPoolStudentService studentCardPoolStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCardPoolStudent:studentCardPoolStudent")
	String StudentCardPoolStudent(){
	    return "school/student/studentCardPoolStudent/studentCardPoolStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCardPoolStudent:studentCardPoolStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCardPoolDO> studentCardPoolStudentList = studentCardPoolStudentService.list(query);
        for (StudentCardPoolDO item : studentCardPoolStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCardPoolStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCardPoolStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCardPoolStudent:add")
	String add(Model model){
	    return "school/student/studentCardPoolStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCardPoolStudent:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentCardPoolDO studentCardPoolStudent = studentCardPoolStudentService.get(id);
		model.addAttribute("studentCardPoolStudent", studentCardPoolStudent);
	    return "school/student/studentCardPoolStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCardPoolStudent:add")
	public R save( StudentCardPoolDO studentCardPoolStudent){
        studentCardPoolStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentCardPoolStudentService.save(studentCardPoolStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCardPoolStudent:edit")
	public R update( StudentCardPoolDO studentCardPoolStudent){
	    studentCardPoolStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCardPoolStudentService.update(studentCardPoolStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPoolStudent:remove")
	public R remove( String id){
		if(studentCardPoolStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPoolStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentCardPoolStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
