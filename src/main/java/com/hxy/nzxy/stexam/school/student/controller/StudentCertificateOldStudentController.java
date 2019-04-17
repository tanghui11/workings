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

import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCertificateOldStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老毕业生数据
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentCertificateOldStudent")
public class StudentCertificateOldStudentController extends SystemBaseController{
	@Autowired
	private StudentCertificateOldStudentService studentCertificateOldStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateOldStudent:studentCertificateOldStudent")
	String StudentCertificateOldStudent(){
	    return "school/student/studentCertificateOldStudent/studentCertificateOldStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateOldStudent:studentCertificateOldStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateOldDO> studentCertificateOldStudentList = studentCertificateOldStudentService.list(query);
        for (StudentCertificateOldDO item : studentCertificateOldStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateOldStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateOldStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCertificateOldStudent:add")
	String add(Model model){
	    return "school/student/studentCertificateOldStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificateOldStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCertificateOldDO studentCertificateOldStudent = studentCertificateOldStudentService.get(id);
		model.addAttribute("studentCertificateOldStudent", studentCertificateOldStudent);
	    return "school/student/studentCertificateOldStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCertificateOldStudent:add")
	public R save( StudentCertificateOldDO studentCertificateOldStudent){
        studentCertificateOldStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentCertificateOldStudentService.save(studentCertificateOldStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificateOldStudent:edit")
	public R update( StudentCertificateOldDO studentCertificateOldStudent){
	    studentCertificateOldStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateOldStudentService.update(studentCertificateOldStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOldStudent:remove")
	public R remove( Long id){
		if(studentCertificateOldStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOldStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateOldStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
