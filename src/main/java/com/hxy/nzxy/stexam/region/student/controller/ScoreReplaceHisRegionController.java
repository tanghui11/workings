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

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreReplaceHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreReplaceHisRegion")
public class ScoreReplaceHisRegionController extends SystemBaseController{
	@Autowired
	private ScoreReplaceHisRegionService scoreReplaceHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplaceHisRegion:scoreReplaceHisRegion")
	String ScoreReplaceHisRegion(){
	    return "region/student/scoreReplaceHisRegion/scoreReplaceHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplaceHisRegion:scoreReplaceHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceHisDO> scoreReplaceHisRegionList = scoreReplaceHisRegionService.list(query);
        for (ScoreReplaceHisDO item : scoreReplaceHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplaceHisRegion:add")
	String add(Model model){
	    return "region/student/scoreReplaceHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplaceHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceHisDO scoreReplaceHisRegion = scoreReplaceHisRegionService.get(id);
		model.addAttribute("scoreReplaceHisRegion", scoreReplaceHisRegion);
	    return "region/student/scoreReplaceHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplaceHisRegion:add")
	public R save( ScoreReplaceHisDO scoreReplaceHisRegion){
        scoreReplaceHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceHisRegionService.save(scoreReplaceHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplaceHisRegion:edit")
	public R update( ScoreReplaceHisDO scoreReplaceHisRegion){
	    scoreReplaceHisRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceHisRegionService.update(scoreReplaceHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHisRegion:remove")
	public R remove( Long id){
		if(scoreReplaceHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
