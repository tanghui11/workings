package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCertificateStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentCertificateStudent")
public class StudentCertificateStudentController extends SystemBaseController{
	@Autowired
	private StudentCertificateStudentService studentCertificateStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateStudent:studentCertificateStudent")
	String StudentCertificateStudent(){
	    return "school/student/studentCertificateStudent/studentCertificateStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateStudent:studentCertificateStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateDO> studentCertificateStudentList = studentCertificateStudentService.list(query);
        for (StudentCertificateDO item : studentCertificateStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCertificateStudent:add")
	String add(Model model){
	    return "school/student/studentCertificateStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificateStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCertificateDO studentCertificateStudent = studentCertificateStudentService.get(id);
		model.addAttribute("studentCertificateStudent", studentCertificateStudent);
	    return "school/student/studentCertificateStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCertificateStudent:add")
	public R save( StudentCertificateDO studentCertificateStudent){
        studentCertificateStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentCertificateStudentService.save(studentCertificateStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificateStudent:edit")
	public R update( StudentCertificateDO studentCertificateStudent){
	    studentCertificateStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateStudentService.update(studentCertificateStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateStudent:remove")
	public R remove( Long id){
		if(studentCertificateStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateStudentService.batchRemove(ids);
		return R.ok();
	}
}