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

import com.hxy.nzxy.stexam.domain.PaperSizeDO;
import com.hxy.nzxy.stexam.region.region.service.PaperSizeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 卷袋设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/paperSizeRegion")
public class PaperSizeRegionController extends SystemBaseController{
	@Autowired
	private PaperSizeRegionService paperSizeRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:paperSizeRegion:paperSizeRegion")
	String PaperSizeRegion(){
	    return "region/region/paperSizeRegion/paperSizeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:paperSizeRegion:paperSizeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaperSizeDO> paperSizeRegionList = paperSizeRegionService.list(query);
        for (PaperSizeDO item : paperSizeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = paperSizeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(paperSizeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:paperSizeRegion:add")
	String add(Model model){
	    return "region/region/paperSizeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:paperSizeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PaperSizeDO paperSizeRegion = paperSizeRegionService.get(id);
		model.addAttribute("paperSizeRegion", paperSizeRegion);
	    return "region/region/paperSizeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:paperSizeRegion:add")
	public R save( PaperSizeDO paperSizeRegion){
        paperSizeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(paperSizeRegionService.save(paperSizeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:paperSizeRegion:edit")
	public R update( PaperSizeDO paperSizeRegion){
	    paperSizeRegion.setOperator(ShiroUtils.getUserId().toString());
		paperSizeRegionService.update(paperSizeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:paperSizeRegion:remove")
	public R remove( Long id){
		if(paperSizeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:paperSizeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		paperSizeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
