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

import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCertificateOldRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老毕业生数据
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCertificateOldRegion")
public class StudentCertificateOldRegionController extends SystemBaseController{
	@Autowired
	private StudentCertificateOldRegionService studentCertificateOldRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateOldRegion:studentCertificateOldRegion")
	String StudentCertificateOldRegion(){
	    return "region/student/studentCertificateOldRegion/studentCertificateOldRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateOldRegion:studentCertificateOldRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateOldDO> studentCertificateOldRegionList = studentCertificateOldRegionService.list(query);
        for (StudentCertificateOldDO item : studentCertificateOldRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateOldRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateOldRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCertificateOldRegion:add")
	String add(Model model){
	    return "region/student/studentCertificateOldRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificateOldRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCertificateOldDO studentCertificateOldRegion = studentCertificateOldRegionService.get(id);
		model.addAttribute("studentCertificateOldRegion", studentCertificateOldRegion);
	    return "region/student/studentCertificateOldRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCertificateOldRegion:add")
	public R save( StudentCertificateOldDO studentCertificateOldRegion){
        studentCertificateOldRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentCertificateOldRegionService.save(studentCertificateOldRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificateOldRegion:edit")
	public R update( StudentCertificateOldDO studentCertificateOldRegion){
	    studentCertificateOldRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateOldRegionService.update(studentCertificateOldRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOldRegion:remove")
	public R remove( Long id){
		if(studentCertificateOldRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificateOldRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateOldRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
