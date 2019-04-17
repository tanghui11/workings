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

import com.hxy.nzxy.stexam.domain.PaperSchoolDO;
import com.hxy.nzxy.stexam.region.exam.service.PaperSchoolRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 阅卷点设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
 
@Controller
@RequestMapping("/exam/paperSchoolRegion")
public class PaperSchoolRegionController extends SystemBaseController{
	@Autowired
	private PaperSchoolRegionService paperSchoolRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:paperSchoolRegion:paperSchoolRegion")
	String PaperSchoolRegion(){
	    return "region/exam/paperSchoolRegion/paperSchoolRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:paperSchoolRegion:paperSchoolRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaperSchoolDO> paperSchoolRegionList = paperSchoolRegionService.list(query);
        for (PaperSchoolDO item : paperSchoolRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = paperSchoolRegionService.count(query);
		PageUtils pageUtils = new PageUtils(paperSchoolRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:paperSchoolRegion:add")
	String add(Model model){
	    return "region/exam/paperSchoolRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:paperSchoolRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PaperSchoolDO paperSchoolRegion = paperSchoolRegionService.get(id);
		model.addAttribute("paperSchoolRegion", paperSchoolRegion);
	    return "region/exam/paperSchoolRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:paperSchoolRegion:add")
	public R save( PaperSchoolDO paperSchoolRegion){
        paperSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
		if(paperSchoolRegionService.save(paperSchoolRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:paperSchoolRegion:edit")
	public R update( PaperSchoolDO paperSchoolRegion){
	    paperSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
		paperSchoolRegionService.update(paperSchoolRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:paperSchoolRegion:remove")
	public R remove( Long id){
		if(paperSchoolRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:paperSchoolRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		paperSchoolRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
