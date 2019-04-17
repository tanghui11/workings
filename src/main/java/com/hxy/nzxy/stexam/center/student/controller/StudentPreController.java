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

import com.hxy.nzxy.stexam.domain.StudentPreDO;
import com.hxy.nzxy.stexam.center.student.service.StudentPreService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentPre")
public class StudentPreController extends SystemBaseController{
	@Autowired
	private StudentPreService studentPreService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentPre:studentPre")
	String StudentPre(){
	    return "center/student/studentPre/studentPre";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentPre:studentPre")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentPreDO> studentPreList = studentPreService.list(query);
        for (StudentPreDO item : studentPreList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentPreService.count(query);
		PageUtils pageUtils = new PageUtils(studentPreList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentPre:add")
	String add(Model model){
	    return "center/student/studentPre/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentPre:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentPreDO studentPre = studentPreService.get(id);
		model.addAttribute("studentPre", studentPre);
	    return "center/student/studentPre/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentPre:add")
	public R save( StudentPreDO studentPre){
        studentPre.setOperator(ShiroUtils.getUserId().toString());
		if(studentPreService.save(studentPre)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentPre:edit")
	public R update( StudentPreDO studentPre){
	    studentPre.setOperator(ShiroUtils.getUserId().toString());
		studentPreService.update(studentPre);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentPre:remove")
	public R remove( Long id){
		if(studentPreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentPre:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentPreService.batchRemove(ids);
		return R.ok();
	}
	
}
