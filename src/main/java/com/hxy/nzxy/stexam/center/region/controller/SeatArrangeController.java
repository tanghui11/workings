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

import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 座次安排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/seatArrange")
public class SeatArrangeController extends SystemBaseController{
	@Autowired
	private SeatArrangeService seatArrangeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:seatArrange:seatArrange")
	String SeatArrange(){
	    return "center/region/seatArrange/seatArrange";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:seatArrange:seatArrange")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SeatArrangeDO> seatArrangeList = seatArrangeService.list(query);
        for (SeatArrangeDO item : seatArrangeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = seatArrangeService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:seatArrange:add")
	String add(Model model){
	    return "center/region/seatArrange/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:seatArrange:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SeatArrangeDO seatArrange = seatArrangeService.get(id);
		model.addAttribute("seatArrange", seatArrange);
	    return "center/region/seatArrange/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:seatArrange:add")
	public R save( SeatArrangeDO seatArrange){
        seatArrange.setOperator(ShiroUtils.getUserId().toString());
		if(seatArrangeService.save(seatArrange)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:seatArrange:edit")
	public R update( SeatArrangeDO seatArrange){
	    seatArrange.setOperator(ShiroUtils.getUserId().toString());
		seatArrangeService.update(seatArrange);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:seatArrange:remove")
	public R remove( Long id){
		if(seatArrangeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:seatArrange:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		seatArrangeService.batchRemove(ids);
		return R.ok();
	}
	
}
