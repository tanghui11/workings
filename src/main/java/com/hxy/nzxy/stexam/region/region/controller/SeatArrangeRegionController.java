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

import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import com.hxy.nzxy.stexam.region.region.service.SeatArrangeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 座次安排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/seatArrangeRegion")
public class SeatArrangeRegionController extends SystemBaseController{
	@Autowired
	private SeatArrangeRegionService seatArrangeRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:seatArrangeRegion:seatArrangeRegion")
	String SeatArrangeRegion(){
	    return "region/region/seatArrangeRegion/seatArrangeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:seatArrangeRegion:seatArrangeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SeatArrangeDO> seatArrangeRegionList = seatArrangeRegionService.list(query);
        for (SeatArrangeDO item : seatArrangeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = seatArrangeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:seatArrangeRegion:add")
	String add(Model model){
	    return "region/region/seatArrangeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:seatArrangeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SeatArrangeDO seatArrangeRegion = seatArrangeRegionService.get(id);
		model.addAttribute("seatArrangeRegion", seatArrangeRegion);
	    return "region/region/seatArrangeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:seatArrangeRegion:add")
	public R save( SeatArrangeDO seatArrangeRegion){
        seatArrangeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(seatArrangeRegionService.save(seatArrangeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:seatArrangeRegion:edit")
	public R update( SeatArrangeDO seatArrangeRegion){
	    seatArrangeRegion.setOperator(ShiroUtils.getUserId().toString());
		seatArrangeRegionService.update(seatArrangeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeRegion:remove")
	public R remove( Long id){
		if(seatArrangeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		seatArrangeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
