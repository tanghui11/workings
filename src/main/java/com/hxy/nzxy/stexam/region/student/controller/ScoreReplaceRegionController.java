package com.hxy.nzxy.stexam.region.student.controller;

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

import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreReplaceRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreReplaceRegion")
public class ScoreReplaceRegionController extends SystemBaseController{
	@Autowired
	private ScoreReplaceRegionService scoreReplaceRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplaceRegion:scoreReplaceRegion")
	String ScoreReplaceRegion(){
	    return "region/student/scoreReplaceRegion/scoreReplaceRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplaceRegion:scoreReplaceRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceDO> scoreReplaceRegionList = scoreReplaceRegionService.list(query);
        for (ScoreReplaceDO item : scoreReplaceRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplaceRegion:add")
	String add(Model model){
	    return "region/student/scoreReplaceRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplaceRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceDO scoreReplaceRegion = scoreReplaceRegionService.get(id);
		model.addAttribute("scoreReplaceRegion", scoreReplaceRegion);
	    return "region/student/scoreReplaceRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplaceRegion:add")
	public R save( ScoreReplaceDO scoreReplaceRegion){
        scoreReplaceRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceRegionService.save(scoreReplaceRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplaceRegion:edit")
	public R update( ScoreReplaceDO scoreReplaceRegion){
	    scoreReplaceRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceRegionService.update(scoreReplaceRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceRegion:remove")
	public R remove( Long id){
		if(scoreReplaceRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
