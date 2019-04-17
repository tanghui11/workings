package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentCertificateOldService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 老毕业证管理
 * 
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCertificateOld")
public class StudentCertificateOldController extends SystemBaseController{
	@Autowired
	private StudentCertificateOldService studentCertificateOldService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateOld:studentCertificateOld")
	String StudentCertificateOld(){
	    return "center/student/studentCertificateOld/studentCertificateOld";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateOld:studentCertificateOld")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateOldDO> studentCertificateOldList = studentCertificateOldService.list(query);
        for (StudentCertificateOldDO item : studentCertificateOldList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateOldService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateOldList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCertificateOld:add")
	String add(Model model){
	    return "center/student/studentCertificateOld/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificateOld:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCertificateOldDO studentCertificateOld = studentCertificateOldService.get(id);
		model.addAttribute("studentCertificateOld", studentCertificateOld);
	    return "center/student/studentCertificateOld/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCertificateOld:add")
	public R save( StudentCertificateOldDO studentCertificateOld){
        studentCertificateOld.setOperator(ShiroUtils.getUserId().toString());
		if(studentCertificateOldService.save(studentCertificateOld)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificateOld:edit")
	public R update( StudentCertificateOldDO studentCertificateOld){
	    studentCertificateOld.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateOldService.update(studentCertificateOld);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOld:remove")
	public R remove( Long id){
		if(studentCertificateOldService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOld:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateOldService.batchRemove(ids);
		return R.ok();
	}
	
}
