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

import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 座次安排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/seatArrangeHis")
public class SeatArrangeHisController extends SystemBaseController{
	@Autowired
	private SeatArrangeHisService seatArrangeHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:seatArrangeHis:seatArrangeHis")
	String SeatArrangeHis(){
	    return "center/region/seatArrangeHis/seatArrangeHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:seatArrangeHis:seatArrangeHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SeatArrangeHisDO> seatArrangeHisList = seatArrangeHisService.list(query);
        for (SeatArrangeHisDO item : seatArrangeHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = seatArrangeHisService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:seatArrangeHis:add")
	String add(Model model){
	    return "center/region/seatArrangeHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:seatArrangeHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SeatArrangeHisDO seatArrangeHis = seatArrangeHisService.get(id);
		model.addAttribute("seatArrangeHis", seatArrangeHis);
	    return "center/region/seatArrangeHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:seatArrangeHis:add")
	public R save( SeatArrangeHisDO seatArrangeHis){
        seatArrangeHis.setOperator(ShiroUtils.getUserId().toString());
		if(seatArrangeHisService.save(seatArrangeHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:seatArrangeHis:edit")
	public R update( SeatArrangeHisDO seatArrangeHis){
	    seatArrangeHis.setOperator(ShiroUtils.getUserId().toString());
		seatArrangeHisService.update(seatArrangeHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeHis:remove")
	public R remove( Long id){
		if(seatArrangeHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		seatArrangeHisService.batchRemove(ids);
		return R.ok();
	}
	
}
