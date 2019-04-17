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

import com.hxy.nzxy.stexam.domain.FoulPunishDO;
import com.hxy.nzxy.stexam.region.exam.service.FoulPunishRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 犯规及处罚设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
 
@Controller
@RequestMapping("/exam/foulPunishRegion")
public class FoulPunishRegionController extends SystemBaseController{
	@Autowired
	private FoulPunishRegionService foulPunishRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:foulPunishRegion:foulPunishRegion")
	String FoulPunishRegion(){
	    return "region/exam/foulPunishRegion/foulPunishRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:foulPunishRegion:foulPunishRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FoulPunishDO> foulPunishRegionList = foulPunishRegionService.list(query);
        for (FoulPunishDO item : foulPunishRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = foulPunishRegionService.count(query);
		PageUtils pageUtils = new PageUtils(foulPunishRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:foulPunishRegion:add")
	String add(Model model){
	    return "region/exam/foulPunishRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:foulPunishRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FoulPunishDO foulPunishRegion = foulPunishRegionService.get(id);
		model.addAttribute("foulPunishRegion", foulPunishRegion);
	    return "region/exam/foulPunishRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:foulPunishRegion:add")
	public R save( FoulPunishDO foulPunishRegion){
        foulPunishRegion.setOperator(ShiroUtils.getUserId().toString());
		if(foulPunishRegionService.save(foulPunishRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:foulPunishRegion:edit")
	public R update( FoulPunishDO foulPunishRegion){
	    foulPunishRegion.setOperator(ShiroUtils.getUserId().toString());
		foulPunishRegionService.update(foulPunishRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:foulPunishRegion:remove")
	public R remove( Long id){
		if(foulPunishRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:foulPunishRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		foulPunishRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
