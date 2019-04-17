package com.hxy.nzxy.stexam.center.student.controller;

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

import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.center.student.service.StudentRegService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 *
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentReg")
public class StudentRegController extends SystemBaseController{
	@Autowired
	private StudentRegService studentRegService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentReg:studentReg")
	String StudentReg(Model model){
		model.addAttribute("stu_student_reg_status", commonService.listFieldDict(getAppName(), "stu_student_reg", "status"));
		return "center/student/studentReg/studentReg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentReg:studentReg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegDO> studentRegList = studentRegService.list(query);
        for (StudentRegDO item : studentRegList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_reg", "status", item.getStatus()));
			item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", String.valueOf(item.getSpecialityid())));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentRegService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentReg:add")
	String add(Model model){
	    return "center/student/studentReg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentReg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegDO studentReg = studentRegService.get(id);
		model.addAttribute("studentReg", studentReg);
	    return "center/student/studentReg/edit";
	}
	

	/**
	 * 单个审核/取消审核
	 */
	@PostMapping( "/updateAudit")
	@ResponseBody
	@RequiresPermissions("student:studentReg:audit")
	public R updateAudit( @RequestParam Map<String, Object> params){
		String auditStatus=(String)params.get("auditStatus");
		String messages="审核成功！";
		if("a".equals(auditStatus))
		{
			messages="取消审核成功！";
		}
		if(studentRegService.updateAudit(params)>0){
			return R.ok(messages);
		}
		return R.error();
	}

	/**
	 * 批量审核/取消审核
	 */
	@PostMapping( "/batchUpdateAudit")
	@ResponseBody
	@RequiresPermissions("student:studentReg:batchAudit")
	public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("auditStatus") String auditStatus){

		String messages="批量审核成功！";
		if("a".equals(auditStatus))
		{
			messages="批量取消审核成功！";
		}
		if(studentRegService.batchUpdateAudit(ids,auditStatus)>0) {
			return R.ok();
		}
		return R.error();
	}
}
