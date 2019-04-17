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

import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;
import com.hxy.nzxy.stexam.region.region.service.SeatArrangeHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 座次安排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/seatArrangeHisRegion")
public class SeatArrangeHisRegionController extends SystemBaseController{
	@Autowired
	private SeatArrangeHisRegionService seatArrangeHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:seatArrangeHisRegion:seatArrangeHisRegion")
	String SeatArrangeHisRegion(){
	    return "region/region/seatArrangeHisRegion/seatArrangeHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:seatArrangeHisRegion:seatArrangeHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SeatArrangeHisDO> seatArrangeHisRegionList = seatArrangeHisRegionService.list(query);
        for (SeatArrangeHisDO item : seatArrangeHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = seatArrangeHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:seatArrangeHisRegion:add")
	String add(Model model){
	    return "region/region/seatArrangeHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:seatArrangeHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SeatArrangeHisDO seatArrangeHisRegion = seatArrangeHisRegionService.get(id);
		model.addAttribute("seatArrangeHisRegion", seatArrangeHisRegion);
	    return "region/region/seatArrangeHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:seatArrangeHisRegion:add")
	public R save( SeatArrangeHisDO seatArrangeHisRegion){
        seatArrangeHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(seatArrangeHisRegionService.save(seatArrangeHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:seatArrangeHisRegion:edit")
	public R update( SeatArrangeHisDO seatArrangeHisRegion){
	    seatArrangeHisRegion.setOperator(ShiroUtils.getUserId().toString());
		seatArrangeHisRegionService.update(seatArrangeHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeHisRegion:remove")
	public R remove( Long id){
		if(seatArrangeHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		seatArrangeHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
