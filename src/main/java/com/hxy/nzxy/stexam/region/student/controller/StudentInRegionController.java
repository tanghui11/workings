package com.hxy.nzxy.stexam.region.student.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.region.student.dao.StudentRegionDao;
import com.hxy.nzxy.stexam.school.student.dao.StudentStudentDao;
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

import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.region.student.service.StudentInRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentInRegion")
public class StudentInRegionController extends SystemBaseController{
	@Autowired
	private StudentInRegionService studentInRegionService;
	@Autowired
	private StudentRegionDao studentRegionDao;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentInRegion:studentInRegion")
	String StudentInRegion(){
	    return "region/student/studentInRegion/studentInRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentInRegion:studentInRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInDO> studentInRegionList = studentInRegionService.list(query);
        for (StudentInDO item : studentInRegionList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_in","status",item.getStatus()));
        }
		int total = studentInRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentInRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentInRegion:add")
	String add(Model model){
	    return "region/student/studentInRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentInRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentInDO studentInRegion = studentInRegionService.get(id);
		model.addAttribute("studentInRegion", studentInRegion);
	    return "region/student/studentInRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentInRegion:add")
	public R save( StudentInDO studentInRegion){
        studentInRegion.setOperator(ShiroUtils.getUserId().toString());
		if(studentInRegionService.save(studentInRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentInRegion:edit")
	public R update( StudentInDO studentInRegion){
	    studentInRegion.setOperator(ShiroUtils.getUserId().toString());
		studentInRegionService.update(studentInRegion);
		return R.ok();
	}
	@GetMapping("/migration")
	@RequiresPermissions("student:studentInMigration:studentInMigration")
	String StudentIn(){
		return "region/region/studentInMigration/studentInMigration";
	}
	@ResponseBody
	@GetMapping("/migrationList")
	@RequiresPermissions("student:studentInMigration:studentInMigration")
	public PageUtils migrationList(@RequestParam Map<String, Object> params){

		//查询列表数据
		Query query = new Query(params);
		List<StudentInDO> studentInList = studentInRegionService.migrationList(query);
		for (StudentInDO item : studentInList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_in","status",item.getStatus()));
			item.setGender(FieldDictUtil.get(getAppName(),"sys_worker","gender",item.getGender()));

		}
		int total = studentInRegionService.migrationCount(query);
		PageUtils pageUtils = new PageUtils(studentInList, total);
		return pageUtils;

	}


	/**
	 * 调档
	 */
	@ResponseBody
	@RequestMapping("/updateMigration")
	@RequiresPermissions("student:studentInMigration:studentInMigration")
	public R updateMigration( StudentInDO studentInRegion){

		StudentDO studentDO = studentRegionDao.get(studentInRegion.getStudentid());
		if(studentDO!=null){
			if(!studentDO.getCertificateNo().equals(studentInRegion.getCertificateNo())){
				return R.ok("身份证号码与调入准考证号身份证不一致，无法调档！");
			}

		}
		if(studentDO==null){
			return R.ok("没有找到此准考证人员信息，无法调档！");
		}
		studentInRegion.setOperator(ShiroUtils.getUserId().toString());
		studentInRegionService.updateMigration(studentInRegion);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentInRegion:remove")
	public R remove( Long id){
		if(studentInRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentInRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentInRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
