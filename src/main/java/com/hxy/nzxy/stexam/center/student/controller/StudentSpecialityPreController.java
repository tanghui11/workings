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

import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityPreService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
 
@Controller
@RequestMapping("/student/studentSpecialityPre")
public class StudentSpecialityPreController extends SystemBaseController{
	@Autowired
	private StudentSpecialityPreService studentSpecialityPreService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityPre:studentSpecialityPre")
	String StudentSpecialityPre(){
	    return "center/student/studentSpecialityPre/studentSpecialityPre";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityPre:studentSpecialityPre")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityPreDO> studentSpecialityPreList = studentSpecialityPreService.list(query);
        for (StudentSpecialityPreDO item : studentSpecialityPreList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityPreService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityPreList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityPre:add")
	String add(Model model){
	    return "center/student/studentSpecialityPre/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityPre:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityPreDO studentSpecialityPre = studentSpecialityPreService.get(id);
		model.addAttribute("studentSpecialityPre", studentSpecialityPre);
	    return "center/student/studentSpecialityPre/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityPre:add")
	public R save( StudentSpecialityPreDO studentSpecialityPre){
        studentSpecialityPre.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityPreService.save(studentSpecialityPre)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityPre:edit")
	public R update( StudentSpecialityPreDO studentSpecialityPre){
	    studentSpecialityPre.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityPreService.update(studentSpecialityPre);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPre:remove")
	public R remove( Long id){
		if(studentSpecialityPreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityPre:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityPreService.batchRemove(ids);
		return R.ok();
	}
	
}
