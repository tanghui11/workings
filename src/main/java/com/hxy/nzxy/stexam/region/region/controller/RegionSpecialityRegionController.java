package com.hxy.nzxy.stexam.region.region.controller;

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

import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import com.hxy.nzxy.stexam.region.region.service.RegionSpecialityRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考区专业课程报考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/regionSpecialityRegion")
public class RegionSpecialityRegionController extends SystemBaseController{
	@Autowired
	private RegionSpecialityRegionService regionSpecialityRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:regionSpecialityRegion:regionSpecialityRegion")
	String RegionSpecialityRegion(){
	    return "region/region/regionSpecialityRegion/regionSpecialityRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:regionSpecialityRegion:regionSpecialityRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RegionSpecialityDO> regionSpecialityRegionList = regionSpecialityRegionService.list(query);
        for (RegionSpecialityDO item : regionSpecialityRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = regionSpecialityRegionService.count(query);
		PageUtils pageUtils = new PageUtils(regionSpecialityRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:regionSpecialityRegion:add")
	String add(Model model){
	    return "region/region/regionSpecialityRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:regionSpecialityRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RegionSpecialityDO regionSpecialityRegion = regionSpecialityRegionService.get(id);
		model.addAttribute("regionSpecialityRegion", regionSpecialityRegion);
	    return "region/region/regionSpecialityRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:regionSpecialityRegion:add")
	public R save( RegionSpecialityDO regionSpecialityRegion){
        regionSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		if(regionSpecialityRegionService.save(regionSpecialityRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:regionSpecialityRegion:edit")
	public R update( RegionSpecialityDO regionSpecialityRegion){
	    regionSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		regionSpecialityRegionService.update(regionSpecialityRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:regionSpecialityRegion:remove")
	public R remove( Long id){
		if(regionSpecialityRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:regionSpecialityRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		regionSpecialityRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
