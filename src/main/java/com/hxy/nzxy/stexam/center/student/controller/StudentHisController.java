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

import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentHis")
public class StudentHisController extends SystemBaseController{
	@Autowired
	private StudentHisService studentHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentHis:studentHis")
	String StudentHis(){
	    return "center/student/studentHis/studentHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentHis:studentHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentHisDO> studentHisList = studentHisService.list(query);
        for (StudentHisDO item : studentHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentHisService.count(query);
		PageUtils pageUtils = new PageUtils(studentHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentHis:add")
	String add(Model model){
	    return "center/student/studentHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentHis:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentHisDO studentHis = studentHisService.get(id);
		model.addAttribute("studentHis", studentHis);
	    return "center/student/studentHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentHis:add")
	public R save( StudentHisDO studentHis){
        studentHis.setOperator(ShiroUtils.getUserId().toString());
		if(studentHisService.save(studentHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentHis:edit")
	public R update( StudentHisDO studentHis){
	    studentHis.setOperator(ShiroUtils.getUserId().toString());
		studentHisService.update(studentHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentHis:remove")
	public R remove( String id){
		if(studentHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentHis:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentHisService.batchRemove(ids);
		return R.ok();
	}
	
}
