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

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteSubmitHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考点上报_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/examSiteSubmitHisRegion")
public class ExamSiteSubmitHisRegionController extends SystemBaseController{
	@Autowired
	private ExamSiteSubmitHisRegionService examSiteSubmitHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examSiteSubmitHisRegion:examSiteSubmitHisRegion")
	String ExamSiteSubmitHisRegion(){
	    return "region/region/examSiteSubmitHisRegion/examSiteSubmitHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examSiteSubmitHisRegion:examSiteSubmitHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamSiteSubmitHisDO> examSiteSubmitHisRegionList = examSiteSubmitHisRegionService.list(query);
        for (ExamSiteSubmitHisDO item : examSiteSubmitHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examSiteSubmitHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examSiteSubmitHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examSiteSubmitHisRegion:add")
	String add(Model model){
	    return "region/region/examSiteSubmitHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examSiteSubmitHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamSiteSubmitHisDO examSiteSubmitHisRegion = examSiteSubmitHisRegionService.get(id);
		model.addAttribute("examSiteSubmitHisRegion", examSiteSubmitHisRegion);
	    return "region/region/examSiteSubmitHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examSiteSubmitHisRegion:add")
	public R save( ExamSiteSubmitHisDO examSiteSubmitHisRegion){
        examSiteSubmitHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examSiteSubmitHisRegionService.save(examSiteSubmitHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examSiteSubmitHisRegion:edit")
	public R update( ExamSiteSubmitHisDO examSiteSubmitHisRegion){
	    examSiteSubmitHisRegion.setOperator(ShiroUtils.getUserId().toString());
		examSiteSubmitHisRegionService.update(examSiteSubmitHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitHisRegion:remove")
	public R remove( Long id){
		if(examSiteSubmitHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examSiteSubmitHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
