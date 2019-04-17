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

import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.center.student.service.StudentGradeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentGrade")
public class StudentGradeController extends SystemBaseController{
	@Autowired
	private StudentGradeService studentGradeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentGrade:studentGrade")
	String StudentGrade(){
	    return "center/student/studentGrade/studentGrade";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentGrade:studentGrade")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentGradeDO> studentGradeList = studentGradeService.list(query);
        for (StudentGradeDO item : studentGradeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentGradeService.count(query);
		PageUtils pageUtils = new PageUtils(studentGradeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentGrade:add")
	String add(Model model){
	    return "center/student/studentGrade/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentGrade:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentGradeDO studentGrade = studentGradeService.get(id);
		model.addAttribute("studentGrade", studentGrade);
	    return "center/student/studentGrade/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentGrade:add")
	public R save( StudentGradeDO studentGrade){
        studentGrade.setOperator(ShiroUtils.getUserId().toString());
		if(studentGradeService.save(studentGrade)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentGrade:edit")
	public R update( StudentGradeDO studentGrade){
	    studentGrade.setOperator(ShiroUtils.getUserId().toString());
		studentGradeService.update(studentGrade);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentGrade:remove")
	public R remove( String id){
		if(studentGradeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentGrade:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentGradeService.batchRemove(ids);
		return R.ok();
	}
	
}
