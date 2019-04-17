package com.hxy.nzxy.stexam.school.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
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

import com.hxy.nzxy.stexam.domain.CollegeSpecialityDO;
import com.hxy.nzxy.stexam.school.school.service.CollegeSpecialitySchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 专业管理对应
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:40
 */
 
@Controller
@RequestMapping("/school/collegeSpecialitySchool")
public class CollegeSpecialitySchoolController extends SystemBaseController{
	@Autowired
	private CollegeSpecialitySchoolService collegeSpecialitySchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private SpecialityRecordService specialityRecordService;
	@GetMapping()
	@RequiresPermissions("school:collegeSpecialitySchool:collegeSpecialitySchool")
	String CollegeSpecialitySchool(){
	    return "school/school/collegeSpecialitySchool/collegeSpecialitySchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:collegeSpecialitySchool:collegeSpecialitySchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CollegeSpecialityDO> collegeSpecialitySchoolList = collegeSpecialitySchoolService.list(query);
        for (CollegeSpecialityDO item : collegeSpecialitySchoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = collegeSpecialitySchoolService.count(query);
		PageUtils pageUtils = new PageUtils(collegeSpecialitySchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:collegeSpecialitySchool:add")
	String add(Model model){
	    return "school/school/collegeSpecialitySchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:collegeSpecialitySchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CollegeSpecialityDO collegeSpecialitySchool = collegeSpecialitySchoolService.get(id);
		model.addAttribute("collegeSpecialitySchool", collegeSpecialitySchool);
	    return "school/school/collegeSpecialitySchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:collegeSpecialitySchool:add")
	public R save( CollegeSpecialityDO collegeSpecialitySchool){
        collegeSpecialitySchool.setOperator(ShiroUtils.getUserId().toString());

		if(collegeSpecialitySchoolService.save(collegeSpecialitySchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:collegeSpecialitySchool:edit")
	public R update( CollegeSpecialityDO collegeSpecialitySchool){
	    collegeSpecialitySchool.setOperator(ShiroUtils.getUserId().toString());
		collegeSpecialitySchoolService.update(collegeSpecialitySchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:collegeSpecialitySchool:remove")
	public R remove( Long id){
		if(collegeSpecialitySchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:collegeSpecialitySchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		collegeSpecialitySchoolService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 待分配专业
	 */
	@ResponseBody
	@GetMapping("/dList")
	@RequiresPermissions("school:collegeSpecialitySchool:collegeSpecialitySchool")
	public PageUtils dList(@RequestParam Map<String, Object> params, HttpServletRequest request){

		Map map= new HashMap();


		map.put("status","a");
		map.put("type","b");
		map.put("schoolid",ShiroUtils.getUser().getWorkerid());
		//查询列表数据
		Query query = new Query(params);
		List<CollegeSpecialityDO> collegeSpecialitySchoolList = collegeSpecialitySchoolService.dist(query);
		for (CollegeSpecialityDO item : collegeSpecialitySchoolList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_course", "status", item.getStatus())+item.getDirection());
			item.setSpecialityname(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));

			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = collegeSpecialitySchoolService.dcount(query);
		PageUtils pageUtils = new PageUtils(collegeSpecialitySchoolList, total);
		return pageUtils;
	}
	/**
	 * 已授权专业
	 */
	@ResponseBody
	@GetMapping("/yList")
	@RequiresPermissions("school:collegeSpecialitySchool:collegeSpecialitySchool")
	public PageUtils yList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CollegeSpecialityDO> collegeSpecialitySchoolList = collegeSpecialitySchoolService.ylist(query);
		for (CollegeSpecialityDO item : collegeSpecialitySchoolList) {
			item.setSpecialityname(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid())+item.getDirection());

			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_course", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = collegeSpecialitySchoolService.ycount(query);
		PageUtils pageUtils = new PageUtils(collegeSpecialitySchoolList, total);
		return pageUtils;
	}
	/**
	 * 待分配专业页面吧
	 */
	@GetMapping("/waitSpecialitySchool")
	String waitSpecialitySchool(){
		return "school/school/collegeSpecialitySchool/waitSpecialitySchool";
	}
}
