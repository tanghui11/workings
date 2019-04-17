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

import com.hxy.nzxy.stexam.domain.ScoreHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreHisRegion")
public class ScoreHisRegionController extends SystemBaseController{
	@Autowired
	private ScoreHisRegionService scoreHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreHisRegion:scoreHisRegion")
	String ScoreHisRegion(){
	    return "region/student/scoreHisRegion/scoreHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreHisRegion:scoreHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreHisDO> scoreHisRegionList = scoreHisRegionService.list(query);
        for (ScoreHisDO item : scoreHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreHisRegion:add")
	String add(Model model){
	    return "region/student/scoreHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreHisDO scoreHisRegion = scoreHisRegionService.get(id);
		model.addAttribute("scoreHisRegion", scoreHisRegion);
	    return "region/student/scoreHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreHisRegion:add")
	public R save( ScoreHisDO scoreHisRegion){
        scoreHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreHisRegionService.save(scoreHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreHisRegion:edit")
	public R update( ScoreHisDO scoreHisRegion){
	    scoreHisRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreHisRegionService.update(scoreHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreHisRegion:remove")
	public R remove( Long id){
		if(scoreHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
