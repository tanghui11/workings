package com.hxy.nzxy.stexam.region.student.controller;

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

import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCertificateRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCertificateRegion")
public class StudentCertificateRegionController extends SystemBaseController{
	@Autowired
	private StudentCertificateRegionService studentCertificateRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateRegion:studentCertificateRegion")
	String StudentCertificateRegion(){
	    return "region/student/studentCertificateRegion/studentCertificateRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateRegion:studentCertificateRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateDO> studentCertificateRegionList = studentCertificateRegionService.list(query);
        for (StudentCertificateDO item : studentCertificateRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCertificateRegion:add")
	String add(Model model){
	    return "region/student/studentCertificateRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificateRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCertificateDO studentCertificateRegion = studentCertificateRegionService.get(id);
		model.addAttribute("studentCertificateRegion", studentCertificateRegion);
	    return "region/student/studentCertificateRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCertificateRegion:add")
	public R save( StudentCertificateDO studentCertificateRegion){
        studentCertificateRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentCertificateRegionService.save(studentCertificateRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificateRegion:edit")
	public R update( StudentCertificateDO studentCertificateRegion){
	    studentCertificateRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateRegionService.update(studentCertificateRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateRegion:remove")
	public R remove( Long id){
		if(studentCertificateRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
