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

import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreSchoolHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/scoreSchoolHisRegion")
public class ScoreSchoolHisRegionController extends SystemBaseController{
	@Autowired
	private ScoreSchoolHisRegionService scoreSchoolHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreSchoolHisRegion:scoreSchoolHisRegion")
	String ScoreSchoolHisRegion(){
	    return "region/student/scoreSchoolHisRegion/scoreSchoolHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchoolHisRegion:scoreSchoolHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolHisDO> scoreSchoolHisRegionList = scoreSchoolHisRegionService.list(query);
        for (ScoreSchoolHisDO item : scoreSchoolHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchoolHisRegion:add")
	String add(Model model){
	    return "region/student/scoreSchoolHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchoolHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolHisDO scoreSchoolHisRegion = scoreSchoolHisRegionService.get(id);
		model.addAttribute("scoreSchoolHisRegion", scoreSchoolHisRegion);
	    return "region/student/scoreSchoolHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchoolHisRegion:add")
	public R save( ScoreSchoolHisDO scoreSchoolHisRegion){
        scoreSchoolHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolHisRegionService.save(scoreSchoolHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchoolHisRegion:edit")
	public R update( ScoreSchoolHisDO scoreSchoolHisRegion){
	    scoreSchoolHisRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolHisRegionService.update(scoreSchoolHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHisRegion:remove")
	public R remove( Long id){
		if(scoreSchoolHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
