package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentInService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentIn")
public class StudentInController extends SystemBaseController{
	@Autowired
	private StudentInService studentInService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentIn:studentIn")
	String StudentIn(){
	    return "center/student/studentIn/studentIn";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentIn:studentIn")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInDO> studentInList = studentInService.list(query);
        for (StudentInDO item : studentInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_in","status",item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_in","audit_status",item.getAuditStatus()));
			item.setGender(FieldDictUtil.get(getAppName(),"sys_worker","gender",item.getGender()));
        }
		int total = studentInService.count(query);
		PageUtils pageUtils = new PageUtils(studentInList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentIn:add")
	String add(Model model){
		model.addAttribute("stu_student_in_status", commonService.listFieldDict(getAppName(), "stu_student_in", "status"));
		model.addAttribute("stu_student_in_audit_status", commonService.listFieldDict(getAppName(), "stu_student_in", "audit_status"));

		return "center/student/studentIn/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentIn:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentInDO studentIn = studentInService.get(id);
		model.addAttribute("stu_student_in_status", commonService.listFieldDict(getAppName(), "stu_student_in", "status"));
		model.addAttribute("stu_student_in_audit_status", commonService.listFieldDict(getAppName(), "stu_student_in", "audit_status"));
		model.addAttribute("sys_worker_gender", commonService.listFieldDict(getAppName(), "sys_worker", "gender"));
		model.addAttribute("studentIn", studentIn);
	    return "center/student/studentIn/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentIn:add")
	public R save( StudentInDO studentIn){
        studentIn.setOperator(ShiroUtils.getUserId().toString());
		if(studentInService.save(studentIn)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentIn:edit")
	public R update( StudentInDO studentIn){
	    studentIn.setOperator(ShiroUtils.getUserId().toString());
		studentInService.update(studentIn);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentIn:remove")
	public R remove( Long id){
		if(studentInService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentIn:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentInService.batchRemove(ids);
		return R.ok();
	}

/**
 *功能描述 取消调档
 * @author ypp
 * @date 2018/11/21
 * @param
 * @return com.hxy.nzxy.stexam.common.utils.R
 */
	@PostMapping( "/cancelStatus")
	@ResponseBody
	@RequiresPermissions("student:studentIn:batchRemove")
	public R cancelStatus(@RequestParam("id") Long id){
		studentInService.cancelStatus(id);
		return R.ok();
	}
}