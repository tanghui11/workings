package com.hxy.nzxy.stexam.center.school.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.CollegeSpecialityVO;
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
import com.hxy.nzxy.stexam.center.school.service.CollegeSpecialityService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 专业管理对应
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/collegeSpeciality")
public class CollegeSpecialityController extends SystemBaseController{
	@Autowired
	private CollegeSpecialityService collegeSpecialityService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:collegeSpeciality:collegeSpeciality")
	String CollegeSpeciality(){
	    return "center/school/collegeSpeciality/collegeSpeciality";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:collegeSpeciality:collegeSpeciality")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CollegeSpecialityDO> collegeSpecialityList = collegeSpecialityService.list(query);
        for (CollegeSpecialityDO item : collegeSpecialityList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = collegeSpecialityService.count(query);
		PageUtils pageUtils = new PageUtils(collegeSpecialityList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:collegeSpeciality:add")
	String add(Model model){
	    return "center/school/collegeSpeciality/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:collegeSpeciality:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CollegeSpecialityDO collegeSpeciality = collegeSpecialityService.get(id);
		model.addAttribute("collegeSpeciality", collegeSpeciality);
	    return "center/school/collegeSpeciality/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:collegeSpeciality:add")
	public R save( CollegeSpecialityDO collegeSpeciality){
        collegeSpeciality.setOperator(ShiroUtils.getUserId().toString());
		if(collegeSpecialityService.save(collegeSpeciality)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:collegeSpeciality:edit")
	public R update( CollegeSpecialityDO collegeSpeciality){
	    collegeSpeciality.setOperator(ShiroUtils.getUserId().toString());
		collegeSpecialityService.update(collegeSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:collegeSpeciality:remove")
	public R remove( Long id){
		if(collegeSpecialityService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:collegeSpeciality:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		collegeSpecialityService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 院校专业(专业id,名称专业开设备案编号,专业开设编号)接口
	 */

	@ResponseBody
	@GetMapping("/listCollegeSpeciality")
	public List<CollegeSpecialityVO> listCollegeSpeciality(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<CollegeSpecialityVO> collegeSpecialityList = collegeSpecialityService.listCollegeSpeciality(params);
		for (CollegeSpecialityVO item : collegeSpecialityList) {
			item.setName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getId()) + item.getDirection());
		}
		return collegeSpecialityList;
	}
}
