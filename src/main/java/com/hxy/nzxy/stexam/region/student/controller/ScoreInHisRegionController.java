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

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreInHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreInHisRegion")
public class ScoreInHisRegionController extends SystemBaseController{
	@Autowired
	private ScoreInHisRegionService scoreInHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreInHisRegion:scoreInHisRegion")
	String ScoreInHisRegion(){
	    return "region/student/scoreInHisRegion/scoreInHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreInHisRegion:scoreInHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInHisDO> scoreInHisRegionList = scoreInHisRegionService.list(query);
        for (ScoreInHisDO item : scoreInHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreInHisRegion:add")
	String add(Model model){
	    return "region/student/scoreInHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreInHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInHisDO scoreInHisRegion = scoreInHisRegionService.get(id);
		model.addAttribute("scoreInHisRegion", scoreInHisRegion);
	    return "region/student/scoreInHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreInHisRegion:add")
	public R save( ScoreInHisDO scoreInHisRegion){
        scoreInHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInHisRegionService.save(scoreInHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreInHisRegion:edit")
	public R update( ScoreInHisDO scoreInHisRegion){
	    scoreInHisRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreInHisRegionService.update(scoreInHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHisRegion:remove")
	public R remove( Long id){
		if(scoreInHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreInHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
