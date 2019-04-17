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

import com.hxy.nzxy.stexam.domain.ScoreInDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreInRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreInRegion")
public class ScoreInRegionController extends SystemBaseController{
	@Autowired
	private ScoreInRegionService scoreInRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreInRegion:scoreInRegion")
	String ScoreInRegion(){
	    return "region/student/scoreInRegion/scoreInRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreInRegion:scoreInRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInDO> scoreInRegionList = scoreInRegionService.list(query);
        for (ScoreInDO item : scoreInRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreInRegion:add")
	String add(Model model){
	    return "region/student/scoreInRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreInRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInDO scoreInRegion = scoreInRegionService.get(id);
		model.addAttribute("scoreInRegion", scoreInRegion);
	    return "region/student/scoreInRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreInRegion:add")
	public R save( ScoreInDO scoreInRegion){
        scoreInRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInRegionService.save(scoreInRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreInRegion:edit")
	public R update( ScoreInDO scoreInRegion){
	    scoreInRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreInRegionService.update(scoreInRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreInRegion:remove")
	public R remove( Long id){
		if(scoreInRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreInRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreInRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
