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

import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentRegHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生注册_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentRegHis")
public class StudentRegHisController extends SystemBaseController{
	@Autowired
	private StudentRegHisService studentRegHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentRegHis:studentRegHis")
	String StudentRegHis(){
	    return "center/student/studentRegHis/studentRegHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentRegHis:studentRegHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegHisDO> studentRegHisList = studentRegHisService.list(query);
        for (StudentRegHisDO item : studentRegHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentRegHisService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentRegHis:add")
	String add(Model model){
	    return "center/student/studentRegHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentRegHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegHisDO studentRegHis = studentRegHisService.get(id);
		model.addAttribute("studentRegHis", studentRegHis);
	    return "center/student/studentRegHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentRegHis:add")
	public R save( StudentRegHisDO studentRegHis){
        studentRegHis.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegHisService.save(studentRegHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentRegHis:edit")
	public R update( StudentRegHisDO studentRegHis){
	    studentRegHis.setOperator(ShiroUtils.getUserId().toString());
		studentRegHisService.update(studentRegHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHis:remove")
	public R remove( Long id){
		if(studentRegHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentRegHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentRegHisService.batchRemove(ids);
		return R.ok();
	}
	
}
