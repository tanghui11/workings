package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentChangeService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 毕业生违纪处理
 * 
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentChange")
public class StudentChangeController extends SystemBaseController{
	@Autowired
	private StudentChangeService studentChangeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentChange:studentChange")
	String StudentChange(){
	    return "center/student/studentChange/studentChange";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentChange:studentChange")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentChangeDO> studentChangeList = studentChangeService.list(query);
        for (StudentChangeDO item : studentChangeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentChangeService.count(query);
		PageUtils pageUtils = new PageUtils(studentChangeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentChange:add")
	String add(Model model){
		model.addAttribute("stu_student_change_type", commonService.listFieldDict(getAppName(), "stu_student_change", "type"));
		model.addAttribute("stu_student_change_status", commonService.listFieldDict(getAppName(), "stu_student_change", "status"));
	    return "center/student/studentChange/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentChange:add")
	public R save( StudentChangeDO studentChange,Long studentid){
		getSpecialityid(studentid);
        studentChange.setOperator(ShiroUtils.getUserId().toString());
		if(studentChangeService.save(studentChange)>0){
			return R.ok();
		}
		return R.error();
	}

    public void getSpecialityid(Long id){
        studentChangeService.getSpecialityid(id);
    }

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update/{id}")
	@RequiresPermissions("student:studentChange:edit")
	public R update( StudentChangeDO studentChange){
	    studentChange.setOperator(ShiroUtils.getUserId().toString());
		studentChangeService.update(studentChange);
		return R.ok();
	}
	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentChange:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentChangeDO studentChange = studentChangeService.get(id);
		model.addAttribute("studentChange", studentChange);
		return "center/student/studentChange/edit";
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentChange:remove")
	public R remove( Long id){
		if(studentChangeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentChange:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentChangeService.batchRemove(ids);
		return R.ok();
	}
	
}
