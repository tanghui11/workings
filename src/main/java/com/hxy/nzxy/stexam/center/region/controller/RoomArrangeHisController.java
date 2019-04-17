package com.hxy.nzxy.stexam.center.region.controller;

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
import com.hxy.nzxy.stexam.center.region.service.RoomArrangeHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场编排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/roomArrangeHis")
public class RoomArrangeHisController extends SystemBaseController{
	@Autowired
	private RoomArrangeHisService roomArrangeHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:roomArrangeHis:roomArrangeHis")
	String RoomArrangeHis(){
	    return "center/region/roomArrangeHis/roomArrangeHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:roomArrangeHis:roomArrangeHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomArrangeHisDO> roomArrangeHisList = roomArrangeHisService.list(query);
        for (RoomArrangeHisDO item : roomArrangeHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = roomArrangeHisService.count(query);
		PageUtils pageUtils = new PageUtils(roomArrangeHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:roomArrangeHis:add")
	String add(Model model){
	    return "center/region/roomArrangeHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:roomArrangeHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomArrangeHisDO roomArrangeHis = roomArrangeHisService.get(id);
		model.addAttribute("roomArrangeHis", roomArrangeHis);
	    return "center/region/roomArrangeHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:roomArrangeHis:add")
	public R save( RoomArrangeHisDO roomArrangeHis){
        roomArrangeHis.setOperator(ShiroUtils.getUserId().toString());
		if(roomArrangeHisService.save(roomArrangeHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:roomArrangeHis:edit")
	public R update( RoomArrangeHisDO roomArrangeHis){
	    roomArrangeHis.setOperator(ShiroUtils.getUserId().toString());
		roomArrangeHisService.update(roomArrangeHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeHis:remove")
	public R remove( Long id){
		if(roomArrangeHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		roomArrangeHisService.batchRemove(ids);
		return R.ok();
	}
	
}
