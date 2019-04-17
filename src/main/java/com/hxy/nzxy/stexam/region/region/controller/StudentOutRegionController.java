package com.hxy.nzxy.stexam.region.region.controller;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.region.region.service.StudentOutRegionService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/studentOutRegion")
public class StudentOutRegionController extends SystemBaseController{
	@Autowired
	private StudentOutRegionService studentOutRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:studentOutRegion:studentOutRegion")
	String StudentOutRegion(){
	    return "region/region/studentOutRegion/studentOutRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:studentOutRegion:studentOutRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentOutDO> studentOutRegionList = studentOutRegionService.list(query);
        for (StudentOutDO item : studentOutRegionList) {
			item.setRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy", "id", item.getRegionid()+""));
			item.setProvinceName(FieldDictUtil.get(getAppName(), "sys_native_nzxy", "id", item.getProvince()));
			item.setCityName(FieldDictUtil.get(getAppName(), "sys_native_nzxy", "id", item.getCity()));
			item.setCause(FieldDictUtil.get(getAppName(), "reg_student_out", "cause", item.getCause()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_student_out", "status", item.getStatus()));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setZkInZyDmName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getZkInZyDm()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentOutRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentOutRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:studentOutRegion:add")
	String add(Model model){

		model.addAttribute("reg_student_out_cause", commonService.listFieldDict(getAppName(), "reg_student_out", "cause"));

		return "region/region/studentOutRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:studentOutRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentOutDO studentOutRegion = studentOutRegionService.get(id);
		model.addAttribute("studentOutRegion", studentOutRegion);
	    return "region/region/studentOutRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:studentOutRegion:add")
	public R save( StudentOutDO studentOutRegion){
        studentOutRegion.setOperator(ShiroUtils.getUserId().toString());
		studentOutRegion.setStatus("a");

		if(studentOutRegionService.save(studentOutRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:studentOutRegion:edit")
	public R update( StudentOutDO studentOutRegion){
	    studentOutRegion.setOperator(ShiroUtils.getUserId().toString());
		studentOutRegionService.update(studentOutRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:studentOutRegion:remove")
	public R remove( Long id){
		if(studentOutRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:studentOutRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentOutRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
