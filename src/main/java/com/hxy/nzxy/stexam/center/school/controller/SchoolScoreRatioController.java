package com.hxy.nzxy.stexam.center.school.controller;

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

import com.hxy.nzxy.stexam.domain.SchoolScoreRatioDO;
import com.hxy.nzxy.stexam.center.school.service.SchoolScoreRatioService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 平时成绩比例设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/schoolScoreRatio")
public class SchoolScoreRatioController extends SystemBaseController{
	@Autowired
	private SchoolScoreRatioService schoolScoreRatioService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolScoreRatio:schoolScoreRatio")
	String SchoolScoreRatio(){
	    return "center/school/schoolScoreRatio/schoolScoreRatio";
	}
	@GetMapping("/School")
	String School(){
		return "center/school/schoolScoreRatio/school";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolScoreRatio:schoolScoreRatio")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolScoreRatioDO> schoolScoreRatioList = schoolScoreRatioService.list(query);
        for (SchoolScoreRatioDO item : schoolScoreRatioList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", item.getMethod()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolScoreRatioService.count(query);
		PageUtils pageUtils = new PageUtils(schoolScoreRatioList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolScoreRatio:add")
	String add(Model model){
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
	    return "center/school/schoolScoreRatio/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolScoreRatio:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolScoreRatioDO schoolScoreRatio = schoolScoreRatioService.get(id);
		model.addAttribute("schoolScoreRatio", schoolScoreRatio);
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_schoolName", FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", schoolScoreRatio.getSchoolid()+""));
		return "center/school/schoolScoreRatio/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolScoreRatio:add")
	public R save( SchoolScoreRatioDO schoolScoreRatio){
        schoolScoreRatio.setOperator(ShiroUtils.getUserId().toString());
		if(schoolScoreRatioService.save(schoolScoreRatio)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolScoreRatio:edit")
	public R update( SchoolScoreRatioDO schoolScoreRatio){
	    schoolScoreRatio.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreRatioService.update(schoolScoreRatio);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolScoreRatio:remove")
	public R remove( Long id){
		if(schoolScoreRatioService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolScoreRatio:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolScoreRatioService.batchRemove(ids);
		return R.ok();
	}
	
}
