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

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreChangeLogRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 成绩变更日志表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreChangeLogRegion")
public class ScoreChangeLogRegionController extends SystemBaseController{
	@Autowired
	private ScoreChangeLogRegionService scoreChangeLogRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreChangeLogRegion:scoreChangeLogRegion")
	String ScoreChangeLogRegion(){
	    return "region/student/scoreChangeLogRegion/scoreChangeLogRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreChangeLogRegion:scoreChangeLogRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreChangeLogDO> scoreChangeLogRegionList = scoreChangeLogRegionService.list(query);
        for (ScoreChangeLogDO item : scoreChangeLogRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreChangeLogRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreChangeLogRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreChangeLogRegion:add")
	String add(Model model){
	    return "region/student/scoreChangeLogRegion/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:scoreChangeLogRegion:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		ScoreChangeLogDO scoreChangeLogRegion = scoreChangeLogRegionService.get(changeType);
		model.addAttribute("scoreChangeLogRegion", scoreChangeLogRegion);
	    return "region/student/scoreChangeLogRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreChangeLogRegion:add")
	public R save( ScoreChangeLogDO scoreChangeLogRegion){
        scoreChangeLogRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreChangeLogRegionService.save(scoreChangeLogRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreChangeLogRegion:edit")
	public R update( ScoreChangeLogDO scoreChangeLogRegion){
	    scoreChangeLogRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreChangeLogRegionService.update(scoreChangeLogRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLogRegion:remove")
	public R remove( String changeType){
		if(scoreChangeLogRegionService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLogRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		scoreChangeLogRegionService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
