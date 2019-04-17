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

import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;
import com.hxy.nzxy.stexam.region.region.service.RoomArrangeHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场编排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/roomArrangeHisRegion")
public class RoomArrangeHisRegionController extends SystemBaseController{
	@Autowired
	private RoomArrangeHisRegionService roomArrangeHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:roomArrangeHisRegion:roomArrangeHisRegion")
	String RoomArrangeHisRegion(){
	    return "region/region/roomArrangeHisRegion/roomArrangeHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:roomArrangeHisRegion:roomArrangeHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomArrangeHisDO> roomArrangeHisRegionList = roomArrangeHisRegionService.list(query);
        for (RoomArrangeHisDO item : roomArrangeHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = roomArrangeHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(roomArrangeHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:roomArrangeHisRegion:add")
	String add(Model model){
	    return "region/region/roomArrangeHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:roomArrangeHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomArrangeHisDO roomArrangeHisRegion = roomArrangeHisRegionService.get(id);
		model.addAttribute("roomArrangeHisRegion", roomArrangeHisRegion);
	    return "region/region/roomArrangeHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:roomArrangeHisRegion:add")
	public R save( RoomArrangeHisDO roomArrangeHisRegion){
        roomArrangeHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(roomArrangeHisRegionService.save(roomArrangeHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:roomArrangeHisRegion:edit")
	public R update( RoomArrangeHisDO roomArrangeHisRegion){
	    roomArrangeHisRegion.setOperator(ShiroUtils.getUserId().toString());
		roomArrangeHisRegionService.update(roomArrangeHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeHisRegion:remove")
	public R remove( Long id){
		if(roomArrangeHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		roomArrangeHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
