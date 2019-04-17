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

import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourseHis")
public class StudentCourseHisController extends SystemBaseController{
	@Autowired
	private StudentCourseHisService studentCourseHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseHis:studentCourseHis")
	String StudentCourseHis(){
	    return "center/student/studentCourseHis/studentCourseHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseHis:studentCourseHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseHisDO> studentCourseHisList = studentCourseHisService.list(query);
        for (StudentCourseHisDO item : studentCourseHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseHisService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseHis:add")
	String add(Model model){
	    return "center/student/studentCourseHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseHisDO studentCourseHis = studentCourseHisService.get(id);
		model.addAttribute("studentCourseHis", studentCourseHis);
	    return "center/student/studentCourseHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseHis:add")
	public R save( StudentCourseHisDO studentCourseHis){
        studentCourseHis.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseHisService.save(studentCourseHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseHis:edit")
	public R update( StudentCourseHisDO studentCourseHis){
	    studentCourseHis.setOperator(ShiroUtils.getUserId().toString());
		studentCourseHisService.update(studentCourseHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseHis:remove")
	public R remove( Long id){
		if(studentCourseHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseHisService.batchRemove(ids);
		return R.ok();
	}
	
}
