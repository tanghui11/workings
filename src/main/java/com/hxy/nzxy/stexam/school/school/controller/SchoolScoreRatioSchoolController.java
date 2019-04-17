package com.hxy.nzxy.stexam.school.school.controller;

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
import com.hxy.nzxy.stexam.school.school.service.SchoolScoreRatioSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 平时成绩比例设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/schoolScoreRatioSchool")
public class SchoolScoreRatioSchoolController extends SystemBaseController{
	@Autowired
	private SchoolScoreRatioSchoolService schoolScoreRatioSchoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolScoreRatioSchool:schoolScoreRatioSchool")
	String SchoolScoreRatioSchool(){
	    return "school/school/schoolScoreRatioSchool/schoolScoreRatioSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolScoreRatioSchool:schoolScoreRatioSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolScoreRatioDO> schoolScoreRatioSchoolList = schoolScoreRatioSchoolService.list(query);
        for (SchoolScoreRatioDO item : schoolScoreRatioSchoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolScoreRatioSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolScoreRatioSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolScoreRatioSchool:add")
	String add(Model model){
	    return "school/school/schoolScoreRatioSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolScoreRatioSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolScoreRatioDO schoolScoreRatioSchool = schoolScoreRatioSchoolService.get(id);
		model.addAttribute("schoolScoreRatioSchool", schoolScoreRatioSchool);
	    return "school/school/schoolScoreRatioSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolScoreRatioSchool:add")
	public R save( SchoolScoreRatioDO schoolScoreRatioSchool){
        schoolScoreRatioSchool.setOperator(ShiroUtils.getUserId().toString());
		if(schoolScoreRatioSchoolService.save(schoolScoreRatioSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolScoreRatioSchool:edit")
	public R update( SchoolScoreRatioDO schoolScoreRatioSchool){
	    schoolScoreRatioSchool.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreRatioSchoolService.update(schoolScoreRatioSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolScoreRatioSchool:remove")
	public R remove( Long id){
		if(schoolScoreRatioSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolScoreRatioSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolScoreRatioSchoolService.batchRemove(ids);
		return R.ok();
	}
	
}
