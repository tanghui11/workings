package com.hxy.nzxy.stexam.region.exam.controller;

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

import com.hxy.nzxy.stexam.domain.EducateLengthDO;
import com.hxy.nzxy.stexam.region.exam.service.EducateLengthRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 学制定义
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
 
@Controller
@RequestMapping("/exam/educateLengthRegion")
public class EducateLengthRegionController extends SystemBaseController{
	@Autowired
	private EducateLengthRegionService educateLengthRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:educateLengthRegion:educateLengthRegion")
	String EducateLengthRegion(){
	    return "region/exam/educateLengthRegion/educateLengthRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:educateLengthRegion:educateLengthRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EducateLengthDO> educateLengthRegionList = educateLengthRegionService.list(query);
        for (EducateLengthDO item : educateLengthRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = educateLengthRegionService.count(query);
		PageUtils pageUtils = new PageUtils(educateLengthRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:educateLengthRegion:add")
	String add(Model model){
	    return "region/exam/educateLengthRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:educateLengthRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EducateLengthDO educateLengthRegion = educateLengthRegionService.get(id);
		model.addAttribute("educateLengthRegion", educateLengthRegion);
	    return "region/exam/educateLengthRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:educateLengthRegion:add")
	public R save( EducateLengthDO educateLengthRegion){
        educateLengthRegion.setOperator(ShiroUtils.getUserId().toString());
		if(educateLengthRegionService.save(educateLengthRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:educateLengthRegion:edit")
	public R update( EducateLengthDO educateLengthRegion){
	    educateLengthRegion.setOperator(ShiroUtils.getUserId().toString());
		educateLengthRegionService.update(educateLengthRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:educateLengthRegion:remove")
	public R remove( Long id){
		if(educateLengthRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:educateLengthRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		educateLengthRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
