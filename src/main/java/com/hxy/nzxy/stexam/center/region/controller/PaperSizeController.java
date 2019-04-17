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

import com.hxy.nzxy.stexam.domain.PaperSizeDO;
import com.hxy.nzxy.stexam.center.region.service.PaperSizeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 卷袋设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/paperSize")
public class PaperSizeController extends SystemBaseController{
	@Autowired
	private PaperSizeService paperSizeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:paperSize:paperSize")
	String PaperSize(){
	    return "center/region/paperSize/paperSize";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:paperSize:paperSize")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaperSizeDO> paperSizeList = paperSizeService.list(query);
        for (PaperSizeDO item : paperSizeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = paperSizeService.count(query);
		PageUtils pageUtils = new PageUtils(paperSizeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:paperSize:add")
	String add(Model model){
	    return "center/region/paperSize/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:paperSize:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PaperSizeDO paperSize = paperSizeService.get(id);
		model.addAttribute("paperSize", paperSize);
	    return "center/region/paperSize/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:paperSize:add")
	public R save( PaperSizeDO paperSize){
        paperSize.setOperator(ShiroUtils.getUserId().toString());
		if(paperSizeService.save(paperSize)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:paperSize:edit")
	public R update( PaperSizeDO paperSize){
	    paperSize.setOperator(ShiroUtils.getUserId().toString());
		paperSizeService.update(paperSize);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:paperSize:remove")
	public R remove( Long id){
		if(paperSizeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:paperSize:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		paperSizeService.batchRemove(ids);
		return R.ok();
	}
	
}
