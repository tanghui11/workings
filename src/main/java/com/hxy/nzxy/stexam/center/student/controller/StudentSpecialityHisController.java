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

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生报考专业信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
 
@Controller
@RequestMapping("/His")
public class StudentSpecialityHisController extends SystemBaseController{
	@Autowired
	private StudentSpecialityHisService studentSpecialityHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentSpecialityHis:studentSpecialityHis")
	String StudentSpecialityHis(){
	    return "center/student/studentSpecialityHis/studentSpecialityHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentSpecialityHis:studentSpecialityHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentSpecialityHisDO> studentSpecialityHisList = studentSpecialityHisService.list(query);
        for (StudentSpecialityHisDO item : studentSpecialityHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentSpecialityHisService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentSpecialityHis:add")
	String add(Model model){
	    return "center/student/studentSpecialityHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentSpecialityHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentSpecialityHisDO studentSpecialityHis = studentSpecialityHisService.get(id);
		model.addAttribute("studentSpecialityHis", studentSpecialityHis);
	    return "center/student/studentSpecialityHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentSpecialityHis:add")
	public R save( StudentSpecialityHisDO studentSpecialityHis){
        studentSpecialityHis.setOperator(ShiroUtils.getUserId().toString());
		if(studentSpecialityHisService.save(studentSpecialityHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentSpecialityHis:edit")
	public R update( StudentSpecialityHisDO studentSpecialityHis){
	    studentSpecialityHis.setOperator(ShiroUtils.getUserId().toString());
		studentSpecialityHisService.update(studentSpecialityHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHis:remove")
	public R remove( Long id){
		if(studentSpecialityHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentSpecialityHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentSpecialityHisService.batchRemove(ids);
		return R.ok();
	}
	
}
