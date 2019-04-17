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

import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreOldHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老课程成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreOldHisRegion")
public class ScoreOldHisRegionController extends SystemBaseController{
	@Autowired
	private ScoreOldHisRegionService scoreOldHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreOldHisRegion:scoreOldHisRegion")
	String ScoreOldHisRegion(){
	    return "region/student/scoreOldHisRegion/scoreOldHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreOldHisRegion:scoreOldHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreOldHisDO> scoreOldHisRegionList = scoreOldHisRegionService.list(query);
        for (ScoreOldHisDO item : scoreOldHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreOldHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreOldHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreOldHisRegion:add")
	String add(Model model){
	    return "region/student/scoreOldHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreOldHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreOldHisDO scoreOldHisRegion = scoreOldHisRegionService.get(id);
		model.addAttribute("scoreOldHisRegion", scoreOldHisRegion);
	    return "region/student/scoreOldHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreOldHisRegion:add")
	public R save( ScoreOldHisDO scoreOldHisRegion){
        scoreOldHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreOldHisRegionService.save(scoreOldHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreOldHisRegion:edit")
	public R update( ScoreOldHisDO scoreOldHisRegion){
	    scoreOldHisRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreOldHisRegionService.update(scoreOldHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHisRegion:remove")
	public R remove( Long id){
		if(scoreOldHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreOldHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
